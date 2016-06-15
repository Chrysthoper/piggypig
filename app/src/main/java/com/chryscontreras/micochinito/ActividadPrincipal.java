package com.chryscontreras.micochinito;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;

public class ActividadPrincipal extends Activity {

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
