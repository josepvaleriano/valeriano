package test.valeriano.mx.tarea;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import test.valeriano.mx.tarea.fragment.FragmentList;
import test.valeriano.mx.tarea.fragment.FragmentProfile;

/**
 * Created by luis.valeriano on 23/06/2016.
 */
public class ActivityDetail extends AppCompatActivity implements View.OnClickListener  {

    private String userName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        userName=getIntent().getExtras().getString("key_user");
        String hello = String.format(getString(R.string.hello),userName);
        //txt.setText(hello);
        findViewById(R.id.activity_content_btnFragment1).setOnClickListener(this);
        findViewById(R.id.activity_content_btnFragment2).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
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
        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder,new FragmentList()).commit();

    }

    private void changeFragmentA() {
        FragmentProfile f = FragmentProfile.newInstance(userName);
        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder,f).commit();
    }
}
