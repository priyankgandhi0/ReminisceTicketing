package com.example.reminisce_ticketing.auth.forget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.reminisce_ticketing.R;
import com.example.reminisce_ticketing.auth.login.LoginActivity;
import com.example.reminisce_ticketing.auth.login.LoginViewModel;
import com.example.reminisce_ticketing.databinding.ActivityForgetBinding;
import com.example.reminisce_ticketing.factory.GeneralViewModelFactory;

public class ForgetActivity extends AppCompatActivity {
    ActivityForgetBinding binding;
    ForgetViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_forget);
        viewModel = new ViewModelProvider(this, new GeneralViewModelFactory<>(binding, this)).get(ForgetViewModel.class);


    }
}