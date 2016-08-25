package com.chryscontreras.micochinito;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.codepond.wizardroid.WizardStep;

import Interfaces.ITransaccionesEventos;

// Instances of this class are fragments representing a single
// object in our collection.
public class TransaccionFragment1 extends WizardStep {
    public ITransaccionesEventos Eventos;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(R.layout.calculadora_layout, container, false);
        return rootView;
    }
}
