package com.example.reminisce_ticketing.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reminisce_ticketing.R;
import com.example.reminisce_ticketing.adapter.HomeItemAdapter;
import com.example.reminisce_ticketing.model.HomeItemModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    private HomeItemAdapter adapter;
    private List<HomeItemModel> itemList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        itemList = new ArrayList<>();
        itemList.add(new HomeItemModel("Item 1", "Description 1"));
        itemList.add(new HomeItemModel("Item 2", "Description 2"));
        itemList.add(new HomeItemModel("Item 3", "Description 3"));
        itemList.add(new HomeItemModel("Item 1", "Description 1"));
        itemList.add(new HomeItemModel("Item 2", "Description 2"));
        itemList.add(new HomeItemModel("Item 3", "Description 3"));
        itemList.add(new HomeItemModel("Item 1", "Description 1"));
        itemList.add(new HomeItemModel("Item 2", "Description 2"));
        itemList.add(new HomeItemModel("Item 3", "Description 3"));
        itemList.add(new HomeItemModel("Item 1", "Description 1"));
        itemList.add(new HomeItemModel("Item 2", "Description 2"));
        itemList.add(new HomeItemModel("Item 3", "Description 3"));
        itemList.add(new HomeItemModel("Item 1", "Description 1"));
        itemList.add(new HomeItemModel("Item 2", "Description 2"));
        itemList.add(new HomeItemModel("Item 3", "Description 3"));

        // Create and set the adapter
        adapter = new HomeItemAdapter(itemList);
        recyclerView.setAdapter(adapter);
        return view;

    }
}