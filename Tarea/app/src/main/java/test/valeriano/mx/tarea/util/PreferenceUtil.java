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
    private static final String KEY_USER = "user_name";
    private static final String KEY_PASSWORD = "user_password";

    public static final String KEY_REMEMBER = "key_remember";
    public static final String KEY_USERS = "key_users";
    public static final String KEY_CREATION = "key_creation";
    public static final String KEY_LAST_CONECTION = "key_last_conection";

    private static Boolean Remember;
    private final SharedPreferences sp;

    /*Route /data/data/test.valeriano.mx.tarea/shared_pref/unam_tarea.xml*/

    public static boolean accessLoginHardCode;

    /*Crea la clase PreferenceUtil con el contexto de la aplicación*/
    public PreferenceUtil(Context context){
        sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
    }

    /*Metodo encarcado de guardar las preferencias del usuario con el objeto @ModelUser*/
    public void saveUser(ModelUser modelUser, Boolean rememberId){
        if(modelUser!=null) {
            sp.edit().putString(KEY_USER, modelUser.userName).apply();
            sp.edit().putString(KEY_PASSWORD, modelUser.password).apply();
            if (rememberId)
                sp.edit().putBoolean(KEY_REMEMBER, modelUser.rememberId).apply();
        }
    }

    /*Metodo encarcado de obtener las preferencias del usuario*/
    public ModelUser getModelUser(){
        String mUser = sp.getString(KEY_USER,null);
        String mPassword = sp.getString(KEY_PASSWORD,null);
        Boolean mRememberId = sp.getBoolean(KEY_REMEMBER,false);
        ModelUser modelUser;
        if(TextUtils.isEmpty(mUser) || TextUtils.isEmpty(mPassword))
            return null;
        else {
            modelUser = new ModelUser(mUser, mPassword);
            modelUser.setRememberId(mRememberId);
        }
        return modelUser;
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
