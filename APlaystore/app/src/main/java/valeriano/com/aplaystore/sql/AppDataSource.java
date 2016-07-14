package valeriano.com.aplaystore.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import valeriano.com.aplaystore.model.ModelAplication;

/**
 * Clase para la adminsitración de la base de datos
 * Created by luis.valeriano on 06/07/2016.
 */
public class AppDataSource {
    private final SQLiteDatabase db;

    /*Constructor de la clase*/
    public AppDataSource(Context context) {
        SqlHprData sqlHprData = new SqlHprData(context);
        db = sqlHprData.getWritableDatabase();
    }

    /*
    * Método para guardar una aplicación
    * */
    public void saveAplication(ModelAplication mApp){
        ContentValues cv = new ContentValues();
        cv.put(SqlHprData.C_02NAMEAPLICATION, mApp.nameAplication );
        cv.put(SqlHprData.C_03NAMEDEVELOPER, mApp.nameDeveloper);
        cv.put(SqlHprData.C_04RESOURCEID, mApp.resourceId);
        cv.put(SqlHprData.C_05INSTALED, mApp.instaled);
        cv.put(SqlHprData.C_06DETAIL, mApp.detail);
        db.insert(SqlHprData.TABLE_NAME,null,cv);
    }

    /*
    * Metódo para la eliminación de una aplicación*/
    public void deleteAplication(ModelAplication mApp){
        String ClauseWhere = SqlHprData.C_02NAMEAPLICATION + "=?";
        db.delete(SqlHprData.TABLE_NAME, ClauseWhere,
                new String[]{String.valueOf(mApp.nameAplication)} );

    }


    /*
    * Metódo para la eliminación de una aplicación*/
    public void updateAplication(ModelAplication mApp){
        String ClauseWhere = SqlHprData.C_02NAMEAPLICATION + "=?";
        ContentValues cv = new ContentValues();
        cv.put(SqlHprData.C_06DETAIL, mApp.detail);
        db.update(SqlHprData.TABLE_NAME, cv , ClauseWhere,
                new String[]{String.valueOf(mApp.nameAplication)} );

    }

    /*Metódo para obtener el lsitado de aplicaciones instalas*/
    public List<ModelAplication> getAllAplications(){
        List<ModelAplication> mList = new ArrayList<>();
        Cursor c = db.query(SqlHprData.TABLE_NAME,null,null, null, null, null, null);
        try {
            while (c.moveToNext()){
                String nA = c.getString(c.getColumnIndexOrThrow(SqlHprData.C_02NAMEAPLICATION));
                String nD = c.getString(c.getColumnIndexOrThrow(SqlHprData.C_03NAMEDEVELOPER));
                String rId = c.getString(c.getColumnIndexOrThrow(SqlHprData.C_04RESOURCEID));
                int inst = c.getInt(c.getColumnIndexOrThrow(SqlHprData.C_05INSTALED));
                ModelAplication ma = new ModelAplication(nA, nD, rId, inst);
                ma.detail = c.getString(c.getColumnIndexOrThrow(SqlHprData.C_06DETAIL));
                mList.add(ma);
            }
        }finally{
            c.close();
        }
        return mList;
    }
}
