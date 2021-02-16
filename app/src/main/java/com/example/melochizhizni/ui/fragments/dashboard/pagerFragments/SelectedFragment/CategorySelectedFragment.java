package com.example.melochizhizni.ui.fragments.dashboard.pagerFragments.SelectedFragment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.melochizhizni.App;
import com.example.melochizhizni.R;
import com.example.melochizhizni.data.models.CumulativePoints;
import com.example.melochizhizni.data.models.Item;
import com.example.melochizhizni.databinding.FragmentCategorySelectedBinding;
import com.example.melochizhizni.extensions.MineCountDownTimer;
import com.example.melochizhizni.ui.adapter.CatSelectedItemAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategorySelectedFragment extends Fragment implements CatSelectedItemAdapter.OnItemClickListener {
    private CatSelectedItemAdapter adapter;
    private List<Item> list = new ArrayList<>();
    private FragmentCategorySelectedBinding binding;
    private CatSelectedViewModel vModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_category_selected, container, false));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vModel = new ViewModelProvider(this).get(CatSelectedViewModel.class);
        getRoomDataOfUser();
        remove();
        progressBar();
    }

    private void progressBar() {
        new MineCountDownTimer(1200, 1000) {
            @Override
            public void onFinish() {
                binding.selectedProgress.setVisibility(View.GONE);
            }
        }.start();
    }

    private void remove() {
        binding.selectedTrash.setOnClickListener(v -> new AlertDialog.Builder(requireContext())
                .setTitle("Подтвердите удаление всего списка")
                .setPositiveButton("Подтверждаю", (dialog, which) -> {
                    vModel.setDeleteAdapter(true);
                })
                .setNegativeButton("Нет", null)
                .show());

        vModel.getConfirmValue().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean)
                adapter.deleteAll();
        });
    }

    private void getRoomDataOfUser() {
        list = App.database.itemSelectedDao().getAllItems();
        if (list != null) {
            binding.selectedText.setVisibility(View.GONE);
            adapter = new CatSelectedItemAdapter(list, this);
            binding.selectedRecycler.setAdapter(adapter);
        } else if (list.size() == 0) {
            binding.selectedText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClicked(Item v, int position) {

    }

    @Override
    public void onViewClicked(Item v, int position) {
        App.database.itemSelectedDao().deleteItem(v);
        adapter.deleteByPos(position);
    }
}