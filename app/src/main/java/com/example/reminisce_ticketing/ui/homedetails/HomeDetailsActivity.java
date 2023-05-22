package com.example.reminisce_ticketing.ui.homedetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.reminisce_ticketing.MainActivity;
import com.example.reminisce_ticketing.R;
import com.example.reminisce_ticketing.ui.scan_with_camera.ScanWithCamera;

public class HomeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_details);
        findViewById(R.id.scan_ticket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeDetailsActivity.this, ScanWithCamera.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}