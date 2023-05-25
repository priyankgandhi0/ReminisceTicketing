package com.example.reminisce_ticketing.auth.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.reminisce_ticketing.MainActivity;
import com.example.reminisce_ticketing.R;
import com.example.reminisce_ticketing.SharedPref;
import com.example.reminisce_ticketing.auth.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start your main activity or any other desired activity here
                if (!SharedPref.getBoolean(SharedPref.IsUserLogin, getApplicationContext())) {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 2000); // Del
    }
}