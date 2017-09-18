package com.teachonmars.module.appLife.appDemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class AppLifeDemoActivity extends AppCompatActivity {
    private static final String TAG = AppLifeDemoActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StaticWithoutContext.test();
    }

}
