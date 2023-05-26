package com.example.reminisce_ticketing;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.reminisce_ticketing.constant.Constants;
import com.example.reminisce_ticketing.databinding.ActivityMainBinding;
import com.example.reminisce_ticketing.factory.GeneralViewModelFactory;
import com.example.reminisce_ticketing.ui.change_password.ChangePassword;
import com.example.reminisce_ticketing.ui.home.HomeFragment;
import com.example.reminisce_ticketing.ui.scan_with_camera.ScanWithCamera;
import com.example.reminisce_ticketing.utils.Utils;


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
        Utils.hideKeyBoard(this);
    }


    private void initView() {
        //        findViewById(R.id.iv_notification).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Notification.class);
//                startActivity(intent);
//            }
//        });

        binding.menuButton.setOnClickListener(v -> {
            Utils.hideKeyBoard(this);
            binding.drawerLayout.open();
        });

        binding.nvItem.homeItem.setOnClickListener(v -> {
            // setMenuColor();
            // binding.nvItem.ivHome.setColorFilter(ContextCompat.getColor(this, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
            //  binding.nvItem.tvHome.setTextColor(getResources().getColor(R.color.white));
            binding.drawerLayout.close();
            viewModel.openFragment(HomeFragment.class, Constants.HOME_FRAGMENTS);
            Utils.hideKeyBoard(this);
        });
        binding.nvItem.logout.setOnClickListener(v -> {
            //  setMenuColor();
            //   binding.nvItem.ivLogOut.setColorFilter(ContextCompat.getColor(this, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
            //  binding.nvItem.tvLogOut.setTextColor(getResources().getColor(R.color.white));
            binding.drawerLayout.close();
            viewModel.showLogoutDialog();
            Utils.hideKeyBoard(this);
        });
        binding.nvItem.scanItem.setOnClickListener(v -> {
            // setMenuColor();
            // binding.nvItem.ivScanner.setColorFilter(ContextCompat.getColor(this, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
            //  binding.nvItem.tvScanner.setTextColor(getResources().getColor(R.color.white));
            binding.drawerLayout.close();
            Intent intent = new Intent(MainActivity.this, ScanWithCamera.class);
            startActivity(intent);
            Utils.hideKeyBoard(this);
        });
        binding.nvItem.changePassword.setOnClickListener(v -> {
            // setMenuColor();
            //  binding.nvItem.ivChangePassword.setColorFilter(ContextCompat.getColor(this, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
            // binding.nvItem.tvChangePassword.setTextColor(getResources().getColor(R.color.white));
            binding.drawerLayout.close();
            Intent intent = new Intent(MainActivity.this, ChangePassword.class);
            startActivity(intent);
            Utils.hideKeyBoard(this);
        });
    }

    public void setMenuColor() {
        binding.nvItem.ivHome.setColorFilter(ContextCompat.getColor(this, R.color.colorBlue), android.graphics.PorterDuff.Mode.SRC_IN);
        binding.nvItem.tvHome.setTextColor(getResources().getColor(R.color.colorBlue));

        binding.nvItem.ivScanner.setColorFilter(ContextCompat.getColor(this, R.color.colorBlue), android.graphics.PorterDuff.Mode.SRC_IN);
        binding.nvItem.tvScanner.setTextColor(getResources().getColor(R.color.colorBlue));

        binding.nvItem.ivChangePassword.setColorFilter(ContextCompat.getColor(this, R.color.colorBlue), android.graphics.PorterDuff.Mode.SRC_IN);
        binding.nvItem.tvChangePassword.setTextColor(getResources().getColor(R.color.colorBlue));

        binding.nvItem.ivLogOut.setColorFilter(ContextCompat.getColor(this, R.color.colorBlue), android.graphics.PorterDuff.Mode.SRC_IN);
        binding.nvItem.tvLogOut.setTextColor(getResources().getColor(R.color.colorBlue));
    }

}