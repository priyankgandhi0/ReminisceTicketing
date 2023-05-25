package com.example.reminisce_ticketing;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.reminisce_ticketing.constant.Constants;
import com.example.reminisce_ticketing.databinding.ActivityMainBinding;
import com.example.reminisce_ticketing.factory.GeneralViewModelFactory;
import com.example.reminisce_ticketing.ui.change_password.ChangePassword;
import com.example.reminisce_ticketing.ui.home.HomeFragment;
import com.example.reminisce_ticketing.ui.scan_with_camera.ScanWithCamera;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this, new GeneralViewModelFactory<>(binding, this)).get(MainActivityViewModel.class);
        initView();
    }


    private void initView() {
        findViewById(R.id.menuButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.drawerLayout.open();
            }
        });
//        findViewById(R.id.iv_notification).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Notification.class);
//                startActivity(intent);
//            }
//        });
        findViewById(R.id.home_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.drawerLayout.close();
                viewModel.openFragment(HomeFragment.class, Constants.HOME_FRAGMENTS);
            }
        });
        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.drawerLayout.close();
                viewModel.showLogoutDialog();
            }
        });
        findViewById(R.id.scan_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.drawerLayout.close();
                Intent intent = new Intent(MainActivity.this, ScanWithCamera.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.change_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.drawerLayout.close();
                Intent intent = new Intent(MainActivity.this, ChangePassword.class);
                startActivity(intent);
            }
        });
    }
}