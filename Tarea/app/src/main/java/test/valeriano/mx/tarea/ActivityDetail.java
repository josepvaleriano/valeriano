package test.valeriano.mx.tarea;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import test.valeriano.mx.tarea.fragment.FragmentList;
import test.valeriano.mx.tarea.fragment.FragmentProfile;
import test.valeriano.mx.tarea.service.ServiceTimer;

/**
 * Created by luis.valeriano on 23/06/2016.
 */
public class ActivityDetail extends AppCompatActivity implements View.OnClickListener {

    private String userName;
    private TextView txtTimer;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int counter = intent.getExtras().getInt("timer");
            txtTimer.setText(String.format("Session lenght %s seconds", counter));
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        userName = getIntent().getExtras().getString("key_user");
        String hello = String.format(getString(R.string.hello), userName);
        //txt.setText(hello);
        findViewById(R.id.activity_content_btnFragment1).setOnClickListener(this);
        findViewById(R.id.activity_content_btnFragment2).setOnClickListener(this);
        txtTimer = (TextView) findViewById(R.id.activity_content_txtTimer);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_content_btnFragment1:
                changeFragmentA();
                break;
            case R.id.activity_content_btnFragment2:
                changeFragmentB();
                break;
        }
    }

    private void changeFragmentB() {
        FragmentProfile f = FragmentProfile.newInstance(" Adios mundo :(");
        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder, new FragmentList()).commit();

    }

    private void changeFragmentA() {
        FragmentProfile f = FragmentProfile.newInstance(userName);
        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder, f).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ServiceTimer.ACTION_SEND_TIMER);
        registerReceiver(broadcastReceiver,filter);
        Log.d(ServiceTimer.TAG,"OnResume, se reinicia boradcast");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(ServiceTimer.TAG,"onPause quitando broadcast");
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(ServiceTimer.TAG,"OnDestroy, terminando servicio");
        stopService(new Intent(getApplicationContext(),ServiceTimer.class));
    }
}
