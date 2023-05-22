package com.example.reminisce_ticketing.auth.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.reminisce_ticketing.MainActivity;
import com.example.reminisce_ticketing.R;
import com.example.reminisce_ticketing.auth.forget.ForgetActivity;

public class LoginActivity extends AppCompatActivity {
    TextView forgetPassword;
    LinearLayout login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        forgetPassword =  findViewById(R.id.forgetPassword);
        login =  findViewById(R.id.login);
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgetActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}