package com.example.reminisce_ticketing.auth.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import com.example.reminisce_ticketing.R;
import com.example.reminisce_ticketing.databinding.ActivityLoginBinding;
import com.example.reminisce_ticketing.factory.GeneralViewModelFactory;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    LoginViewModel viewModel;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        viewModel = new ViewModelProvider(this, new GeneralViewModelFactory<>(binding, this)).get(LoginViewModel.class);
    }
}