package com.devs.victorhernandez.yogafitness.Api;


import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by victor.hernandez on 04/07/2017.
 */

public class Globales {
    //variables de pantalla
    public static int AnchoPantallaOriginal = 1080;
    public int w = 0;
    public int h = 0;


    //api getnew pixels
    public Globales(int width, int height) {
        w = width;
        h = height;
    }



    //Display
    public static int Display(String v, Context contexto) {
        int valor = 0;
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) contexto
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        int wi = metrics.widthPixels;
        int he = metrics.heightPixels;
        if (v.equals("h")) {
            valor = he;
        } else {
            valor = wi;
        }
        return valor;

    }

    //getnew pixels
    public int getNewPixels(int pixels) {
        int retorno = 0;
        try {
            double factor = ((double) pixels) / ((double) AnchoPantallaOriginal);
            retorno = (int) (w * factor);
        } catch (Exception e) {
        }
        return retorno;
    }

    //inicializar realm
    public Realm initrealm(Context contexto, Realm realm) {
        try {
            Realm.init(contexto);
            RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                    .name("gourmet.realm")
                    .schemaVersion(2)
                    .deleteRealmIfMigrationNeeded()
                    .build();

            realm = Realm.getInstance(realmConfiguration);
            Realm.setDefaultConfiguration(realmConfiguration);
        } catch (Exception e) {
        }

        return realm;
    }


}
