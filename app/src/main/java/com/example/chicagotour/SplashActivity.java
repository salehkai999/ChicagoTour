package com.example.chicagotour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 3000;
    private static final String TAG = "SplashActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Log.d(TAG, "onCreate: Splash");
        new Handler().postDelayed(() -> {
            Intent i =
                    new Intent(SplashActivity.this, MapsActivity.class);
            startActivity(i);
            Log.d(TAG, "onCreate: ");
            overridePendingTransition(R.anim.in, R.anim.out);
            finish();
        }, SPLASH_TIME_OUT);
    }
}