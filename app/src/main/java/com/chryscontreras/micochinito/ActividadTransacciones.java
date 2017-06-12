package com.chryscontreras.micochinito;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import Actividades.Transacciones.TransaccionesWizard;
import Adaptadores.CuentaElementoListaAdapter;
import Interfaces.ITransaccionesEventos;
import Modelos.Categoria;
import Modelos.Transaccion;
import Persistencia.ContextoBD;

public class ActividadTransacciones extends FragmentActivity implements ITransaccionesEventos {

    private ContextoBD ContextoBD;
    Spinner spCuentas;
    ArrayList<Categoria> ListaCuentas;
    TextView tvCostoTransaccion;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_transacciones);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            int tipo = intent.getIntExtra("TIPO", 0);
            char op = intent.getCharExtra("OP", 'X');
            String fecha = intent.getStringExtra("FECHA");
            int cuenta_id = intent.getIntExtra("CUENTA_ID", 0);
            Transaccion trans = (Transaccion) intent.getSerializableExtra("TRANS");
            if(trans == null)
                trans = new Transaccion();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            TransaccionesWizard fragment = new TransaccionesWizard();
            Bundle args = new Bundle();
            args.putChar("OP", op);
            args.putInt("TIPO", tipo);
            args.putString("FECHA", fecha);
            args.putInt("CUENTA_ID", cuenta_id);
            args.putSerializable("TRANS", trans);
            args.putSerializable("listener", this);
            fragment.setArguments(args);
            ft.remove(fragment);
            ft.replace(R.id.tutorial_wizard_fragment, fragment, "your_fragment_tag");
            ft.commit();
        } else {
            TransaccionesWizard fragmentSaved = (TransaccionesWizard) getSupportFragmentManager().findFragmentByTag("your_fragment_tag");
        }

        tvCostoTransaccion = (TextView)findViewById(R.id.tvCostoTransaccion);
        spCuentas = (Spinner)findViewById(R.id.spCuentas);

        ContextoBD = new ContextoBD(ActividadTransacciones.this);
        ListaCuentas = this.ContextoBD.Categorias.ObtenCuentas();

        CuentaElementoListaAdapter adapter = new CuentaElementoListaAdapter(ActividadTransacciones.this, ListaCuentas);
        spCuentas.setAdapter(adapter);
    }

    @Override
    public void tecladoCalculadora(String tecla) {
        tvCostoTransaccion.setText(tecla);
    }

    @Override
    public void cuentaSeleccionada(int cuenta) {

    }

}



