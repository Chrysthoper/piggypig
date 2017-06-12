package Actividades.Transacciones;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.chryscontreras.micochinito.R;

import org.codepond.wizardroid.WizardFlow;
import org.codepond.wizardroid.layouts.BasicWizardLayout;
import org.codepond.wizardroid.persistence.ContextVariable;

import Interfaces.ITransaccionesEventos;
import Modelos.Transaccion;
import Modelos.TransaccionProgramada;
import Persistencia.ContextoBD;

/**
 * Created by Chrys-Emcor on 25/08/2016.
 */
public class TransaccionesWizard extends BasicWizardLayout {

    @ContextVariable
    private ITransaccionesEventos eventos;
    @ContextVariable
    private Transaccion trans = new Transaccion("",-1,-1,"","","",0,0,false,0);
    @ContextVariable
    private char op = 'X';

    public TransaccionesWizard() {
        super();
    }

    /*
    public static TransaccionesWizard newInstance(ITransaccionesEventos eventos){

        TransaccionesWizard dialogFragment = new TransaccionesWizard();
        Bundle bundle = new Bundle();
        bundle.putSerializable("listener", eventos);
        dialogFragment.setArguments(bundle);

        return dialogFragment;

    }
    */

    @Override
    public WizardFlow onSetup() {
        return new WizardFlow.Builder()
                .addStep(TransaccionFragment1.class)
                .addStep(TransaccionFragment2.class)
                .addStep(TransaccionFragment3.class)
                .create();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        View frag = (View)view.findViewById(R.id.step_container);
        String fecha;
        int tipo = 0;
        int cuenta_id = 0;
        Transaccion transaccion = new Transaccion();

        if (savedInstanceState != null)
        {
            eventos = (ITransaccionesEventos) savedInstanceState.getSerializable("listener");
            fecha = savedInstanceState.getString("FECHA");
            tipo = savedInstanceState.getInt("TIPO");
            cuenta_id = savedInstanceState.getInt("CUENTA_ID");
            op = savedInstanceState.getChar("OP");
            transaccion = (Transaccion) savedInstanceState.getSerializable("TRANS");
            this.trans.fecha_alta = fecha;
            this.trans.tipo_transaccion = tipo;
            this.trans.cuenta_prin_id = cuenta_id;
            this.trans.costo = transaccion.costo;
        }
        else if(getArguments() != null)
        {
            fecha = getArguments().getString("FECHA");
            tipo = getArguments().getInt("TIPO", 0);
            cuenta_id = getArguments().getInt("CUENTA_ID", 0);
            op = getArguments().getChar("OP");
            transaccion = (Transaccion)getArguments().getSerializable("TRANS");
            eventos = (ITransaccionesEventos) getArguments().getSerializable("listener");
            this.trans.fecha_alta = fecha;
            this.trans.tipo_transaccion = tipo;
            this.trans.cuenta_prin_id = cuenta_id;
            if(transaccion != null)
            {
                this.trans.costo = transaccion.costo;
            }
        }


        this.trans.tipo_transaccion = 1;
        /*
        switch(tipo)
        {
            case R.id.btnEntrada: case 1:
            this.trans.tipo_transaccion = 1;
            frag.setBackgroundColor(Color.parseColor("#ff99cc00"));
            break;
            case R.id.btnSalida: case 0:
            this.trans.tipo_transaccion = 0;
            frag.setBackgroundColor(Color.parseColor("#ffff4444"));
            break;
            case R.id.lyTraspaso:case 2:
            this.trans.tipo_transaccion = 2;
            frag.setBackgroundColor(Color.BLACK);
            break;
            default:
                break;
        }
        */
        if(op == 'C' || op == 'N')
        {
            this.trans = transaccion;
            this.trans.textoKeyPad = String.valueOf(trans.costo);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("listener", eventos);
        outState.putString("FECHA", trans.fecha_alta);
        outState.putInt("TIPO", trans.tipo_transaccion);
        outState.putInt("CUENTA_ID", trans.cuenta_prin_id);
        outState.putChar("OP", op);
        outState.putSerializable("TRANS", trans);
    }

    @Override
    public void onWizardComplete() {
        super.onWizardComplete();
        Intent returnIntent = new Intent();
        String error = Validate();
        if(error.equals(""))
        {
            ContextoBD ContextoBD = new ContextoBD(getActivity().getApplicationContext());
            Transaccion transaccion = new Transaccion(
                    trans.id,
                    Double.parseDouble(trans.textoKeyPad),
                    trans.numeroCategoria,
                    trans.fecha_alta,
                    trans.nota,
                    trans.descripcion,
                    trans.cuenta_prin_id,
                    trans.cuenta_secu_id,
                    trans.tipo_transaccion,
                    trans.programacion_id);
            if(op == 'C');
                //transaccion = ContextoBD.Modifica(transaccion);
            else
            {
                transaccion = ContextoBD.Transacciones.Inserta(transaccion);
                /*
                if(trans.programar)
                {
                    TransaccionProgramada transaccion_programada = new TransaccionProgramada(trans.id,Double.parseDouble(trans.textoKeyPad),trans.numeroCategoria,trans.fecha_alta,trans.nota,trans.descripcion,trans.cuenta_prin_id, trans.tipo_transaccion, trans.fecha_alta, "MENSUAL", true);
                    transaccion_programada = ContextoBD.Transacciones_Programadas.Inserta(transaccion_programada);
                    trans.programadaObj = transaccion_programada;
                }
                */
            }
            returnIntent.putExtra("trans", transaccion);
            getActivity().setResult(1, returnIntent);
            getActivity().finish();     //Terminate the wizard
        }
        else
        {
            Toast.makeText(getActivity().getApplicationContext(), error, Toast.LENGTH_SHORT).show();
        }
    }

    public String Validate(){
        if(trans.textoKeyPad.equals(".") || trans.textoKeyPad.equals("") || !tryParseDouble(trans.textoKeyPad))
        {
            return "No se ha ingresado un total";
        }
        else if(trans.tipo_transaccion < 2)
        {
            if(trans.numeroCategoria == -1)
                return "No se ha seleccionado una categoria";
            else
                return "";
        }
        else
        {
            if(trans.cuenta_secu_id == -1)
                return "No se ha seleccionado una cuenta";
            else
                return "";
        }

    }

    boolean tryParseDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
