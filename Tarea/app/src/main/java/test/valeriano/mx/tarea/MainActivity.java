package test.valeriano.mx.tarea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import test.valeriano.mx.tarea.util.PreferenceUtil;

/*Constructor principal, debe extender de @AppCompatActivity para tener funcionalidades mejoradas
 de android, además estender de @OnClickListener para escuchar los eventos*/
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*Declaración de variables*/
    private EditText mUser;
    private EditText mPassword;
    private CheckBox chkRememberMe;
    private CheckBox chkHardCodeLogin;
    private View loading;
    private String [] usr_to_login = {"jose", "luis", "unam", "javier", "Zenkun"};
    private String [] pwd_to_login = {"unam"};
    private PreferenceUtil preferenceUtil;


    /* Actividad principal principal onCreate()*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        listenerdButton();
        preferenceUtil= new PreferenceUtil(getApplicationContext());
    }

    /*Metodo para escuchar el onclick*/
    public void onClick(View v) {
        try {
            switch (v.getId())        {
                case R.id.activity_main_btnLogin:
                    processLogin();
                    break;
                case R.id.activity_main_btnRegistrerLogin:
                    processRegistrer();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            showMsg(R.string.passMsg);
        }
    }

    /*Procesa el regsitro a las preferencias*/
    private void processRegistrer() {
        Intent intent=new Intent(getApplicationContext(),ActivityRegister.class);
        startActivity(intent);
    }

    /*Procesa el registro mediante lo guardado en la clase*/
    private void processLogin() {
        String user = mUser.getText().toString();
        String password = mPassword.getText().toString();
        loading.setVisibility(View.VISIBLE);
        if(TextUtils.isEmpty(user)) {
            showMsg(R.string.login_empty);
        }
        else if(TextUtils.isEmpty(password)) {
            showMsg(R.string.password_empty);
        }
        else {
            validateLogin(user, password);
        }
        loading.setVisibility(View.INVISIBLE);
    }

    /*Una vez validado guarda el usuario*/
    private void validateLogin(String user, String password) {
        boolean ingreso = false;
        for (int i=0; i<usr_to_login.length; i++) {
            if (user.equals(usr_to_login[i]) && password.equals(pwd_to_login[0])) {
                Toast.makeText(getApplicationContext(), R.string.pass_login, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ActivityDetail.class);
                intent.putExtra("key_user", user);
                ingreso = true;
                startActivity(intent);
                break;
            }
        }
        if (!ingreso){
            showMsg(R.string.wrong_credentials);
            mUser.setText("");
            mPassword.setText("");
        }
    }

    /*Metódo para dejar activos los listener de los botones*/
    private void listenerdButton() {
        try {
            findViewById(R.id.activity_main_btnLogin).setOnClickListener((View.OnClickListener) this);
            findViewById(R.id.activity_main_btnRegistrerLogin).setOnClickListener((View.OnClickListener) this);

            chkRememberMe.setOnCheckedChangeListener(
                    new CompoundButton.OnCheckedChangeListener(){
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            Log.d(ServiceTimer.TAG,"Checkeo es: "+isChecked);
                        }
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Metódo para buscar los elementos de la pantalla*/
    private void findView() {
        mUser = (EditText) findViewById(R.id.activity_main_user);
        mPassword = (EditText) findViewById(R.id.activity_main_password);
        loading = findViewById(R.id.progress);
        chkRememberMe = (CheckBox) findViewById(R.id.activity_main_chkRememberMe);
        chkHardCodeLogin = (CheckBox) findViewById(R.id.activity_main_chkHardCodeLogin);
    }

    private void showMsg(int resourceString) {
        Toast.makeText(getApplicationContext(),resourceString,Toast.LENGTH_SHORT).show();
    }


}
