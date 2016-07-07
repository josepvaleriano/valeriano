package valeriano.com.aplaystore.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import valeriano.com.aplaystore.R;
import valeriano.com.aplaystore.adapter.AdapterItemList;
import valeriano.com.aplaystore.model.ModelAplication;
import valeriano.com.aplaystore.sql.AppDataSource;

/**
 * Created by luis.valeriano on 06/07/2016.
 */
public class FragmentList extends Fragment {
    private ListView listView;
    private AppDataSource appDS;

    /*Método para crear el fragmento*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appDS = new AppDataSource(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        listView = (ListView) view.findViewById(R.id.fraglist_listItems);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                AdapterItemList adapter = (AdapterItemList) adapterView.getAdapter();
                ModelAplication mApp = adapter.getItem(pos);
                Toast.makeText(getActivity(),mApp.nameAplication,Toast.LENGTH_SHORT).show();
            }
        });
        List<ModelAplication> lsApp = appDS.getAllAplications();
        listView.setAdapter( new AdapterItemList(getActivity(), lsApp));
        if (lsApp.isEmpty()){
            Toast.makeText(getActivity(), R.string.NoAplications,Toast.LENGTH_LONG).show();
        }
        return view;
    }
}
