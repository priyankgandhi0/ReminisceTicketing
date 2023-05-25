package com.example.reminisce_ticketing.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.reminisce_ticketing.R;
import com.example.reminisce_ticketing.databinding.FragmentHomeBinding;
import com.example.reminisce_ticketing.factory.GeneralViewModelFactory;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    HomeFragmentViewModel viewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this, new GeneralViewModelFactory<>(binding, getActivity())).get(HomeFragmentViewModel.class);
        binding.setViewModel(viewModel);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setFragment(this);
        return binding.getRoot();

    }
}