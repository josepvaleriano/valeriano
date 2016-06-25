package test.valeriano.mx.empaquetado.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import test.valeriano.mx.empaquetado.R;
import test.valeriano.mx.empaquetado.adapter.AdapterItemList;
import test.valeriano.mx.empaquetado.model.ModelItem;

/**
 * Created by luis.valeriano on 18/06/2016.
 */
public class FragmentList extends Fragment{

    private ListView listView;
    private List<ModelItem> array = new ArrayList<>();
    private int counter;
    private boolean isWifi;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container, false);
        listView = (ListView) view.findViewById(R.id.frmListItems);
        listView.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    AdapterItemList adapter= (AdapterItemList) parent.getAdapter();
                    ModelItem modelItem =adapter.getItem(position);
                    ModelItem modelItem2 = array.get(position);
                    //aqui generar otro action activity
                    FragmentProfile f = FragmentProfile.newInstance(modelItem2.item);
                    getFragmentManager().beginTransaction().replace(R.id.fragmentHolder,f).commit();
                    //Toast.makeText(getActivity(),modelItem2.item,Toast.LENGTH_SHORT).show();
                }
            }
        );
        final EditText mItemsText = (EditText) view.findViewById(R.id.frmItemText);
        view.findViewById(R.id.frmBtnAddItem);
        view.setOnClickListener(
            new View.OnClickListener() {
                public void onClick(View v) {
                    String itemData = mItemsText.getText().toString();
                    if(!TextUtils.isEmpty(itemData)){
                        ModelItem item =new ModelItem();
                        item.item = itemData;
                        item.id  = "Description más counter"+counter;
                        item.resourceId=isWifi?R.drawable.ic_device_signal_wifi_4_bar:R.drawable.ic_action_settings_voice;
                        array.add(item);
                        listView.setAdapter(new AdapterItemList(getActivity(),array));
                        isWifi=!isWifi;
                        counter++;
                        mItemsText.setText("");
                    }

                }
                }
        );

        return view;
    }
}
