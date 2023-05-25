package com.example.reminisce_ticketing.factory;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.reminisce_ticketing.MainActivityViewModel;
import com.example.reminisce_ticketing.auth.forget.ForgetViewModel;
import com.example.reminisce_ticketing.auth.login.LoginViewModel;
import com.example.reminisce_ticketing.ui.change_password.ChangePasswordViewModel;
import com.example.reminisce_ticketing.ui.home.HomeFragmentViewModel;
import com.example.reminisce_ticketing.ui.homedetails.HomeDetailsViewModel;

public class GeneralViewModelFactory<T> implements ViewModelProvider.Factory {

    private T binding;
    FragmentActivity activity;

    public GeneralViewModelFactory(T binding, FragmentActivity activity) {
        this.binding = binding;
        this.activity = activity;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(binding, activity);
        }
        if (modelClass.isAssignableFrom(HomeDetailsViewModel.class)) {
            return (T) new HomeDetailsViewModel(binding, activity);
        }
        if (modelClass.isAssignableFrom(ChangePasswordViewModel.class)) {
            return (T) new ChangePasswordViewModel(binding, activity);
        } if (modelClass.isAssignableFrom(ForgetViewModel.class)) {
            return (T) new ForgetViewModel(binding, activity);
        }
        if (modelClass.isAssignableFrom(HomeFragmentViewModel.class)) {
            return (T) new HomeFragmentViewModel(binding, activity);
        }
        if (modelClass.isAssignableFrom(MainActivityViewModel.class)) {
            return (T) new MainActivityViewModel(binding, activity);
        }
        return ViewModelProvider.Factory.super.create(modelClass);
    }
}
