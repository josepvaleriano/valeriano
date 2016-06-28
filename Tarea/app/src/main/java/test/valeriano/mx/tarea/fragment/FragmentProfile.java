package test.valeriano.mx.tarea.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import test.valeriano.mx.tarea.R;
import test.valeriano.mx.tarea.model.ModelUser;
import test.valeriano.mx.tarea.sql.ItemDataSource;
import test.valeriano.mx.tarea.util.PreferenceUtil;

/**
 * Created by luis.valeriano on 23/06/2016.
 */
public class FragmentProfile extends Fragment {
    private ImageView imgProfile;
    private boolean change = true;
    private ItemDataSource itemDS;




    public static FragmentProfile newInstance(String name, ModelUser modelUser) {
        FragmentProfile f = new FragmentProfile();
        Bundle b = new Bundle();
        b.putString(PreferenceUtil.KEY_USERS, name);
        if (modelUser != null) {
            b.putString(PreferenceUtil.KEY_CREATION, modelUser.getCreation() + "");
            b.putString(PreferenceUtil.KEY_REMEMBER, (modelUser.getRememberId()?"Si":"No"));
            b.putString(PreferenceUtil.KEY_LAST_CONECTION, modelUser.getLast_Conection() + "");
        }
        f.setArguments(b);
        return f;
    }


    public void changeImage(String name){
        String usrInicial = name.toUpperCase().substring(1,1);
        String cadenaControl = "M";
        change = (cadenaControl.compareTo(usrInicial)<0)?true:false;
        imgProfile.setImageResource(change? R.drawable.ic_device_signal_wifi_4_bar: R.drawable.ic_action_settings_voice);
        //change=!change;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        imgProfile = (ImageView) view.findViewById(R.id.frag_prifle_imgProfile);
        TextView txtUsr = (TextView) view.findViewById(R.id.frag_prifle_txtUserFragment);
        TextView txtCreation = (TextView)view.findViewById(R.id.frag_prifle_txtvUserCreation);
        TextView txtRemember = (TextView)view.findViewById(R.id.frag_prifle_txtRememberId);
        TextView txtLast = (TextView)view.findViewById(R.id.frag_prifle_txtLastConection);
        Bundle bundle=getArguments();
        String user ;
        String creation = "User registed :";
        String last =  "Last conection :";
        String remember = "Remember credentials: ";
        if(bundle!=null) {
            user = bundle.getString(PreferenceUtil.KEY_USERS);
            if (!PreferenceUtil.accessLoginHardCode) {
                creation = bundle.getString(PreferenceUtil.KEY_CREATION);
                if (creation != null && !creation.equals("null")) {
                    creation = "User registed :" + creation;
                }
                else
                    creation = "User registed :";
                last = bundle.getString(PreferenceUtil.KEY_LAST_CONECTION);
                if (last != null && !last.equals("null"))
                    last = "Last conection :"+ last;
                else
                    last =  "Last conection :";
                remember = "Remember credentials: " + bundle.getString(PreferenceUtil.KEY_REMEMBER);
            }
        }
        else
            user= "XML inflate";

        txtUsr.setText(user);
        txtCreation.setText(creation);
        txtRemember.setText(remember);
        txtLast.setText(last);
        return view;
    }
}
