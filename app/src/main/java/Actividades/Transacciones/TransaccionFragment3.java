package Actividades.Transacciones;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.chryscontreras.micochinito.R;

import org.codepond.wizardroid.WizardStep;
import org.codepond.wizardroid.persistence.ContextVariable;

import Adaptadores.CuentaElementoDetallesAdapter;
import Interfaces.ITransaccionesEventos;
import Modelos.Transaccion;

/**
 * Created by Chrysthoper on 14/08/2016.
 */
public class TransaccionFragment3 extends WizardStep {

    @ContextVariable
    private ITransaccionesEventos eventos;
    @ContextVariable
    private Transaccion trans;
    @ContextVariable
    private char op;

    EditText tvDescripcion,tvNotas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.transacciones_paso3, container, false);
        tvDescripcion = (EditText) rootView.findViewById(R.id.tvDescTransaccion);
        tvDescripcion.setText(trans.descripcion);
        tvNotas = (EditText) rootView.findViewById(R.id.tvNotaTransaccion);
        tvNotas.setText(trans.nota);
        return rootView;
    }

    @Override
    public void onExit(int exitCode) {
        switch (exitCode) {
            case WizardStep.EXIT_NEXT:
                bindDataFields();
                break;
            case WizardStep.EXIT_PREVIOUS:
                //Do nothing...
                break;
        }
    }

    private void bindDataFields() {
        trans.descripcion = tvDescripcion.getText().toString().trim();
        trans.nota = tvNotas.getText().toString().trim();
    }

}


