package com.chryscontreras.micochinito;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.codepond.wizardroid.WizardStep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Interfaces.ITransaccionesEventos;
import Modelos.Categoria;
import Persistencia.Categorias;
import Persistencia.ContextoBD;

/**
 * Created by Chrysthoper on 14/08/2016.
 */
public class TransaccionFragment2 extends WizardStep {
    public ITransaccionesEventos Eventos;

    public ContextoBD ContextoBD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cuentas_fragment, container, false);

        ContextoBD = new ContextoBD(getContext());

        ArrayList<Categoria> ListaCategorias = ContextoBD.Categorias.Obten();

        final ListView listview = (ListView) rootView.findViewById(R.id.listview);
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };

        final CuentaElementoAdapter adapter = new CuentaElementoAdapter(getContext(), ListaCategorias);
        listview.setAdapter(adapter);

        return rootView;
    }
}


