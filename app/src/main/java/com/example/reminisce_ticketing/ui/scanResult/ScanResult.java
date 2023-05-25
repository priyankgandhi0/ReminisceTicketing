package com.example.reminisce_ticketing.ui.scanResult;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.reminisce_ticketing.MainActivityViewModel;
import com.example.reminisce_ticketing.R;
import com.example.reminisce_ticketing.auth.forget.ForgetViewModel;
import com.example.reminisce_ticketing.databinding.ActivityForgetBinding;
import com.example.reminisce_ticketing.databinding.ActivityScanResultBinding;
import com.example.reminisce_ticketing.factory.GeneralViewModelFactory;

public class ScanResult extends AppCompatActivity {
    ActivityScanResultBinding binding;
    ScanResultViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this, new GeneralViewModelFactory<>(binding, this)).get(ScanResultViewModel.class);

    }
}