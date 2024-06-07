package com.example.sgmis_java.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {
    private static SharedPreferences sharedPreferences;

    public static void init(Context context) {
        sharedPreferences = context.getSharedPreferences("sgmis", Context.MODE_PRIVATE);
    }

    public static String getString(String key, String def) {
        return sharedPreferences.getString(key, def);
    }

    public static void putString(String key, String value) {
        sharedPreferences
                .edit()
                .putString(key, value)
                .apply();
    }

    public static Boolean getBoolean(String key, Boolean def) {
        return sharedPreferences.getBoolean(key, def);
    }

    public static void putBoolean(String key, Boolean value) {
        sharedPreferences
                .edit()
                .putBoolean(key, value)
                .apply();
    }
}
