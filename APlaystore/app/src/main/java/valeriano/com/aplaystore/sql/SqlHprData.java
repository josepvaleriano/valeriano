package valeriano.com.aplaystore.sql;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Clase para la definición de la base de datos
 * Created by luis.valeriano on 06/07/2016.
 */
public class SqlHprData extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "unam_aplications";
    private final static int DATABASE_VERSION = 1;

    public final static String TABLE_NAME = "aplications";
    public final static String C_01ID = BaseColumns._ID;
    public final static String C_02NAMEAPLICATION = "nameAplication";
    public final static String C_03NAMEDEVELOPER = "nameDeveloper";
    public final static String C_04RESOURCEID = "resourceId";
    public final static String C_05INSTALED = "instaled";

    private static final String CREATE_TABLE = "create table " + TABLE_NAME +
            "(" + C_01ID+" integer primary key autoincrement,"+
            C_02NAMEAPLICATION +" text null, "+
            C_03NAMEDEVELOPER+ " text null, "+
            C_04RESOURCEID + " text null, "+
            C_05INSTALED + " integer null)";

    public SqlHprData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*Método para la creación de la base de datos*/
    @Override
    public void onCreate(SQLiteDatabase sql) {
        sql.beginTransaction();
        try {
            sql.execSQL(CREATE_TABLE);
            sql.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sql.endTransaction();
        }
    }

    /*Método para la actualización de de la base de datos*/
    @Override
    public void onUpgrade(SQLiteDatabase sql, int oldVer, int newVer) {

    }

    /*Método para el borrado de la table*/
    public void deleteTable(SQLiteDatabase sql){
        sql.delete(TABLE_NAME, null, null);
    }
}
