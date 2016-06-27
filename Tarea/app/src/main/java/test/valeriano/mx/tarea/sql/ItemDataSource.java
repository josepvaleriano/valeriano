package test.valeriano.mx.tarea.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import test.valeriano.mx.tarea.model.ModelItem;
import test.valeriano.mx.tarea.model.ModelUser;

/**
 * Clase para la el manjeo de las operaciones con base de datos de las
 * 2 tablas usuarios e items
 * Created by luis.valeriano on 26/06/2016.
 */
public class ItemDataSource {
    private final SQLiteDatabase db;

    /*
    * Creación de la base de datos
    **/
    public ItemDataSource(Context context) {
        sqlHprItemsData sqlHelper = new sqlHprItemsData(context);
        db = sqlHelper.getWritableDatabase();
    }

    /*Método para salvar un elemento de la lista*/
    public void saveItem(ModelItem modelItem) {
        ContentValues cv = new ContentValues();
        cv.put(sqlHprItemsData.C_ITEM_02NAME, modelItem.item);
        cv.put(sqlHprItemsData.C_ITEM_03DESC, modelItem.id);
        cv.put(sqlHprItemsData.C_ITEM_04RESOURCE, modelItem.resourceId);
        db.insert(sqlHprItemsData.TABLE_NAME_ITEM_LIST, null, cv);
    }

    /*Método para borrar un elemento de la lista*/
    public void deleteItem(ModelItem modelItem){
        String where = sqlHprItemsData.C_ITEM_01ID + "=?";
        db.delete(sqlHprItemsData.TABLE_NAME_ITEM_LIST, where,  new String[]{String.valueOf(modelItem.id )});
    }

    /*Método para salvar los datos de un usuario*/
    public void saveUser (ModelUser modelUser){
        ContentValues cv = new ContentValues();
        cv.put(sqlHprItemsData.C_USERS_02NAME, modelUser.userName);
        cv.put(sqlHprItemsData.C_USERS_03PASSWD, modelUser.password);
        cv.put(sqlHprItemsData.C_USERS_05CREATION, getDateTime() );
        db.insert(sqlHprItemsData.TABLE_NAME_USERS, null, cv);
    }

    /*Método para la obtención de todos los elementos de la lista*/
    public List<ModelItem> getAllItems(){
        List<ModelItem> modelItemList = new ArrayList<>();
        Cursor c = db.query(sqlHprItemsData.TABLE_NAME_ITEM_LIST,
                null,null, null, null,null,null,null);
        try {
            while (c.moveToNext()) {
                String itemName = c.getString(c.getColumnIndexOrThrow(sqlHprItemsData.C_ITEM_02NAME));
                String desc = c.getString(c.getColumnIndexOrThrow(sqlHprItemsData.C_ITEM_03DESC));
                int resourceId = c.getInt(c.getColumnIndexOrThrow(sqlHprItemsData.C_ITEM_04RESOURCE));
                ModelItem mI = new ModelItem();
                mI.resourceId = resourceId;
                mI.id = desc;
                mI.item = itemName;
                modelItemList.add(mI);
            }
        }
        finally{
            c.close();
            //db.close();
        }
        return modelItemList;
    }

    /*Método para la obtención de datos del usuario*/
    public ModelUser getDataUser(String user){
        ModelUser modelUser = new ModelUser();
        String[] campos = new String[] {sqlHprItemsData.C_USERS_04LAST_CONECTION };
        String[] args = new String[] {user};
        String where = sqlHprItemsData.C_ITEM_02NAME + "=?";

        Cursor c = db.query(sqlHprItemsData.TABLE_NAME_USERS, campos,
                where, args, null, null, null, null);
        try {
            while (c.moveToNext()){
                String usr = c.getString(c.getColumnIndexOrThrow(sqlHprItemsData.C_USERS_02NAME));
                String pwd = c.getString(c.getColumnIndexOrThrow(sqlHprItemsData.C_USERS_03PASSWD));
                Date dateLastConection = new Date(c.getLong(2)*1000);
                Date dateCreation = new Date(c.getLong(3)*1000);
                modelUser.userName = usr;
                modelUser.password = pwd;
                modelUser.last_Conection = dateLastConection;
                modelUser.creation = dateCreation;
            }
        }finally {
            c.close();
        }
        return modelUser;
    }

    /*Método para la obtención de datos del usuario*/
    public int updateLastConetion(ModelUser modelUser){
        ContentValues cv = new ContentValues();
        cv.put(sqlHprItemsData.C_USERS_04LAST_CONECTION, getDateTime());
        String where = sqlHprItemsData.C_ITEM_02NAME + "=?";
        String[] args = new String[] { modelUser.userName};
        return db.update(sqlHprItemsData.TABLE_NAME_USERS, cv, where, args);
    }

    /*Método privado para obtener la fecha en formato yyyy-MM-dd HH:mm:ss*/
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}


