package com.example.reminisce_ticketing.ui.scanResult;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.reminisce_ticketing.MainActivity;
import com.example.reminisce_ticketing.MainActivityViewModel;
import com.example.reminisce_ticketing.R;
import com.example.reminisce_ticketing.auth.forget.ForgetViewModel;
import com.example.reminisce_ticketing.constant.Constants;
import com.example.reminisce_ticketing.databinding.ActivityForgetBinding;
import com.example.reminisce_ticketing.databinding.ActivityScanResultBinding;
import com.example.reminisce_ticketing.factory.GeneralViewModelFactory;
import com.example.reminisce_ticketing.model.ValidationData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ScanResult extends AppCompatActivity {
    ActivityScanResultBinding binding;
    ScanResultViewModel viewModel;

    ValidationData data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_scan_result);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_scan_result);
        viewModel = new ViewModelProvider(this, new GeneralViewModelFactory<>(binding, this)).get(ScanResultViewModel.class);

        if (getIntent() != null){
            data = new GsonBuilder().create().fromJson(getIntent().getStringExtra(Constants.data),ValidationData.class);
            Log.e("","ValidationData : "+new Gson().toJson(data));
            setData();
        }

        binding.ivBack.setOnClickListener(v -> onBackPressed());

    }

    public void setData(){
        /*code -> 200 -> Success
        code -> 400 -> Already scanned
        code -> 404 -> Invalid Ticket*/
        if (data != null){
            if (data.getCode() == 200){
                binding.checkTicket.setImageDrawable(getResources().getDrawable(R.drawable.ic_valid_tag));
                binding.txtName.setText(R.string.valid_ticket);
            }else if (data.getCode() == 400){
                binding.checkTicket.setImageDrawable(getResources().getDrawable(R.drawable.ic_alredy_scan_tag));
                binding.txtName.setText(R.string.ticket_Invalid_Already_scanned_in);
            }else if (data.getCode() == 404){
                binding.checkTicket.setImageDrawable(getResources().getDrawable(R.drawable.ic_not_found));
                binding.txtName.setText(R.string.ticket_Invalid_Not_found_in_the_system);
            }
        }
    }

    @Override
    public void onBackPressed() {
       finish();
    }
}