package com.chryscontreras.micochinito;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import Interfaces.ITransaccionesEventos;

public class ActividadTransacciones extends FragmentActivity implements ITransaccionesEventos {
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    TransaccionPagerAdapter PagerAdapter;
    ViewPager mViewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_transacciones);

        /*
        PagerAdapter = new TransaccionPagerAdapter(getSupportFragmentManager());
        PagerAdapter.addFragment(new TransaccionFragment1());
        PagerAdapter.addFragment(new TransaccionFragment2());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(PagerAdapter);
        */
    }

    @Override
    public void tecladoCalculadora(String tecla) {

    }

    @Override
    public void cuentaSeleccionada(int cuenta) {

    }

}



