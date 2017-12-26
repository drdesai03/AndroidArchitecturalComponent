package com.inventyfy.architecture.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.inventyfy.architecture.R;
import com.inventyfy.architecture.ui.home.search.activity.HomeActivity;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                startHome();
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void startHome() {
        Intent dashboardIntent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(dashboardIntent);
    }
}
