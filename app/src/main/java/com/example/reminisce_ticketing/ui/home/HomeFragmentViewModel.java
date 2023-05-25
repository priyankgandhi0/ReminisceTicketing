package com.example.reminisce_ticketing.ui.home;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reminisce_ticketing.SharedPref;
import com.example.reminisce_ticketing.adapter.HomeItemAdapter;
import com.example.reminisce_ticketing.apiservice.ApiInterface;
import com.example.reminisce_ticketing.apiservice.RetrofitClient;
import com.example.reminisce_ticketing.auth.login.LoginActivity;
import com.example.reminisce_ticketing.databinding.FragmentHomeBinding;
import com.example.reminisce_ticketing.model.EventListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragmentViewModel extends ViewModel {
    FragmentHomeBinding binding;
    FragmentActivity activity;
    private HomeItemAdapter adapter;
    private List<EventListModel.Data> itemList;
    public <T> HomeFragmentViewModel(T binding, FragmentActivity activity) {
        this.binding = ((FragmentHomeBinding) binding);
        this.activity = activity;
        initView();
    }


    private void initView() {
        getEventList();
        binding.searchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Clicked", Toast.LENGTH_SHORT).show();
                String query = binding.etSearch.getText().toString();
                adapter.search(query);
            }
        });

    }

    private void getEventList() {
        binding.progressBar.setVisibility(View.VISIBLE);
        ApiInterface service = RetrofitClient.getClient(activity).create(ApiInterface.class);
        Call<EventListModel> call = service.getEventList();

        call.enqueue(new Callback<EventListModel>() {
            @Override
            public void onResponse(Call<EventListModel> call, Response<EventListModel> response) {
                Log.e("Tag", "Response" + response.body());
                if (response.isSuccessful()) {
                    Log.e("EventList","data"+response.body());
                    if (response.body()!=null){
                        binding.progressBar.setVisibility(View.GONE);
                        binding.recyclerView.setVisibility(View.VISIBLE);
                        itemList=response.body().data;
                        adapter = new HomeItemAdapter(itemList,activity);
                        binding.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                        binding.recyclerView.setAdapter(adapter);
                    }

                }else {
                    binding.progressBar.setVisibility(View.GONE);
                    SharedPref.saveBoolean(SharedPref.IsUserLogin, false, activity);
                    SharedPref.saveUserToken(null,activity);
                    Intent intent = new Intent(activity, LoginActivity.class);
                    activity.startActivity(intent);
                    Toast.makeText(activity, "Session Expired", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EventListModel> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }
}
