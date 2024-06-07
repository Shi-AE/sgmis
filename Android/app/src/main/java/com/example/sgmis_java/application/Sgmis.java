package com.example.sgmis_java.application;

import android.app.Application;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sgmis_java.R;
import com.example.sgmis_java.utils.SharedPreferencesUtils;

public class Sgmis extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesUtils.init(this);
    }
}
