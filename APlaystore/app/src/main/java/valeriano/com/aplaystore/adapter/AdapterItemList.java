package valeriano.com.aplaystore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import valeriano.com.aplaystore.R;
import valeriano.com.aplaystore.model.ModelAplication;

/**
 * Clase para manejar la lista de elementos
 * Created by luis.valeriano on 06/07/2016.
 */
public class AdapterItemList extends ArrayAdapter<ModelAplication>{

    /*Cosntructor del adaptador*/
    public AdapterItemList(Context context, List<ModelAplication> list) {
        super(context, 0, list);
    }
    /*Método para procesar todos los elementos de la vista*/
    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent, false);
        }
        TextView txtVwTitleApp = (TextView) convertView.findViewById(R.id.rowlist_txtTitleAplication);
        TextView txtVwDevep = (TextView) convertView.findViewById(R.id.rowlist_txtDesarrollador);
        ImageView img = (ImageView) convertView.findViewById(R.id.rowlist_rowImagenView);
        TextView txtInstal = (TextView) convertView.findViewById(R.id.rowlist_txtInstalada);
        ModelAplication mApp = getItem(position);
        txtVwTitleApp.setText(mApp.nameAplication);
        if (!mApp.getNameAplication().isEmpty()) {
            txtVwDevep.setText(mApp.getNameDeveloper());
            txtInstal.setText(getInstaled(mApp.instaled));
            Picasso.with(getContext()).load(mApp.resourceId).into(img);
        }
        return convertView;
    }

    private String getInstaled(int instaled) {
        if (instaled == 2)
                return getContext().getResources().getString(R.string.AppUpdate);
        if (instaled == 1)
            return getContext().getResources().getString(R.string.ApptInstaled);
        if (instaled == 0)
            return getContext().getResources().getString(R.string.AppNotInstaled);
        return getContext().getResources().getString(R.string.AppNotInstaled);
    }


}
