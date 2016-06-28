package test.valeriano.mx.tarea.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
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

import test.valeriano.mx.tarea.R;
import test.valeriano.mx.tarea.adapters.AdapterItemList;
import test.valeriano.mx.tarea.model.ModelItem;
import test.valeriano.mx.tarea.sql.ItemDataSource;

/**
 * Created by luis.valeriano on 23/06/2016.
 */
public class FragmentList extends Fragment {
    private ListView listView;
    //private List<ModelItem> array = new ArrayList<>();
    private int counter;
    private boolean isWifi;
    private ItemDataSource itemDS;

    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        itemDS = new ItemDataSource(getActivity());
    }

    /* Crea la vista del fragmento de lista*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        listView = (ListView) view.findViewById(R.id.frag_list_listItems);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AdapterItemList adapter = (AdapterItemList) parent.getAdapter();
                ModelItem modelItem = adapter.getItem(position);
                //ModelItem modelItem2 = array.get(position);
                Toast.makeText(getActivity(),modelItem.item,Toast.LENGTH_SHORT).show();
            }
        });

        List<ModelItem> modelItemList = itemDS.getAllItems();
        isWifi = !(modelItemList.size()%2==0);
        counter = modelItemList.size();
        listView.setAdapter(new AdapterItemList(getActivity(),modelItemList));
        /*Colocar loa función de borrado*/
        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                        AdapterItemList adapterItemList = (AdapterItemList) adapterView.getAdapter();
                        final ModelItem mi = adapterItemList.getItem(position);
                        new AlertDialog.Builder(getActivity())
                                .setTitle(R.string.delete_title)
                                .setMessage(String.format("¿Do you want delete this element %s?", mi.item))
                                .setPositiveButton(android.R.string.ok,new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        itemDS.deleteItem(mi);
                                        listView.setAdapter(new AdapterItemList(getActivity(),
                                                itemDS.getAllItems()));
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .setCancelable(false).create().show();
                        return true;
                    }
                }
        );

        final EditText mItemsText = (EditText) view.findViewById(R.id.frag_list_mItemText);
        view.findViewById(R.id.btnAddItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemData = mItemsText.getText().toString();
                if(!TextUtils.isEmpty(itemData)){
                    ModelItem item =new ModelItem();
                    item.item = itemData;
                    item.id  = "Description "+counter;
                    item.resourceId = isWifi?
                            R.drawable.ic_device_signal_wifi_4_bar: R.drawable.ic_action_settings_voice;
                    itemDS.saveItem(item);
                    listView.setAdapter(new AdapterItemList(getActivity(),itemDS.getAllItems()));
                    isWifi=!isWifi;
                    counter++;
                    mItemsText.setText("");
                } else {
                    Toast.makeText(getActivity(),R.string.empty_list,Toast.LENGTH_SHORT).show();

                }
            }
        });
        return view;
    }


}

