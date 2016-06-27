package test.valeriano.mx.tarea.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import test.valeriano.mx.tarea.service.ServiceTimer;

/**
 * Clase para manejar las operaciones hacia la base de datos @DATABASE_NAME
 *
 * Created by luis.valeriano on 26/06/2016.
 */
public class sqlHprItemsData extends SQLiteOpenHelper{
    private final static String DATABASE_NAME = "unam_android";
    private final static int DATABASE_VERSION = 1;

    public final static String TABLE_NAME_ITEM_LIST = "item_table";
    public final static String C_ITEM_01ID = BaseColumns._ID;
    public final static String C_ITEM_02NAME = "name";
    public final static String C_ITEM_03DESC = "description";
    public final static String C_ITEM_04RESOURCE = "resource";

    public final static String TABLE_NAME_USERS = "item_table";
    public final static String C_USERS_01ID = BaseColumns._ID;
    public final static String C_USERS_02NAME = "name";
    public final static String C_USERS_03PASSWD = "password";
    public final static String C_USERS_04LAST_CONECTION = "last_conection";
    public final static String C_USERS_05CREATION = "creation";

    private static final String CREATE_TABLE_ITEM_LIST = "create table " + TABLE_NAME_ITEM_LIST +
            "(" + C_ITEM_01ID+" integer primary key autoincrement,"+
            C_ITEM_02NAME +" text not null,"+
            C_ITEM_03DESC + " text not null,"+
            C_ITEM_04RESOURCE +" integer not null)";


    private static final String CREATE_TABLE_USERS = "create table " + TABLE_NAME_USERS +
            "(" + C_USERS_01ID+" integer primary key autoincrement,"+
            C_USERS_02NAME +" text not null,"+
            C_USERS_03PASSWD + " text not null,"+
            C_USERS_04LAST_CONECTION +" datetime null," +
            C_USERS_05CREATION +" datetime not null DEFAULT CURRENT_TIMESTAMP )";


    /*Método para crear la base de datos*/
    public sqlHprItemsData(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*Método para crear las tablas de la base de datos*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(CREATE_TABLE_ITEM_LIST);
            db.execSQL(CREATE_TABLE_USERS);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    /*Método para actualizar la versión de la base de datos*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(ServiceTimer.TAG,"OnUpgrade SQL from "+oldVersion+ " to "+newVersion);
    }
}
