package test.valeriano.mx.tarea;

import android.content.Intent;
import android.os.Handler;
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

import test.valeriano.mx.tarea.model.ModelUser;
import test.valeriano.mx.tarea.service.ServiceTimer;
import test.valeriano.mx.tarea.sql.ItemDataSource;
import test.valeriano.mx.tarea.util.PreferenceUtil;

/*Constructor principal, debe extender de @AppCompatActivity para tener funcionalidades mejoradas
 de android, además estender de @OnClickListener para escuchar los eventos*/
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*Declaración de variables*/
    private EditText mUser;
    private EditText mPassword;
    private CheckBox chkRememberMe;
    private CheckBox chkHardCodeLogin;
    private Button btnRegistrar;
    private View loading;
    private String [] usr_to_login = {"jose", "luis", "unam", "javier", "Zenkun"};
    private String [] pwd_to_login = {"unam"};
    private PreferenceUtil preferenceUtil;

    private ItemDataSource itemDS;

    /* Actividad principal principal onCreate()*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemDS = new ItemDataSource(getBaseContext());
        findView();
        findViewById(R.id.activity_main_btnLogin).setOnClickListener(this);
        findViewById(R.id.activity_main_btnRegistrerLogin).setOnClickListener(this);
        preferenceUtil= new PreferenceUtil(getApplicationContext());
        chkRememberMe.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        preferenceUtil.setAccessLoginHardCode(false);
                        if (b){
                            chkHardCodeLogin.setEnabled(false);
                            btnRegistrar.setEnabled(true);
                        }
                        else{
                            chkHardCodeLogin.setEnabled(true);
                        }
                        Log.d(ServiceTimer.TAG,"Checkeo es: "+b);
                    }
                }
        );
        chkHardCodeLogin.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b){
                            chkRememberMe.setEnabled(false);
                            btnRegistrar.setEnabled(false);
                            mUser.setText("");
                            mPassword.setText("");
                            preferenceUtil.setAccessLoginHardCode(true);
                        }
                        else{
                            chkRememberMe.setEnabled(true);
                            btnRegistrar.setEnabled(true);
                            preferenceUtil.setAccessLoginHardCode(false);
                        }
                        Log.d(ServiceTimer.TAG,"Checkeo es: "+b);
                    }
                }
        );
        verifiyRemember();
    }

    private void verifiyRemember() {
        ModelUser modelUser = preferenceUtil.getModelUser();
        if (modelUser!=null && !modelUser.userName.isEmpty() && modelUser.rememberId){
            mUser.setText(modelUser.getUserName());
            mPassword.setText(modelUser.getPassword());
            chkRememberMe.setChecked(modelUser.getRememberId());
        }
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
        try {
            if (TextUtils.isEmpty(user)) {
                showMsg(R.string.login_empty);
            } else if (TextUtils.isEmpty(password)) {
                showMsg(R.string.password_empty);
            } else {
                if (preferenceUtil.getAccessLoginHardCode()) {
                    validateLogin(user, password);
                } else {
                    validateLoginPreferens(user, password);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        loading.setVisibility(View.INVISIBLE);
    }

    private void validateLoginPreferens(final String user, final String password) {
        new Handler().postDelayed(
            new Runnable() {
              @Override
              public void run() {
                  loading.setVisibility(View.GONE);
                  ModelUser modelUser = preferenceUtil.getModelUser();
                  if(modelUser==null){
                    showMsg(R.string.need_registry);
                  }else if (user.equals(modelUser.userName) && password.equals(modelUser.password)) {
                      Toast.makeText(getApplicationContext(), R.string.pass_login, Toast.LENGTH_SHORT).show();
                      ModelUser dbModelUser = itemDS.getDataUser(user);
                      dbModelUser.setRememberId(chkRememberMe.isChecked());
                      /*Se tenvía true para salvar preferencia de recordar*/
                      preferenceUtil.saveUser(modelUser, true);
                      Intent intent = new Intent(getApplicationContext(), ActivityDetail.class);
                      intent.putExtra(PreferenceUtil.KEY_USERS, user);
                      intent.putExtra(PreferenceUtil.KEY_REMEMBER, dbModelUser.getRememberId() );
                      if (dbModelUser.getCreation()!=null)
                        intent.putExtra(PreferenceUtil.KEY_CREATION, dbModelUser.getCreation()+"");
                      if (dbModelUser.getLast_Conection()!=null)
                        intent.putExtra(PreferenceUtil.KEY_LAST_CONECTION, dbModelUser.getLast_Conection()+"");
                      startActivity(intent);
                      startService(new Intent(getApplicationContext(), ServiceTimer.class));

                  }
                  else
                      showMsg(R.string.need_registry);
              }
          },1000*1);
    }

    /*Una vez validado guarda el usuario*/
    private void validateLogin(String user, String password) {
        boolean ingreso = false;
        for (int i=0; i<usr_to_login.length; i++) {
            if (user.equals(usr_to_login[i]) && password.equals(pwd_to_login[0])) {
                Toast.makeText(getApplicationContext(), R.string.pass_login, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ActivityDetail.class);
                intent.putExtra(PreferenceUtil.KEY_USERS, user);
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



    /*Metódo para buscar los elementos de la pantalla*/
    private void findView() {
        mUser = (EditText) findViewById(R.id.activity_main_user);
        mPassword = (EditText) findViewById(R.id.activity_main_password);
        loading = findViewById(R.id.progress);
        chkRememberMe = (CheckBox) findViewById(R.id.activity_main_chkRememberMe);
        chkHardCodeLogin = (CheckBox) findViewById(R.id.activity_main_chkHardCodeLogin);
        btnRegistrar = (Button) findViewById(R.id.activity_main_btnRegistrerLogin);
    }

    private void showMsg(int resourceString) {
        Toast.makeText(getApplicationContext(),resourceString,Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        //sabe last extado de conexión
        super.onDestroy();
    }
}
