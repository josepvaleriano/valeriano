package test.valeriano.mx.tarea;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import test.valeriano.mx.tarea.model.ModelUser;
import test.valeriano.mx.tarea.util.PreferenceUtil;

/**
 * Created by luis.valeriano on 23/06/2016.
 */
public class ActivityRegister extends AppCompatActivity implements View.OnClickListener{

    private EditText mUser;
    private EditText mPassword;

    /*Crea la vista del activity Register*/
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findView();
        listenerdButton();
    }

    /*Metodo para escuchar el onclick de la forma*/
    public void onClick(View v) {
        switch (v.getId())        {
            case R.id.activity_register_btnRegisterUser:
                processRegister();
                break;
        }
    }

    /*Método para procesar el registro*/
    private void processRegister() {
        String user = mUser.getText().toString();
        String password = mPassword.getText().toString();
        if(TextUtils.isEmpty(user)) {
            showMsg(R.string.login_empty);
        }
        else if(TextUtils.isEmpty(password)) {
            showMsg(R.string.password_empty);
        }
        else {
            PreferenceUtil util = new PreferenceUtil(getApplicationContext());
            util.saveUser(new ModelUser(user,password));
            finish();
        }
    }

    /*Metódo para dejar activos los listener de los botones*/
    private void listenerdButton() {
        try {
            findViewById(R.id.activity_register_btnRegisterUser).setOnClickListener((View.OnClickListener) this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Metódo para buscar los elementos de la pantalla*/
    private void findView() {
        mUser = (EditText) findViewById(R.id.activity_register_mUserRegister);
        mPassword = (EditText) findViewById(R.id.activity_register_mPasswordRegister);
    }

    /* Metódo para mostrar los mensajes*/
    private void showMsg(int resourceString) {
        Toast.makeText(getApplicationContext(),resourceString,Toast.LENGTH_SHORT).show();
    }


}
