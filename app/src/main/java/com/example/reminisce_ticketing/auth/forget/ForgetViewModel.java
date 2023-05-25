package com.example.reminisce_ticketing.auth.forget;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;
import com.example.reminisce_ticketing.apiservice.ApiInterface;
import com.example.reminisce_ticketing.apiservice.RetrofitClient;
import com.example.reminisce_ticketing.auth.login.LoginActivity;
import com.example.reminisce_ticketing.databinding.ActivityForgetBinding;
import com.example.reminisce_ticketing.model.ForgetRespo;
import com.example.reminisce_ticketing.model.UserLoginReq;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetViewModel extends ViewModel {
    ActivityForgetBinding binding;
    Activity activity;

    public <T> ForgetViewModel(T binding, Activity activity) {
        this.binding = ((ActivityForgetBinding) binding);
        this.activity = activity;

        initView();
    }

    private void initView() {
        binding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, LoginActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
        });
        binding.forgetRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgetPasswordApiCall();
            }
        });
    }

    private void forgetPasswordApiCall() {
        UserLoginReq userLoginReq = new UserLoginReq();
        userLoginReq.email = binding.etEmail.getText().toString().trim();

        ApiInterface service = RetrofitClient.getClient(activity).create(ApiInterface.class);
        Call<ForgetRespo> call = service.forgetPassword(userLoginReq);

        call.enqueue(new Callback<ForgetRespo>() {
            @Override
            public void onResponse(Call<ForgetRespo> call, Response<ForgetRespo> response) {
                Log.e("Tag", "Response" + response.body());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().code == 200) {
                            Object field = response.body().message;
                            if (field instanceof String) {
                                String value = (String) field;
                                Toast.makeText(activity, value, Toast.LENGTH_SHORT).show();
                                binding.etEmail.setText("");
                                Log.e("Tag", "Response" + response.body());
                            }
                        }
                        if (response.body().code == 401) {
                            Object field = response.body().message;
                            if (response.body().message instanceof Map) {
                                Map map = (Map) field;
                                Log.e("Tag", "Response" + map);
                                if (map.containsKey("email")) {
                                    List<String> oldPasswordErrors = (List<String>) map.get("email");
                                    Toast.makeText(activity, oldPasswordErrors.get(0), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ForgetRespo> call, Throwable t) {
            }
        });
    }
}
