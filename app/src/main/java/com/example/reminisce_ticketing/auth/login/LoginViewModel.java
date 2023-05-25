package com.example.reminisce_ticketing.auth.login;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.reminisce_ticketing.MainActivity;
import com.example.reminisce_ticketing.utils.SharedPref;
import com.example.reminisce_ticketing.apiservice.ApiInterface;
import com.example.reminisce_ticketing.apiservice.RetrofitClient;
import com.example.reminisce_ticketing.auth.forget.ForgetActivity;
import com.example.reminisce_ticketing.databinding.ActivityLoginBinding;
import com.example.reminisce_ticketing.model.UserLoginReq;
import com.example.reminisce_ticketing.model.UserLoginRespo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    ActivityLoginBinding binding;
    Activity activity;

    public <T> LoginViewModel(T binding, Activity activity) {
        this.binding = ((ActivityLoginBinding) binding);
        this.activity = activity;

        initView();
    }

    private void initView() {
        binding.forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ForgetActivity.class);
                activity.startActivity(intent);
//                activity.finish();
            }
        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.etPassword.getText().toString().isEmpty() || binding.etEmail.getText().toString().isEmpty()) {
                    Toast.makeText(activity, "Please enter valid fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                CallApi(binding.etEmail, binding.etPassword);

            }
        });
    }

    private void CallApi(EditText etEmail, EditText etPassword) {


        UserLoginReq userLoginReq = new UserLoginReq();
        userLoginReq.email = etEmail.getText().toString().trim();
        userLoginReq.password = etPassword.getText().toString().trim();


        ApiInterface service = RetrofitClient.getClient(activity).create(ApiInterface.class);
        Call<UserLoginRespo> call = service.userLogin(userLoginReq);

        call.enqueue(new Callback<UserLoginRespo>() {
            @Override
            public void onResponse(Call<UserLoginRespo> call, Response<UserLoginRespo> response) {
                Log.e("Tag", "Response" + response.body());
                if (response.isSuccessful()) {
                    if (response.body()!=null&& response.body().code==200){
                        SharedPref.saveBoolean(SharedPref.IsUserLogin, true, activity);
                        SharedPref.saveUserToken(response.body().data.userToken,activity);
                        Intent intent = new Intent(activity, MainActivity.class);
                        intent.putExtra("LoginData", response.body().data.name.toString());
                        activity.startActivity(intent);
                        activity.finish();
                    }else if (response.body().code==401){
                        Toast.makeText(activity, "Credentials not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserLoginRespo> call, Throwable t) {
                SharedPref.saveBoolean(SharedPref.IsUserLogin, false, activity);
            }
        });
    }
}