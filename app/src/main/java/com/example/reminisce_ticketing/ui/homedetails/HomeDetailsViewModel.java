package com.example.reminisce_ticketing.ui.homedetails;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;

import com.example.reminisce_ticketing.MainActivity;
import com.example.reminisce_ticketing.SharedPref;
import com.example.reminisce_ticketing.apiservice.ApiInterface;
import com.example.reminisce_ticketing.apiservice.RetrofitClient;
import com.example.reminisce_ticketing.databinding.ActivityHomeDetailsBinding;
import com.example.reminisce_ticketing.databinding.ActivityLoginBinding;
import com.example.reminisce_ticketing.model.EventDetailsModel;
import com.example.reminisce_ticketing.model.UserLoginReq;
import com.example.reminisce_ticketing.model.UserLoginRespo;
import com.example.reminisce_ticketing.ui.scan_with_camera.ScanWithCamera;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeDetailsViewModel extends ViewModel {
    ActivityHomeDetailsBinding binding;
    FragmentActivity activity;

    public <T> HomeDetailsViewModel(T binding, FragmentActivity activity) {
        this.binding = ((ActivityHomeDetailsBinding) binding);
        this.activity = activity;

        initView();
    }

    private void initView() {
        if (activity.getIntent().hasExtra("slug")) {
            String slug = activity.getIntent().getStringExtra("slug");
            getDetails(slug);
        }

        binding.scanTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ScanWithCamera.class);
                activity.startActivity(intent);
            }
        });
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onBackPressed();
            }
        });
    }
    private void getDetails(String slug) {

        UserLoginReq userLoginReq = new UserLoginReq();
        userLoginReq.slug = slug;
        ApiInterface service = RetrofitClient.getClient(activity).create(ApiInterface.class);
        Call<EventDetailsModel> call = service.getEventDetails(userLoginReq);

        call.enqueue(new Callback<EventDetailsModel>() {
            @Override
            public void onResponse(Call<EventDetailsModel> call, Response<EventDetailsModel> response) {
                Log.e("Tag", "Response" + response.body());
                if (response.isSuccessful()) {
                    EventDetailsModel eventDetailsModel = response.body();
                    binding.txtName.setText(eventDetailsModel.data.name);
                    binding.description.setText(eventDetailsModel.data.description);
                    binding.txtDate.setText(eventDetailsModel.data.eventEndDate);
                    Log.e("Tag", "Response" + response.body());
                }
            }

            @Override
            public void onFailure(Call<EventDetailsModel> call, Throwable t) {
                Log.e("Tag", "Response" + t.getMessage());
            }
        });
    }
}
