package com.swap.covid_19tracker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.swap.covid_19tracker.R;

public class MainActivity extends AppCompatActivity {

    private static final long SPLASH_TIMEOUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // splash screen code
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                finish();
            }
        }, SPLASH_TIMEOUT);
    }
}