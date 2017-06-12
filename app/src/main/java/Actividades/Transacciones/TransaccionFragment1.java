package Actividades.Transacciones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chryscontreras.micochinito.R;

import org.codepond.wizardroid.WizardStep;
import org.codepond.wizardroid.persistence.ContextVariable;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Interfaces.ITransaccionesEventos;
import Modelos.Transaccion;

public class TransaccionFragment1 extends WizardStep {

    @ContextVariable
    private ITransaccionesEventos eventos;
    @ContextVariable
    private Transaccion trans;
    @ContextVariable
    private char op;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.transacciones_paso1, container, false);
        Button btn1 = (Button)rootView.findViewById(R.id.btn1);
        Button btn2 = (Button)rootView.findViewById(R.id.btn2);
        Button btn3 = (Button)rootView.findViewById(R.id.btn3);
        Button btn4 = (Button)rootView.findViewById(R.id.btn4);
        Button btn5 = (Button)rootView.findViewById(R.id.btn5);
        Button btn6 = (Button)rootView.findViewById(R.id.btn6);
        Button btn7 = (Button)rootView.findViewById(R.id.btn7);
        Button btn8 = (Button)rootView.findViewById(R.id.btn8);
        Button btn9 = (Button)rootView.findViewById(R.id.btn9);
        Button btnZero = (Button)rootView.findViewById(R.id.btnZero);
        Button btnDel = (Button)rootView.findViewById(R.id.btnDEL);
        Button btnPunto = (Button)rootView.findViewById(R.id.btnPunto);
        btn1.setOnClickListener(clickEvent);
        btn2.setOnClickListener(clickEvent);
        btn3.setOnClickListener(clickEvent);
        btn4.setOnClickListener(clickEvent);
        btn5.setOnClickListener(clickEvent);
        btn6.setOnClickListener(clickEvent);
        btn7.setOnClickListener(clickEvent);
        btn8.setOnClickListener(clickEvent);
        btn9.setOnClickListener(clickEvent);
        btnZero.setOnClickListener(clickEvent);
        btnDel.setOnClickListener(clickEvent);
        btnPunto.setOnClickListener(clickEvent);
        return rootView;
    }

    View.OnClickListener clickEvent = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
        Button b = (Button)v;
        String texto = b.getText().toString();
        if(!texto.equals("DEL"))
        {
            IngresaCosto(trans.textoKeyPad + texto);
        }
        else
        {
            trans.textoKeyPad = "";
            eventos.tecladoCalculadora("");
        }
        }
    };

    public void IngresaCosto(String costo){
        Pattern patron = Pattern.compile("^([1-9][0-9]{0,7})?(\\.[0-9]{0,2})?$");
        Matcher matcher = patron.matcher(costo);
        if(matcher.matches())
        {
            trans.textoKeyPad = costo;

            if(trans.textoKeyPad.startsWith("."))
                eventos.tecladoCalculadora("$ 0" + trans.textoKeyPad);
            else
            {
                DecimalFormat formatter = new DecimalFormat("#,###.00");
                eventos.tecladoCalculadora("$ " + formatter.format(Double.parseDouble(trans.textoKeyPad)));
            }
        }
    }
}
