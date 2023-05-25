package com.example.reminisce_ticketing.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.reminisce_ticketing.MainActivity;
import com.example.reminisce_ticketing.R;
import com.example.reminisce_ticketing.SharedPref;
import com.example.reminisce_ticketing.adapter.HomeItemAdapter;
import com.example.reminisce_ticketing.apiservice.ApiInterface;
import com.example.reminisce_ticketing.apiservice.RetrofitClient;
import com.example.reminisce_ticketing.auth.login.LoginActivity;
import com.example.reminisce_ticketing.model.EventListModel;
import com.example.reminisce_ticketing.model.HomeItemModel;
import com.example.reminisce_ticketing.model.UserLoginRespo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    private HomeItemAdapter adapter;
    private List<EventListModel.Data> itemList;
    LinearLayout searchText;
    EditText etSearch;


    @Override
    public void onResume() {
        super.onResume();
        progressBar.setVisibility(View.VISIBLE);
        ApiInterface service = RetrofitClient.getClient(getContext()).create(ApiInterface.class);
        Call<EventListModel> call = service.getEventList();

        call.enqueue(new Callback<EventListModel>() {
            @Override
            public void onResponse(Call<EventListModel> call, Response<EventListModel> response) {
                Log.e("Tag", "Response" + response.body());
                if (response.isSuccessful()) {
                    Log.e("EventList","data"+response.body());
                    if (response.body()!=null){
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        itemList=response.body().data;
                        adapter = new HomeItemAdapter(itemList,getContext());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(adapter);

                    }

                }else {
                    progressBar.setVisibility(View.GONE);
                    SharedPref.saveBoolean(SharedPref.IsUserLogin, false, getContext());
                    SharedPref.saveUserToken(null,getContext());
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(getContext(), "Session Expired", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EventListModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        searchText = view.findViewById(R.id.searchText);
        etSearch = view.findViewById(R.id.et_search);
        searchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = etSearch.getText().toString();
                adapter.search(query);
            }
        });


//        itemList = new ArrayList<>();
//        itemList.add(new HomeItemModel("Item 1", "Description 1"));
//        itemList.add(new HomeItemModel("Item 2", "Description 2"));
//        itemList.add(new HomeItemModel("Item 3", "Description 3"));
//        itemList.add(new HomeItemModel("Item 1", "Description 1"));
//        itemList.add(new HomeItemModel("Item 2", "Description 2"));
//        itemList.add(new HomeItemModel("Item 3", "Description 3"));
//        itemList.add(new HomeItemModel("Item 1", "Description 1"));
//        itemList.add(new HomeItemModel("Item 2", "Description 2"));
//        itemList.add(new HomeItemModel("Item 3", "Description 3"));
//        itemList.add(new HomeItemModel("Item 1", "Description 1"));
//        itemList.add(new HomeItemModel("Item 2", "Description 2"));
//        itemList.add(new HomeItemModel("Item 3", "Description 3"));
//        itemList.add(new HomeItemModel("Item 1", "Description 1"));
//        itemList.add(new HomeItemModel("Item 2", "Description 2"));
//        itemList.add(new HomeItemModel("Item 3", "Description 3"));

        // Create and set the adapter
        return view;

    }
}