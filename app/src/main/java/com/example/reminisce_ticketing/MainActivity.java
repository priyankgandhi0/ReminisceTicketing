package com.example.reminisce_ticketing;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.reminisce_ticketing.adapter.HomeItemAdapter;
import com.example.reminisce_ticketing.model.HomeItemModel;
import com.example.reminisce_ticketing.ui.change_password.ChangePassword;
import com.example.reminisce_ticketing.ui.home.HomeFragment;
import com.example.reminisce_ticketing.ui.scan_with_camera.ScanWithCamera;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public DrawerLayout drawerLayout;

    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        openFragment(new HomeFragment());

        findViewById(R.id.menuButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });
        findViewById(R.id.home_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.close();
                Toast.makeText(MainActivity.this, "Clickedd", Toast.LENGTH_SHORT).show();
                        openFragment(new HomeFragment());
            }
        });
        findViewById(R.id.scan_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.close();
                Intent intent = new Intent(MainActivity.this, ScanWithCamera.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.change_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.close();
                Intent intent = new Intent(MainActivity.this, ChangePassword.class);
                startActivity(intent);
            }
        });

    }

    private void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }
}