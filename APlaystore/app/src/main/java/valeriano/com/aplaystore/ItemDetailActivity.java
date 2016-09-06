package valeriano.com.aplaystore;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import valeriano.com.aplaystore.model.ModelAplication;
import valeriano.com.aplaystore.util.utilPlayStore;

public class ItemDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int REQUEST_CODE_SECOND_ACTIVITY = 1;
    private ImageView imageView;
    private TextView txNameApp;
    private TextView txNameDev;
    private TextView txInstaled;
    private TextView txtDetail;
    private LinearLayout lytBotones;
    private LinearLayout lytSave;
    private ModelAplication mApp;
    private String detail_activity;

    private String DETAIL_ACCION_EXECUTE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        setTitle(R.string.activityAddDev_etDetail);

        detail_activity = getIntent().getExtras().getString(utilPlayStore.KEY_ACTION);

        TextView txtVwTitleApp = (TextView) findViewById(R.id.itemDetail_txtTitleAplication);
        TextView txtVwDevep = (TextView) findViewById(R.id.itemDetail_txtDesarrollador);
        ImageView img = (ImageView) findViewById(R.id.itemDetail_rowImagenView);
        TextView txtInstal = (TextView) findViewById(R.id.itemDetail_txtInstalada);
        TextView txtDetail = (TextView) findViewById(R.id.itemDetail_txtDetail);
        lytBotones = (LinearLayout) findViewById(R.id.itemDetail_LytBotones);
        lytSave = (LinearLayout) findViewById(R.id.itemDetail_LytSave);

        mApp = new ModelAplication();
        mApp.nameAplication = getIntent().getExtras().getString(utilPlayStore.KEY_NAMEAPP );
        mApp.nameDeveloper = getIntent().getExtras().getString(utilPlayStore.KEY_NAMEDEV);
        mApp.resourceId = getIntent().getExtras().getString(utilPlayStore.KEY_RESOURCEID);
        mApp.detail = getIntent().getExtras().getString(utilPlayStore.KEY_DETAIL);
        String status = getIntent().getExtras().getString(utilPlayStore.KEY_STATUS);
        if (status!= null) {
            mApp.instaled = Integer.parseInt(status);
            txtInstal.setText(mApp.instaled + "");
        }
        txtVwTitleApp.setText(mApp.nameAplication);
        txtVwDevep.setText(mApp.nameDeveloper);
        if ( mApp.detail !=null)
            txtDetail.setText(mApp.detail);
        Picasso.with( getApplicationContext()).load(mApp.resourceId).into(img);
        /*Creación del menu*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        //getSupportActionBar().setIcon(android.R.drawable.sym_def_app_icon);
        getSupportActionBar().setHomeButtonEnabled(true);


        if (detail_activity.equals("D")) {
            findViewById(R.id.itemDetail_btnUninstall).setOnClickListener(this);
            findViewById(R.id.itemDetail_btnOpen).setOnClickListener(this);
            findViewById(R.id.itemDetail_btnUpdate).setOnClickListener(this);
            lytSave.setActivated(false);
            lytSave.setVisibility(View.GONE);
            lytSave.setEnabled(false);
            lytSave.setSelected(false);
            getSupportActionBar().setSubtitle( R.string.menu_txtdetal);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        else if (detail_activity.equals("E")) {
            findViewById(R.id.itemDetail_btnSave).setOnClickListener(this);
            lytBotones.setActivated(false);
            lytBotones.setVisibility(View.GONE);
            lytBotones.setEnabled(false);
            lytBotones.setSelected(false);
            getSupportActionBar().setSubtitle( R.string.menu_editApp);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (detail_activity.equals("D"))
            getMenuInflater().inflate(R.menu.menu_editapp, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.itemDetail_btnUninstall:
                doUninstall();
            break;
            case R.id.itemDetail_btnOpen:
                doOpen();
                break;
            case R.id.itemDetail_btnUpdate:
                doUpdate();
                break;
            case R.id.itemDetail_btnSave:
                doSaveEdit();
                break;
        }

    }

    private void doSaveEdit() {
        Toast.makeText(getApplicationContext(), getString(R.string.saveEditApp) ,
                Toast.LENGTH_SHORT).show();
    }

    private void doUpdate() {
        Toast.makeText(getApplicationContext(), getString(R.string.updateAplication) ,
                Toast.LENGTH_SHORT).show();
    }

    private void doOpen() {
        Toast.makeText(getApplicationContext(), getString(R.string.openAplication) ,
                Toast.LENGTH_SHORT).show();
    }

    private void doUninstall() {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(android.R.string.dialog_alert_title);
        alertDialog.setMessage(
                getApplicationContext().getResources().getString(R.string.uninstall));
        alertDialog.setButton(
                DialogInterface.BUTTON_POSITIVE,
                getApplicationContext().getResources().getString(android.R.string.yes),
            new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getApplicationContext(),getString(R.string.unInstaling) ,
                            Toast.LENGTH_SHORT).show();
                }
            });
        alertDialog.setButton(
                DialogInterface.BUTTON_NEGATIVE,
                getApplicationContext().getResources().getString(android.R.string.no),
            new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getApplicationContext(),"No hace nada",Toast.LENGTH_SHORT).show();
                    alertDialog.cancel();
                }
            });
        alertDialog.show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_editApp:
                Intent intent = new Intent(getApplicationContext(), ItemDetailActivity.class);
                DETAIL_ACCION_EXECUTE = "E";
                intent.putExtra(utilPlayStore.KEY_ACTION, DETAIL_ACCION_EXECUTE);
                intent.putExtra(utilPlayStore.KEY_NAMEAPP, mApp.nameAplication );
                intent.putExtra(utilPlayStore.KEY_NAMEDEV, mApp.nameDeveloper);
                intent.putExtra(utilPlayStore.KEY_RESOURCEID, mApp.resourceId);
                intent.putExtra(utilPlayStore.KEY_STATUS, mApp.instaled + "");
                intent.putExtra(utilPlayStore.KEY_DETAIL, mApp.detail);
                startActivityForResult(intent, REQUEST_CODE_SECOND_ACTIVITY);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
