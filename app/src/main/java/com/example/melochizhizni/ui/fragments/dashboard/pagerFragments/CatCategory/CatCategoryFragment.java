package com.example.melochizhizni.ui.fragments.dashboard.pagerFragments.CatCategory;

import android.os.Bundle;
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

import com.example.melochizhizni.R;
import com.example.melochizhizni.data.ExpandableListData;
import com.example.melochizhizni.data.models.ExpandableCategory;
import com.example.melochizhizni.ui.adapter.CatCatExpandableAdapter;

import java.util.ArrayList;

import static com.example.melochizhizni.data.ConstantKeys.PARENT_NAME;

public class CatCategoryFragment extends Fragment {

    private ExpandableListView expandableListView;
    private CatCatExpandableAdapter adapter;
    private ArrayList<ExpandableCategory> list = new ArrayList<>();
    private CatCatViewModel vModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cat_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        listeners();
        vModel = new ViewModelProvider(requireActivity()).get(CatCatViewModel.class);
    }

    private void listeners() {
        expandableListView.setOnGroupCollapseListener(groupPosition -> {
            expandableListView.animate();
        });

        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            String parentName = list.get(groupPosition).getItems().get(childPosition);
            Bundle bundle = new Bundle();
            bundle.putString(PARENT_NAME, parentName);
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.action_navigation_category_to_catCatRecyclerFragment, bundle);
            return true;
        });
    }

    private void init(View view) {
        expandableListView = view.findViewById(R.id.expand_list_View);
        list = ExpandableListData.getData();
        adapter = new CatCatExpandableAdapter(requireContext(), list);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnGroupExpandListener(groupPosition -> {
        });
    }
}