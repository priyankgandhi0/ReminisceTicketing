package com.example.reminisce_ticketing;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.example.reminisce_ticketing.apiservice.ApiInterface;
import com.example.reminisce_ticketing.apiservice.RetrofitClient;
import com.example.reminisce_ticketing.auth.login.LoginActivity;
import com.example.reminisce_ticketing.constant.Constants;
import com.example.reminisce_ticketing.databinding.ActivityMainBinding;
import com.example.reminisce_ticketing.ui.home.HomeFragment;
import com.example.reminisce_ticketing.utils.FragmentUtils;
import com.example.reminisce_ticketing.utils.SharedPref;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {
    ActivityMainBinding binding;
    Activity activity;

    public <T> MainActivityViewModel(T binding, Activity activity) {
        this.binding = ((ActivityMainBinding) binding);
        this.activity = activity;
        initView();
    }

    private void initView() {
        openFragment(HomeFragment.class, Constants.HOME_FRAGMENTS);

    }


    public void openFragment(Class<? extends Fragment> fragmentClass,String tag) {
        FragmentUtils.loadFragmentInRoot((MainActivity)activity,
                R.id.fragmentContainer,
                fragmentClass,
                new Bundle(),
                tag);
    }


    public void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");

        builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logOut();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void logOut() {
        binding.progressBar.setVisibility(View.VISIBLE);
        ApiInterface service = RetrofitClient.getClient(activity).create(ApiInterface.class);
        Call<ResponseBody> call = service.logOut();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                binding.progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()){
                    SharedPref.saveBoolean(SharedPref.IsUserLogin, false, activity);
                    SharedPref.saveUserToken(null,activity);
                    Intent intent = new Intent(activity, LoginActivity.class);
                    activity.startActivity(intent);
                    activity.finish();
                    Log.e("Tag", "Response" + response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

}
