package test.valeriano.mx.empaquetado;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import test.valeriano.mx.empaquetado.fragment.FragmentList;
import test.valeriano.mx.empaquetado.fragment.FragmentProfile;

/**
 * Created by luis.valeriano on 18/06/2016.
 */
public class ActivityContent extends AppCompatActivity implements View.OnClickListener {

    private String userName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        userName=getIntent().getExtras().getString("key_user");
        String hello = String.format(getString(R.string.hello),userName);

        findViewById(R.id.btnFragment1).setOnClickListener(this);
        findViewById(R.id.btnFragment2).setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnFragment1:
                changeFragmentA();
                Log.d(R.id.btnFragment1+"", "si paso por aqui");
                break;
            case R.id.btnFragment2:
                changeFragmentB();
                Log.d(R.id.btnFragment2+"", "si paso por aqui");
                break;
        }
    }

    private void changeFragmentB() {
        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder,new FragmentList()).commit();
    }

    private void changeFragmentA() {
        FragmentProfile f = FragmentProfile.newInstance(userName);
        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder,f).commit();
    }
}
