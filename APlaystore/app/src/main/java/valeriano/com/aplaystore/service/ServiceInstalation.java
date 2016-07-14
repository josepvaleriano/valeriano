package valeriano.com.aplaystore.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import valeriano.com.aplaystore.MainActivity;
import valeriano.com.aplaystore.R;
import valeriano.com.aplaystore.model.ModelAplication;
import valeriano.com.aplaystore.util.utilPlayStore;

/**
 * Servicio para manipular la instación de las aplicaciones
 * Created by luis.valeriano on 06/07/2016.
 */
public class ServiceInstalation extends Service {

    public static final String TAG = "unam_tag_play";
    private int id;
    private final int timeProcessing = 6;
    private MyAsyncTask myAsyncTask;
    private ModelAplication modelAplication;

    private String actionApp;
    private String nameApp;
    private String nameDev;

    /*Método onBlind para controlar el servicio*/
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /*Método @onCreate para crear el servicio*/
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"Oncreate servicio" + id);
    }

    /*Método @onDestroy para destruir el servicio*/
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy servicio" + id);
    }

    /*Método @onStartCommand para manjera el servicio*/
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        modelAplication = new ModelAplication();
        if (intent.getExtras()!= null ){
            if (intent.getExtras().containsKey(utilPlayStore.KEY_ID))
                id = intent.getExtras().getInt(utilPlayStore.KEY_ID);
            if (intent.getExtras().containsKey(utilPlayStore.KEY_NAMEAPP))
                modelAplication.nameAplication = intent.getExtras().getString(utilPlayStore.KEY_NAMEAPP);
            if (intent.getExtras().containsKey(utilPlayStore.KEY_NAMEDEV))
                modelAplication.nameDeveloper = intent.getExtras().getString(utilPlayStore.KEY_NAMEDEV);
            if (intent.getExtras().containsKey(utilPlayStore.KEY_RESOURCEID))
                modelAplication.resourceId = intent.getExtras().getString(utilPlayStore.KEY_RESOURCEID);
            if (intent.getExtras().containsKey(utilPlayStore.KEY_STATUS))
                modelAplication.instaled = intent.getExtras().getInt(utilPlayStore.KEY_STATUS);
            if (intent.getExtras().containsKey(utilPlayStore.KEY_DETAIL))
                modelAplication.detail= intent.getExtras().getString(utilPlayStore.KEY_DETAIL);
        }
        if (myAsyncTask == null){
            myAsyncTask = new MyAsyncTask();
            myAsyncTask.execute();
        }
        Log.d(TAG,"onStartCommand servicio " + id);
        return START_STICKY;
    }

    /*Método @MyAsyncTask para maneajar la sincronia del servicio*/
    private class MyAsyncTask extends AsyncTask <Integer, Integer, Boolean> {

        private NotificationCompat.Builder mNotif;

        /*Método @onPreExecute para preparar el servicio */
        protected void onPreExecute() {
            actionApp =  (modelAplication.instaled==1)?
            getApplicationContext().getString(R.string.serviveActionDownload):
            getApplicationContext().getString(R.string.serviveActionUploading);
            nameApp = modelAplication.nameAplication;
            nameDev = modelAplication.nameDeveloper;

            mNotif = new NotificationCompat.Builder(getApplicationContext())
                    .setContentTitle(actionApp)
                    .setContentText(nameApp)
                    .setSubText(nameDev)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.presence_video_away))
                    .setSmallIcon(android.R.drawable.ic_dialog_email);
        }


        /*Método donde se deja la descarga en back*/
        @Override
        protected Boolean doInBackground(Integer... integers) {

            for (int i = 0; i < timeProcessing; i++) {
                publishProgress(i);
                try {
                    Thread.sleep(1000 * 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            Log.d(TAG,"doInBackground servicio" + id);
            return true;
        }


        /*Método para realizar el progreso de la barra*/
        @Override
        protected void onProgressUpdate(Integer... values) {

            mNotif.setProgress(timeProcessing, values[0],false);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(id, mNotif.build());
            Log.d(TAG,"onProgressUpdate servicio" + id);
        }

        /*Método para mostrar el resultado de la acción*/
        @Override
        protected void onPostExecute(Boolean result) {
            if (result){
                mNotif.setProgress(0,0,false);
                mNotif.setContentTitle(getApplicationContext().getString(R.string.contentTitle));
                mNotif.setContentText(getApplicationContext().getString(R.string.contentText));
                mNotif.setContentInfo(getApplicationContext().getString(R.string.contentInfo));
                mNotif.setAutoCancel(true);
                NotificationCompat.Style s = new NotificationCompat.BigTextStyle()
                        .bigText(getApplicationContext().getString(R.string.tanksInstaled ));
                mNotif.setStyle(s);
                PendingIntent pendingIntent =PendingIntent.
                        getActivity(getApplicationContext(),
                                1,new Intent(getApplicationContext(),
                                        MainActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
                mNotif.setContentIntent(pendingIntent);
                NotificationManager manager  = (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(id,mNotif.build());
            }
            myAsyncTask = null;
            stopSelf();
            Log.d(TAG,"onPostExecute servicio" + id);
        }
    }




}
