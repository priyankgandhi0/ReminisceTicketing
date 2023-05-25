package com.example.reminisce_ticketing.auth.forget;

import android.content.Intent;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;

import com.example.reminisce_ticketing.R;
import com.example.reminisce_ticketing.apiservice.ApiInterface;
import com.example.reminisce_ticketing.apiservice.RetrofitClient;
import com.example.reminisce_ticketing.auth.login.LoginActivity;
import com.example.reminisce_ticketing.databinding.ActivityForgetBinding;
import com.example.reminisce_ticketing.listener.onClickUnderLine;
import com.example.reminisce_ticketing.model.ForgetRespo;
import com.example.reminisce_ticketing.model.UserLoginReq;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetViewModel extends ViewModel {
    ActivityForgetBinding binding;
    FragmentActivity activity;

    public <T> ForgetViewModel(T binding, FragmentActivity activity) {
        this.binding = ((ActivityForgetBinding) binding);
        this.activity = activity;

        initView();
    }

    public static void addUnderColorLine(String text, TextView textView, int start, int end, final onClickUnderLine listener, int color, boolean setUnderlineText) {
        if (text.length() != 0) {
            Spannable wordtoSpan = new SpannableString(text);

            ClickableSpan clickableSpan3 = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    widget.setBackgroundColor(Color.TRANSPARENT);

                    if (listener != null) {
                        listener.onClickUnderLine();
                    }
                }

                @Override
                public void updateDrawState(@NonNull TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setColor(color);
                    ds.setUnderlineText(setUnderlineText);
                    if (listener != null) {
                        listener.updateDrawState(ds);
                    }
                }
            };
            wordtoSpan.setSpan(clickableSpan3, start, end, wordtoSpan.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setText(wordtoSpan);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    private void initView() {

        addUnderColorLine(activity.getString(R.string.log_in), binding.tvLogin, 0, activity.getString(R.string.log_in).length(), new onClickUnderLine() {
            @Override
            public void onClickUnderLine() {

            }

            @Override
            public void updateDrawState(TextPaint ds) {

            }
        }, activity.getColor(R.color.colorBlue), true);
        binding.tvLogin.setOnClickListener(view -> activity.onBackPressed());
        binding.btnRequest.setOnClickListener(new View.OnClickListener() {
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
