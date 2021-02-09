package com.example.melochizhizni.ui.fragments.dashboard.pagerFragments.item;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.melochizhizni.App;
import com.example.melochizhizni.R;
import com.example.melochizhizni.data.models.Item;
import com.example.melochizhizni.data.room.Database;
import com.example.melochizhizni.databinding.FragmentItemBinding;
import com.example.melochizhizni.ui.fragments.dashboard.pagerFragments.CatCategoryFragment;

import java.io.File;

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

        init();

    }

    private void init() {
        Item item = (Item) getArguments().getSerializable("item");
        if (item != null) {
            Glide.with(getContext())
                    .load(item.getPhoto())
                    .into(binding.catItemImg);
            binding.setModel(item);
        }
        binding.catItemAddToBasket.setOnClickListener(v -> {
//            App.database.itemDao().addItem(item);
            transfer();
        });
    }

    private void transfer() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_itemFragment_to_navigation_category);
    }
}