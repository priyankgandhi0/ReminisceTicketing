package com.example.reminisce_ticketing.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.reminisce_ticketing.utils.SharedPref;
import com.example.reminisce_ticketing.adapter.HomeItemAdapter;
import com.example.reminisce_ticketing.apiservice.ApiInterface;
import com.example.reminisce_ticketing.apiservice.RetrofitClient;
import com.example.reminisce_ticketing.auth.login.LoginActivity;
import com.example.reminisce_ticketing.databinding.FragmentHomeBinding;
import com.example.reminisce_ticketing.model.EventListModel;
import com.example.reminisce_ticketing.utils.Utils;
import com.google.gson.Gson;

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

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("","onTextChanged : "+s);
               // if (!s.toString().trim().isEmpty()) {
                    Log.e("","onTextChanged 1 : "+s);
                    String query = binding.etSearch.getText().toString();
                    adapter.search(query);
               // }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        binding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //events
                    Utils.hideKeyBoard(binding.etSearch);
                    String query = binding.etSearch.getText().toString();
                    adapter.search(query);
                    return true;
                }
                return false;
            }
        });

      /*  binding.tlSearch.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etSearch.getText().clear();
                if (searchItemsList.getValue() != null) {
                    searchItemsList.getValue().clear();
                }
                adapter.notifyDataSetChanged();
            }
        });*/
        binding.searchText.setOnClickListener(new View.OnClickListener() {@Override
            public void onClick(View view) {
            Utils.hideKeyBoard(activity);
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
                binding.progressBar.setVisibility(View.GONE);
                Log.e("Tag", "Response" + response.body());
                if (response.isSuccessful()) {
                    Log.e("EventList","data"+response.body());
                    if (response.body()!=null){

                        binding.recyclerView.setVisibility(View.VISIBLE);
                        itemList=response.body().data;
                        Log.e("","itemList : "+new Gson().toJson(itemList));
                        adapter = new HomeItemAdapter(itemList,activity);
                        binding.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                        binding.recyclerView.setAdapter(adapter);
                    }
                }else {

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
