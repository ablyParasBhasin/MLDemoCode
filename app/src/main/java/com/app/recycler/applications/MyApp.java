package com.app.recycler.applications;

import android.app.Application;

import com.pixplicity.easyprefs.library.Prefs;
import android.content.ContextWrapper;



public class MyApp extends Application {

    @Override
    public void onCreate() {

        super.onCreate();

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
    }


}