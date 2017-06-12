package Adaptadores;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chryscontreras.micochinito.R;
import com.chryscontreras.micochinito.Util;

import java.util.ArrayList;

import Modelos.Categoria;

public class CuentaElementoDetallesAdapter extends ArrayAdapter<Categoria> {

    private final Context context;
    private final ArrayList<Categoria> values;
    private int seleccion;

    public CuentaElementoDetallesAdapter(Context context, ArrayList<Categoria> values, int seleccion) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
        this.seleccion = seleccion;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MyViewHolder holder;
        final Categoria cat = this.values.get(position);
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.cuenta_elemento_detalle, null, false);

            holder = new MyViewHolder();
            holder.tvValue = (TextView) row.findViewById(R.id.tvValue);
            holder.ivValue = (ImageView) row.findViewById(R.id.ivValue);

            if(seleccion == cat.id)
                row.setBackgroundColor(Color.parseColor("#FFAA2300"));

            row.setTag(holder);
        } else {
            holder = (MyViewHolder) row.getTag();
        }
        holder.tvValue.setText(cat.nombre);
        holder.ivValue.setImageResource(Util.imagenesFull[cat.resource]);
        return row;
    }

    private static class MyViewHolder {
        public TextView tvValue;
        public ImageView ivValue;
    }

}
