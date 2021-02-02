package com.example.melochizhizni.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import com.example.melochizhizni.MainActivity;
import com.example.melochizhizni.R;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;

public class SplashScreenActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);
                finish();
                overridePendingTransition( R.anim.rotate, R.anim.alpha);
            }
        }, SPLASH_SCREEN_OUT);


    }
}