package com.chryscontreras.micochinito;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import Modelos.Categoria;

public class CuentaElementoAdapter extends ArrayAdapter<Categoria> {

    private final Context context;
    private final ArrayList<Categoria> values;

    public CuentaElementoAdapter(Context context, ArrayList<Categoria> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.cuentas_elemento_lista, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.firstLine);
        textView.setText(values.get(position).nombre);
        TextView textView2 = (TextView) rowView.findViewById(R.id.secondLine);
        textView2.setText(String.valueOf(values.get(position).id));
        return rowView;
    }

}
