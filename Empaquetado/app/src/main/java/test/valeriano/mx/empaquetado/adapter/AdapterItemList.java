package test.valeriano.mx.empaquetado.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import test.valeriano.mx.empaquetado.R;
import test.valeriano.mx.empaquetado.model.ModelItem;

/**
 * Created by luis.valeriano on 18/06/2016.
 */
public class AdapterItemList extends ArrayAdapter <ModelItem>{

    /*Se inicializa con el constructor*/
    public AdapterItemList(Context context, List <ModelItem> list ) {
        super(context, 0, list);
    }

    /*Genera los renglones que se captura del fragmento*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list,parent,false);
        }
        TextView txtItemDescription= (TextView) convertView.findViewById(R.id.txtItemDescription);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtItemTitle);
        ImageView img = (ImageView) convertView.findViewById(R.id.row_image_view);

        ModelItem modelItem = getItem(position);
        txtTitle.setText(modelItem.item);
        txtItemDescription.setText(modelItem.id);
        img.setImageResource(modelItem.resourceId);
        return convertView;
    }
}
