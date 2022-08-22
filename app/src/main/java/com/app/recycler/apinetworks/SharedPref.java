package com.app.recycler.apinetworks;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private SharedPreferences sharedpreferences;

    public SharedPref(Context act) {
        String MY_PREFERENCES = getClass().getSimpleName();
        sharedpreferences = act.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
    }

    public String getString(String key) {
        return sharedpreferences.getString(key, "");
    }

    public void saveString(String key, String value) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, value);

        editor.apply();

    }

    public Boolean getBoolean(String key) {
        return sharedpreferences.getBoolean(key, false);
    }

    public void saveBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public long getLong(String key) {
        return sharedpreferences.getLong(key, 0l);
    }

    public void saveLong(String key, long value) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public void clear(String key) {
        sharedpreferences.edit().remove(key).apply();
    }

    public void  clearPreferrence() {
        sharedpreferences.edit().clear().apply();
    }

    public byte[] getBytes(String key) {
        String byteString = sharedpreferences.getString(key, "");
        try {
            if (byteString != null && !byteString.isEmpty()) {
                String[] split = byteString.substring(1, byteString.length() - 1).split(", ");
                byte[] array = new byte[split.length];
                for (int i = 0; i < split.length; i++) {
                    array[i] = Byte.parseByte(split[i]);
                }

                return array;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

}