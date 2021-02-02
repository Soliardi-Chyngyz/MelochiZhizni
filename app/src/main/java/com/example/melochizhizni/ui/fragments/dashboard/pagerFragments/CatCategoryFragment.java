package com.example.melochizhizni.ui.fragments.dashboard.pagerFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.melochizhizni.R;
import com.example.melochizhizni.data.ExpandableListData;
import com.example.melochizhizni.data.models.ExpandableCategory;
import com.example.melochizhizni.data.models.Item;
import com.example.melochizhizni.ui.adapter.CatCatExpandableAdapter;
import com.example.melochizhizni.ui.adapter.CatCatItemAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class CatCategoryFragment extends Fragment implements CatCatItemAdapter.OnItemClickListener {

    private ExpandableListView expandableListView;
    private CatCatExpandableAdapter adapter;
    private CatCatItemAdapter itemAdapter;
    private RecyclerView recyclerView;
    private ArrayList<ExpandableCategory> list = new ArrayList<>();
    private List<Item> listItem = new ArrayList<>();
    private CatCatViewModel vModel;
    private FirebaseFirestore fb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fb = FirebaseFirestore.getInstance();
        return inflater.inflate(R.layout.fragment_cat_category, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        listeners();
        initItemAdapter(view);
        vModel = new ViewModelProvider(requireActivity()).get(CatCatViewModel.class);
        getDataFromFB();
    }

    private void getDataFromFB() {
        vModel.setData(fb);
        vModel.getData().observe(requireActivity(), queryDocumentSnapshots -> {
            listItem.clear();
            for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                Item item = new Item(
                        doc.getString("title"),
                        doc.getString("article"),
                        doc.getString("photo"),
                        doc.getString("features"),
                        doc.getString("size"),
                        doc.getString("descriptions"),
                        doc.getString("sizeInBox"),
                        doc.getString("weight"),
                        doc.getString("price"),
                        doc.getString("category")
                );
                listItem.add(item);
            }
            if (listItem != null)
                itemAdapter = new CatCatItemAdapter(listItem, this);
            recyclerView.setAdapter(itemAdapter);
        });
    }

    private void initItemAdapter(View view) {
        recyclerView = view.findViewById(R.id.cat_cat_recycler_view);
    }

    private void listeners() {

        expandableListView.setOnGroupCollapseListener(groupPosition -> {
            Toast.makeText(getContext(),
                    list.get(groupPosition) + " List Collapsed.",
                    Toast.LENGTH_SHORT).show();
        });

        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            Toast.makeText(getContext(), " -> " + list.get(childPosition), Toast.LENGTH_SHORT).show();
            return false;
        });
    }

    private void init(View view) {
        expandableListView = view.findViewById(R.id.expand_list_View);
        list = ExpandableListData.getData();
        adapter = new CatCatExpandableAdapter(getContext(), list);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnGroupExpandListener(groupPosition -> {
            Toast.makeText(getContext(),
                    list.get(groupPosition) + " List Expanded.",
                    Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onItemClicked(Item v, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("item", v);
        bundle.putString("article", v.getArticle());
//        getParentFragmentManager().setFragmentResult("items", bundle);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_navigation_category_to_itemFragment, bundle);
    }
}