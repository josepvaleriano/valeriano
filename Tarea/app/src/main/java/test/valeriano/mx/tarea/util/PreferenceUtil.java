package test.valeriano.mx.tarea.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import test.valeriano.mx.tarea.model.ModelUser;

/**
 * Created by luis.valeriano on 23/06/2016.
 */
public class PreferenceUtil {

    /*Declaración de variables*/
    private static final String FILE_NAME ="unam_tarea";
    private final SharedPreferences sp;
    private boolean accessLoginHardCode;

    /*Crea la clase PreferenceUtil con el contexto de la aplicación*/
    public PreferenceUtil(Context context){
        sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
    }

    /*Metodo encarcado de guardar las preferencias del usuario con el objeto @ModelUser*/
    public void saveUser(ModelUser modelUser){
        if(modelUser!=null) {
            sp.edit().putString("user_name", modelUser.userName).apply();
            sp.edit().putString("user_password", modelUser.password).apply();
        }
    }

    /*Metodo encarcado de obtener las preferencias del usuario*/
    public ModelUser getUser(){
        String mUser=sp.getString("user_name",null);
        String mPassword=sp.getString("user_password",null);
        if(TextUtils.isEmpty(mUser) || TextUtils.isEmpty(mPassword))
            return null;
        return new ModelUser(mUser,mPassword);
    }

    /*Establece el tipo de acceso*/
    public void setAccessLoginHardCode(Boolean access){
        this.accessLoginHardCode = access;
    }

    /*Obtiene el tipo de acceso hardcode true funciona con 4 login compilados*/
    public Boolean getAccessLoginHardCode(){
        return this.accessLoginHardCode;
    }
}
