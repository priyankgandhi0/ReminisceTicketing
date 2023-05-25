package com.example.reminisce_ticketing.ui.homedetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.reminisce_ticketing.R;
import com.example.reminisce_ticketing.databinding.ActivityHomeDetailsBinding;
import com.example.reminisce_ticketing.factory.GeneralViewModelFactory;

public class HomeDetailsActivity extends AppCompatActivity {

    ActivityHomeDetailsBinding binding;
    HomeDetailsViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_details);
        viewModel = new ViewModelProvider(this, new GeneralViewModelFactory<>(binding, this)).get(HomeDetailsViewModel.class);

    }
}