package com.example.reminisce_ticketing;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;

import com.example.reminisce_ticketing.apiservice.ApiInterface;
import com.example.reminisce_ticketing.apiservice.RetrofitClient;
import com.example.reminisce_ticketing.auth.login.LoginActivity;
import com.example.reminisce_ticketing.constant.Constants;
import com.example.reminisce_ticketing.databinding.ActivityLoginBinding;
import com.example.reminisce_ticketing.databinding.ActivityMainBinding;
import com.example.reminisce_ticketing.ui.change_password.ChangePassword;
import com.example.reminisce_ticketing.ui.home.HomeFragment;
import com.example.reminisce_ticketing.ui.notification.Notification;
import com.example.reminisce_ticketing.ui.scan_with_camera.ScanWithCamera;
import com.example.reminisce_ticketing.utils.FragmentUtils;
import com.google.android.material.navigation.NavigationView;

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
//    public void menuButton(){
//        binding.drawerLayout.open();
//    }

    public void iv_notification(){
        Intent intent = new Intent(activity, Notification.class);
        activity.startActivity(intent);
    }

    public void menuItem(View view){
        switch (view.getId()){
            case R.id.home_item:
                Log.e("Response","Click");
                binding.drawerLayout.close();
                openFragment(HomeFragment.class, Constants.HOME_FRAGMENTS);
            break;
            case R.id.logout:
                Log.e("Response","Click");
                binding.drawerLayout.close();
                showLogoutDialog();
            break;
            case R.id.scan_item:
                binding.drawerLayout.close();
                Intent intent = new Intent(activity, ScanWithCamera.class);
                activity.startActivity(intent);
            break;
            case R.id.change_password:
                binding.drawerLayout.close();
                Intent intent1 = new Intent(activity, ChangePassword.class);
                activity.startActivity(intent1);
            break;
        }

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
        ApiInterface service = RetrofitClient.getClient(activity).create(ApiInterface.class);
        Call<ResponseBody> call = service.logOut();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

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

            }
        });
    }
}
