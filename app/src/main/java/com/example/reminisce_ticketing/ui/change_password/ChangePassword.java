package com.example.reminisce_ticketing.ui.change_password;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.reminisce_ticketing.R;
import com.example.reminisce_ticketing.databinding.ChangePasswordBinding;
import com.example.reminisce_ticketing.factory.GeneralViewModelFactory;
import com.example.reminisce_ticketing.ui.homedetails.HomeDetailsViewModel;

public class ChangePassword extends AppCompatActivity {

    ChangePasswordBinding binding;
    ChangePasswordViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.change_password);
        viewModel = new ViewModelProvider(this, new GeneralViewModelFactory<>(binding, this)).get(ChangePasswordViewModel.class);

    }
}