package com.example.reminisce_ticketing.ui.change_password;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;

import com.example.reminisce_ticketing.apiservice.ApiInterface;
import com.example.reminisce_ticketing.apiservice.RetrofitClient;
import com.example.reminisce_ticketing.databinding.ChangePasswordBinding;
import com.example.reminisce_ticketing.model.ChangePasswordReq;
import com.example.reminisce_ticketing.model.ChangePasswordRespo;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordViewModel extends ViewModel {
    ChangePasswordBinding binding;
    Activity activity;


    public <T> ChangePasswordViewModel(T binding, Activity activity) {
        this.binding = ((ChangePasswordBinding) binding);
        this.activity = activity;
        initView();
    }

    private void initView() {
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onBackPressed();
            }
        });
        binding.changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword();
            }
        });
    }

    private void changePassword() {
        binding.progressBar.setVisibility(View.VISIBLE);
        ChangePasswordReq changePasswordReq = new ChangePasswordReq();
        changePasswordReq.oldPassword = binding.oldPassword.getText().toString().trim();
        changePasswordReq.newPassword = binding.newPassword.getText().toString().trim();
        changePasswordReq.confirmPassword = binding.confirmPassword.getText().toString().trim();

        ApiInterface service = RetrofitClient.getClient(activity).create(ApiInterface.class);
        Call<ChangePasswordRespo> call = service.changePassword(changePasswordReq);

        call.enqueue(new Callback<ChangePasswordRespo>() {
            @Override
            public void onResponse(Call<ChangePasswordRespo> call, Response<ChangePasswordRespo> response) {
                binding.progressBar.setVisibility(View.GONE);

                Log.e("Tag", "Response" + response.body());
                if (response.isSuccessful()) {
                    ChangePasswordRespo changePasswordRespo = response.body();
                    if (changePasswordRespo != null) {
                        if (changePasswordRespo.code == 200) {

                            Object field = changePasswordRespo.message;
                            if (field instanceof String) {
                                String value = (String) field;
                                Toast.makeText(activity, value, Toast.LENGTH_SHORT).show();
                                binding.oldPassword.setText("");
                                binding.newPassword.setText("");
                                binding.confirmPassword.setText("");
                                binding.newPasswordError.setVisibility(View.GONE);
                                binding.oldPasswordError.setVisibility(View.GONE);
                                binding.confirmPasswordError.setVisibility(View.GONE);
                            }
//                            Log.e("Tag", "Response" + changePasswordRespo.message.confirmPassword.get(0));

                        } else if (changePasswordRespo.code == 401) {
                            Object field = changePasswordRespo.message;
                            if (changePasswordRespo.message instanceof Map) {
                                Map map = (Map) field;
                                Log.e("Tag", "Response" + map);
                                if (map.containsKey("old_password")) {
                                    List<String> oldPasswordErrors = (List<String>) map.get("old_password");
                                    binding.oldPasswordError.setVisibility(View.VISIBLE);
                                    binding.oldPasswordError.setText(oldPasswordErrors.get(0));
                                } else {
                                    binding.oldPasswordError.setVisibility(View.GONE);
                                }
                                if (map.containsKey("new_password")) {
                                    List<String> newPasswordErrors = (List<String>)map.get("new_password");
                                    binding.newPasswordError.setVisibility(View.VISIBLE);
                                    binding.newPasswordError.setText(newPasswordErrors.get(0));
                                }else {
                                    binding.newPasswordError.setVisibility(View.GONE);
                                }
                                if (map.containsKey("confirm_password")) {
                                    List<String> confirmPasswordErrors = (List<String>)map.get("confirm_password");
                                    binding.confirmPasswordError.setVisibility(View.VISIBLE);
                                    binding.confirmPasswordError.setText(confirmPasswordErrors.get(0));
                                }else {
                                    binding.confirmPasswordError.setVisibility(View.GONE);
                                }

                            }
//                            if (changePasswordRespo.message.oldPassword!=null){
//                                binding.oldPasswordError.setVisibility(View.VISIBLE);
//                                binding.oldPasswordError.setText(changePasswordRespo.message.oldPassword.get(0));
//                            }else {
//                                binding.oldPasswordError.setVisibility(View.GONE);
//                            }
//                            if (changePasswordRespo.message.newPassword!=null){
//                                binding.newPasswordError.setVisibility(View.VISIBLE);
//                                binding.newPasswordError.setText(changePasswordRespo.message.newPassword.get(0));
//                            }else {
//                                binding.newPasswordError.setVisibility(View.GONE);
//                            }
//                            if (changePasswordRespo.message.confirmPassword!=null){
//                                binding.confirmPasswordError.setVisibility(View.VISIBLE);
//                                binding.confirmPasswordError.setText(changePasswordRespo.message.confirmPassword.get(0));
//                            }else {
//                                binding.confirmPasswordError.setVisibility(View.GONE);
//                            }
                        } else if (changePasswordRespo.code == 404) {
                            Object field = changePasswordRespo.message;
                            if (field instanceof String) {
                                String value = (String) field;
                                Toast.makeText(activity, value, Toast.LENGTH_SHORT).show();
                                binding.newPasswordError.setVisibility(View.GONE);
                                binding.oldPasswordError.setVisibility(View.GONE);
                                binding.confirmPasswordError.setVisibility(View.GONE);
                            }

                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<ChangePasswordRespo> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

}
