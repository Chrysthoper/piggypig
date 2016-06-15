package com.chryscontreras.micochinito;

import android.app.Application;
import com.squareup.otto.Bus;

public class App extends Application
{
    private static Bus bus;

    // necessary to avoid to unregister a non-registered instance . Since EventListener is a Singleton
    private static boolean isRegistered;

    public static void setIsRegistered(final boolean registered){
        isRegistered = registered;
    }

    public static boolean isRegistered(){
        return isRegistered;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();

        bus = new Bus(); // Instantiate a new Bus
    }

    public static Bus getBus()
    {
        return bus;
    }

}
