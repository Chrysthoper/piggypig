package Actividades.Transacciones;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.chryscontreras.micochinito.R;

import org.codepond.wizardroid.WizardStep;
import org.codepond.wizardroid.persistence.ContextVariable;

import java.util.ArrayList;
import java.util.List;

import Adaptadores.CuentaElementoDetallesAdapter;
import Adaptadores.CuentaElementoListaAdapter;
import Interfaces.ITransaccionesEventos;
import Modelos.Categoria;
import Modelos.Transaccion;
import Persistencia.ContextoBD;

/**
 * Created by Chrysthoper on 14/08/2016.
 */
public class TransaccionFragment2 extends WizardStep {

    @ContextVariable
    public ITransaccionesEventos Eventos;
    @ContextVariable
    private Transaccion trans;
    @ContextVariable
    private char op;

    public ContextoBD ContextoBD;

    ArrayList<Categoria> ListaCategorias;
    CuentaElementoDetallesAdapter adapter;
    ListView lvCategorias;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.transacciones_paso2, container, false);

        ContextoBD = new ContextoBD(getActivity().getApplicationContext());

        lvCategorias = (ListView)rootView.findViewById(R.id.lvCategorias);

        if(trans.tipo_transaccion < 2)
        {
            ListaCategorias = ContextoBD.Categorias.ObtenPorTipo(trans.tipo_transaccion);
            adapter = new CuentaElementoDetallesAdapter(getActivity().getApplicationContext(), ListaCategorias, trans.numeroCategoria);
            lvCategorias.setAdapter(adapter);
            lvCategorias.setOnItemClickListener(click_grid);
            if(op == 'C')
            {
                for(Categoria cat : ListaCategorias)
                    if(cat.id == trans.categoriaObj.id)
                        trans.categoriaObj = cat;
            }
        }
        else
        {
            ListaCategorias = ContextoBD.Categorias.ObtenPorTipo(trans.tipo_transaccion);
            adapter = new CuentaElementoDetallesAdapter(getActivity().getApplicationContext(), ListaCategorias, trans.cuenta_secu_id);
            lvCategorias.setAdapter(adapter);
            lvCategorias.setOnItemClickListener(click_grid);
            if(op == 'C')
            {
                for(Categoria cuenta : ListaCategorias)
                    if(cuenta.id == trans.cuentaSecundariaObj.id)
                        trans.cuentaSecundariaObj = cuenta;
            }
        }
        return rootView;
    }



    AdapterView.OnItemClickListener click_grid = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position,
                                long id) {

            if(trans.tipo_transaccion < 2)
            {
                //if(position > 0) {
                    int idCategoria = ListaCategorias.get(position).id;
                    if (trans.numeroCategoria != idCategoria) {
                        if (trans.numeroCategoria != -1 && trans.categoriaObj != null)
                        {
                            for(Categoria cat : ListaCategorias)
                                if(cat.id == trans.categoriaObj.id)
                                    lvCategorias.getChildAt(ListaCategorias.indexOf(cat)).setBackground(null);
                        }
                        trans.numeroCategoria = ListaCategorias.get(position).id;
                        trans.categoriaObj = ListaCategorias.get(position);
                        v.setBackgroundColor(Color.parseColor("#FFAA2300"));
                    } else {
                        for(Categoria cat : ListaCategorias)
                            if(cat.id == trans.categoriaObj.id)
                                lvCategorias.getChildAt(ListaCategorias.indexOf(cat)).setBackground(null);
                        trans.numeroCategoria = -1;
                    }
            /*
                }
                else {
                    startActivityForResult(new Intent(v.getContext(), abcCategorias.class), 1);
                }
            */
            }
            else
            {
                int idCuenta = ListaCategorias.get(position).id;
                if (trans.cuenta_secu_id != idCuenta)
                {
                    if (trans.cuenta_secu_id != -1 && trans.cuentaSecundariaObj != null)
                    {
                        for(Categoria cuenta : ListaCategorias)
                            if(cuenta.id == trans.cuentaSecundariaObj.id)
                                lvCategorias.getChildAt(ListaCategorias.indexOf(cuenta)).setBackground(null);
                    }
                    trans.cuenta_secu_id = ListaCategorias.get(position).id;
                    trans.cuentaSecundariaObj = ListaCategorias.get(position);
                    v.setBackgroundColor(Color.parseColor("#FFAA2300"));
                }
                else
                {
                    for(Categoria cuenta : ListaCategorias)
                        if(cuenta.id == trans.cuentaSecundariaObj.id)
                            lvCategorias.getChildAt(ListaCategorias.indexOf(cuenta)).setBackground(null);
                    trans.cuenta_secu_id = -1;
                }
            }
        }
    };
/*
    ListView.OnItemClickListener lista_click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
            builderSingle.setIcon(R.mipmap.micochinito);
            builderSingle.setAdapter(adapter,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String strName = ListaCategorias.get(which).nombre;
                            AlertDialog.Builder builderInner = new AlertDialog.Builder(getContext());
                            builderInner.setMessage(strName);
                            builderInner.setTitle("Your Selected Item is");
                            builderInner.setPositiveButton(
                                    "Ok",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(
                                                DialogInterface dialog,
                                                int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            builderInner.show();
                        }
                    });
            builderSingle.show();
        }
    };
*/



}


