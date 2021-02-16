package com.example.melochizhizni.ui.fragments.dashboard.pagerFragments.item;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.melochizhizni.App;
import com.example.melochizhizni.R;
import com.example.melochizhizni.data.models.Item;
import com.example.melochizhizni.databinding.FragmentItemBinding;

public class ItemFragment extends Fragment {
    private FragmentItemBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_item, container, false));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Item item = (Item) getArguments().getSerializable("item");
        if (item != null) {
            init(item);
            onStar(item);
        }
    }

    private void onStar(Item item) {
        binding.catItemStar.setOnClickListener(v -> {
            v.setBackgroundResource(R.drawable.ic_star_2);
            App.database.itemSelectedDao().addItem(item);
            Toast.makeText(getContext(), "Добавлен в избранное", Toast.LENGTH_SHORT).show();
        });
    }

    private void init(Item item) {
        Glide.with(getContext())
                .load(item.getPhoto())
                .into(binding.catItemImg);
        binding.setModel(item);

        binding.catItemAddToBasket.setOnClickListener(v -> {
            App.database.itemBasketDao().addItem(item);
            transfer();
        });

        binding.catItemBack.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            navController.navigateUp();
        });
    }

    private void transfer() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_itemFragment_to_catCategoryFragment);
    }
}