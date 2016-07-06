package valeriano.com.aplaystore.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import valeriano.com.aplaystore.model.ModelAplication;

/**
 * Clase para la adminsitración de la base de datos
 * Created by luis.valeriano on 06/07/2016.
 */
public class DataSource {
    private final SQLiteDatabase db;

    /*Constructor de la clase*/
    public DataSource(Context context) {
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
        db.insert(SqlHprData.TABLE_NAME,null,cv);
    }

    /*
    * Metódo para la eliminación de una aplicación*/
    public void deleteAplication(ModelAplication mApp){
        String ClauseWhere = SqlHprData.C_02NAMEAPLICATION + "=?";
        db.delete(SqlHprData.TABLE_NAME, ClauseWhere,
                new String[]{String.valueOf(mApp.nameAplication)} );

    }

    public List<ModelAplication> getAllAplications(){
        List<ModelAplication> mList = new ArrayList<>();
        Cursor c = db.query(SqlHprData.TABLE_NAME,null,null, null, null, null, null);
        try {
            while (c.moveToNext()){
                String nA = c.getString(c.getColumnIndexOrThrow(SqlHprData.C_02NAMEAPLICATION));
                String nD = c.getString(c.getColumnIndexOrThrow(SqlHprData.C_03NAMEDEVELOPER));
                int rId = c.getInt(c.getColumnIndexOrThrow(SqlHprData.C_04RESOURCEID));
                int inst = c.getInt(c.getColumnIndexOrThrow(SqlHprData.C_05INSTALED));
                ModelAplication ma = new ModelAplication(nA, nD, rId, inst);
                mList.add(ma);
            }
        }finally{
            c.close();
        }
        return mList;
    }
}
