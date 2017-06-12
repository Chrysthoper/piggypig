package com.chryscontreras.micochinito;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import java.util.Calendar;

public class ActividadPrincipal extends Activity {

    Calendar cTransaccion = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        WindowManager wm = getWindowManager();
        Display d = wm.getDefaultDisplay();

        if (d.getRotation() != Surface.ROTATION_0) {
            FragmentLandscape fragment1 = new FragmentLandscape();
            fragmentTransaction.replace(android.R.id.content, fragment1).commit();
        } else {
            FragmentPortrait fragment2 = new FragmentPortrait();
            fragmentTransaction.replace(android.R.id.content, fragment2).commit();
        }
    }
    @Subscribe
    public void OnFirstItemClicked(FirstItemClicked firstItemClicked){
        Toast.makeText(this, "FirstItem - Clicked!!!", Toast.LENGTH_LONG).show();
        Log.d("FirstItem", "Clicked !!");
    }
    @Subscribe
    public void OnSecondItemClicked(SecondItemClicked secondItemClicked){
        Toast.makeText(this, "SecondItem - Clicked!!!", Toast.LENGTH_LONG).show();
        Log.d("SecondItem", "Clicked !!");

        String fecha = Util.FechaToFormat(cTransaccion.getTime());
        final Intent intent = new Intent(getApplicationContext(), ActividadTransacciones.class);
        intent.putExtra("FECHA", fecha);
        intent.putExtra("TIPO", 1);
        intent.putExtra("OP", 'A');
        intent.putExtra("CUENTA_ID", 2);//Cuentas.get(which).id);

        startActivity(intent);//new Intent(getApplicationContext(), ActividadTransacciones.class));
    }
    @Override
    protected void onStart(){
        super.onStart();
        if(!App.isRegistered())
        {
            App.setIsRegistered(true);
            App.getBus().register(this); // Here we register this activity in bus.
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(App.isRegistered())
        {
            App.getBus().unregister(this); // Here we unregister this acitivity from the bus.
            App.setIsRegistered(false);
        }

    }
}
