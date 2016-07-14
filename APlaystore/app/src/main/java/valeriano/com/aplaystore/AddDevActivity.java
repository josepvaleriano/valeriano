package valeriano.com.aplaystore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

import valeriano.com.aplaystore.model.ModelAplication;
import valeriano.com.aplaystore.service.ServiceInstalation;
import valeriano.com.aplaystore.sql.AppDataSource;
import valeriano.com.aplaystore.util.utilPlayStore;

public class AddDevActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edNameApp;
    private EditText ednameDev;
    private EditText edDetailApp;

    private AppDataSource appDS;

    private final String [] linkImg = {
            "https://lh3.googleusercontent.com/ZXPyCtwa9F3P5MQsz7R8CSoOjYenBx2xDvSMEC5NijCEc6gYsrvwJt9hLGNYEb-pCA=w300-rw",
            "https://lh3.googleusercontent.com/kX82PDF39T15PN7dqH5A1oRzS1TAXa8Lze3Xqk2RbgAexzX5opfKf2RzCLBkFO1D1Q4=w300-rw",
            "https://lh6.ggpht.com/sxvBxafXMzbEPeNR-Ib3IFE0Vsm0Q2uXnm37-HXi9OlQvNhlLu0KInBPFPG22uOZwfk=w300-rw",
            "https://lh6.ggpht.com/mp86vbELnqLi2FzvhiKdPX31_oiTRLNyeK8x4IIrbF5eD1D5RdnVwjQP0hwMNR_JdA=w300-rw",
            "https://lh3.googleusercontent.com/ZZPdzvlpK9r_Df9C3M7j1rNRi7hhHRvPhlklJ3lfi5jk86Jd1s0Y5wcQ1QgbVaAP5Q=w300-rw",
            "https://lh3.ggpht.com/O0aW5qsyCkR2i7Bu-jUU1b5BWA_NygJ6ui4MgaAvL7gfqvVWqkOBscDaq4pn-vkwByUx=w300-rw",
            "https://lh5.ggpht.com/0HTaiRyUraX2o4B0IbW887TuLfZ70eNWOaOY9_xEiRck35V44BYXR5brEOy9MBUWBjo=w300-rw",
            "https://lh3.googleusercontent.com/SNjS81FG3okdMTief0vJu4BNTA03D7es5yRbfVUE2KMgYe5loR7t77bMX8rbG4gVN2TX=w300-rw",
            "https://lh3.googleusercontent.com/2n0hceUvwwEW5maNUzRk4ucXgH_KIVBK0u_8eX_znW-DnnXTbx7MablS3po2_-LYBtFL=w300-rw",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Biblioteca_Central_UNAM_M%C3%A9xico.jpg/250px-Biblioteca_Central_UNAM_M%C3%A9xico.jpg",
            "https://www.unam.mx/sites/default/files/images/menu/library-345273_1280.jpg"

    };

    /*Método creador de la actividad donde se establecen los elementos que contiene la forma*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appDS = new AppDataSource(getApplicationContext());
        setContentView(R.layout.activity_add_dev);
        edNameApp = (EditText) findViewById(R.id.activityAddDev_etNameAplication);
        ednameDev = (EditText) findViewById(R.id.activityAddDev_etNameDev);
        edDetailApp = (EditText) findViewById(R.id.activityAddDev_etDetail);
        findViewById(R.id.activityAddDev_btnSave).setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle( R.string.app_name );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle( R.string.menu_agregarApp);

    }

    /*Método @onClick encargado de procesar el boton de guardar*/
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activityAddDev_btnSave:
                saveApplication();
        }

    }

    /*Método encargado de salvar la la aplicación*/
    private void saveApplication() {
        String nApp = edNameApp.getText().toString().trim();
        String nDev = ednameDev.getText().toString().trim();
        String detail = edDetailApp.getText().toString().trim();

        if (nApp.length()==0) {
            showMsg(R.string.activityDataRequiere);
            edNameApp.requestFocus();
        }
        else if (nDev.length()==0) {
            showMsg(R.string.activityDataRequiere);
            ednameDev.requestFocus();
        }
        else if (detail.length()==0) {
            showMsg(R.string.activityDataRequiere);
            edDetailApp.requestFocus();
        }
        else{
            ModelAplication mApp = new ModelAplication(nApp, nDev);
            Random r = new Random();
            int pos = r.nextInt(linkImg.length);
            mApp.setResourceId(linkImg[pos]);
            mApp.setInstaled(Integer.parseInt(utilPlayStore.STATUS_DONWLOAD));
            mApp.setDetail(detail);

            Intent intent = new Intent(getApplicationContext(), ServiceInstalation.class);
            //intent.putExtra(utilPlayStore.KEY_ID, utilPlayStore.STATUS_DONWLOAD);
            intent.putExtra(utilPlayStore.KEY_NAMEAPP, mApp.nameAplication);
            intent.putExtra(utilPlayStore.KEY_NAMEDEV, mApp.nameDeveloper);
            intent.putExtra(utilPlayStore.KEY_RESOURCEID, mApp.resourceId);
            intent.putExtra(utilPlayStore.KEY_STATUS, mApp.instaled);
            intent.putExtra(utilPlayStore.KEY_DETAIL, mApp.detail);
            startService(intent);
            appDS.saveAplication(mApp);
            intent = new Intent (getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    /*Método para escuchar los eventos del toolbar*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*Método para mostrar mensaje*/
    private void showMsg(int resourceString) {
        Toast.makeText(getApplicationContext(),resourceString,Toast.LENGTH_SHORT).show();
    }
}
