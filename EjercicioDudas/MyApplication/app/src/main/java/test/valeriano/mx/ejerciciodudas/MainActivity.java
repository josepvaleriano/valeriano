package test.valeriano.mx.ejerciciodudas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_SECOND_ACTIVITY = 1;
    private TextView txtUno;
    private TextView txtDos;
    private CheckBox chkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUno= (TextView) findViewById(R.id.activity_main_txtUno);
        txtDos = (TextView) findViewById(R.id.activity_main_txtDos);
        chkBox = (CheckBox) findViewById(R.id.activity_main_chkBox);
        findViewById(R.id.activity_main_btnEditar).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            ;
            /*
            R.id.activity_main_btnEditar:
            startActivityForResult(
                    new Intent(getApplicationContext(),
                    EditActivity.class),
                    REQUEST_CODE_SECOND_ACTIVITY);
*/
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (REQUEST_CODE_SECOND_ACTIVITY == requestCode
                && resultCode == RESULT_OK){
            String txtBack1 = data.getExtras().getString("key_txt1");
            String txtBack2 = data.getExtras().getString("key_txt2");
            String chkBox = data.getExtras().getString("key_chkBox");

            txtUno.setText(txtBack1);
            txtDos.setText(txtBack1);


        }
        else
            super.onActivityResult(requestCode, resultCode, data);
    }
}

