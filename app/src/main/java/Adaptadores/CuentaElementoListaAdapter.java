package Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chryscontreras.micochinito.R;
import com.chryscontreras.micochinito.Util;

import java.util.ArrayList;

import Modelos.Categoria;

/**
 * Created by Chrysthoper on 28/08/2016.
 */
public class CuentaElementoListaAdapter extends BaseAdapter {

    private Context context;
    private final ArrayList<Categoria> categorias;

    public CuentaElementoListaAdapter(Context context, ArrayList<Categoria> categorias) {
        this.context = context;
        this.categorias = categorias;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MyViewHolder holder;
        final Categoria cuenta = this.categorias.get(position);
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.cuenta_elemento_lista, null, false);

            holder = new MyViewHolder();
            holder.tvNombreElementoAdapter = (TextView) row.findViewById(R.id.tvNombreElementoAdapter);
            holder.tvElementoAdapter = (TextView) row.findViewById(R.id.tvElementoAdapter);
            holder.ivElementoAdapter = (ImageView) row.findViewById(R.id.ivElementoAdapter);

            row.setTag(holder);
        } else {
            holder = (MyViewHolder) row.getTag();
        }
        holder.tvNombreElementoAdapter.setText(cuenta.nombre);
        holder.tvElementoAdapter.setText(Util.PriceFormat(cuenta.total));
        holder.ivElementoAdapter.setImageResource(Util.imagenesFull[cuenta.resource]);
        return row;
    }

    @Override
    public int getCount() {
        return categorias.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private static class MyViewHolder {
        public TextView tvNombreElementoAdapter,tvElementoAdapter;
        public ImageView ivElementoAdapter;
    }
}
