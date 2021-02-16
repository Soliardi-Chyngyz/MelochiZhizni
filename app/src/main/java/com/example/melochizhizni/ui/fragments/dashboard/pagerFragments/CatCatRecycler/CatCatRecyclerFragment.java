package com.example.melochizhizni.ui.fragments.dashboard.pagerFragments.CatCatRecycler;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.melochizhizni.R;
import com.example.melochizhizni.data.models.Item;
import com.example.melochizhizni.ui.adapter.CatCatItemAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static com.example.melochizhizni.data.ConstantKeys.PARENT_NAME;

public class CatCatRecyclerFragment extends Fragment implements CatCatItemAdapter.OnItemClickListener {
    private FirebaseFirestore fb;
    private RecyclerView recyclerView;
    private CatCatItemAdapter itemAdapter;
    private CatCatRecyclerViewModel vModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cat_cat_recycler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fb = FirebaseFirestore.getInstance();
        vModel = new ViewModelProvider(requireActivity()).get(CatCatRecyclerViewModel.class);
        init(view);
        getTitleFromCatCatFragment(view);

        view.findViewById(R.id.cat_cat_rec_back).setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            navController.navigateUp();
        });
    }

    private void getTitleFromCatCatFragment(View view) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String parentName = bundle.getString(PARENT_NAME);
            getDataFromFB(view, parentName);
        }
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.cat_cat_recycler_view);
    }

    private void getDataFromFB(View view, String parentName) {
        vModel.setData(fb, parentName);
        vModel.getData().observe(getViewLifecycleOwner(), items -> {
            view.findViewById(R.id.progress_cat_cat_recycler).setVisibility(View.GONE);
            itemAdapter = new CatCatItemAdapter(items, this);
            recyclerView.setAdapter(itemAdapter);
        });
    }


    @Override
    public void onItemClicked(Item v, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("item", v);
        bundle.putString("article", v.getArticle());
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_catCatRecyclerFragment_to_itemFragment, bundle);
    }
}