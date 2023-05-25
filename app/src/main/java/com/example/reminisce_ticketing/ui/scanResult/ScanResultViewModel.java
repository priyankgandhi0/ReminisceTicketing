package com.example.reminisce_ticketing.ui.scanResult;

import android.app.Activity;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.example.reminisce_ticketing.databinding.ActivityScanResultBinding;

public class ScanResultViewModel extends ViewModel {
    ActivityScanResultBinding binding;
    Activity activity;

    public <T> ScanResultViewModel(T binding, Activity activity) {
        this.binding = ((ActivityScanResultBinding) binding);
        this.activity = activity;

        initView();
    }

    private void initView() {
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onBackPressed();
            }
        });
    }
}
