package com.example.melochizhizni.ui.fragments.dashboard;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
import androidx.work.WorkManager;

import com.example.melochizhizni.R;
import com.example.melochizhizni.databinding.FragmentDashboardBinding;
import com.example.melochizhizni.ui.adapter.FragmentAdapter;
import com.example.melochizhizni.ui.fragments.dashboard.pagerFragments.CatCategoryFragment;
import com.example.melochizhizni.ui.fragments.dashboard.pagerFragments.CategorySelectedFragment;
import com.example.melochizhizni.ui.fragments.dashboard.pagerFragments.CategoryTopFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DashboardFragment extends Fragment {
    private ViewPager2 viewPager;
    private FragmentAdapter fragmentAdapter;
    private FragmentDashboardBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentAdapter = new FragmentAdapter(this, getFragmentList());
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_dashboard, container, false));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.view_pager_dash);
        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1){
            viewPager.setUserInputEnabled(false);
        }
        viewPager.setAdapter(fragmentAdapter);
        init(view);

    }

    private void init(View view) {
        TabLayout tabLayout = view.findViewById(R.id.my_tab_layout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, true, (tab, position) -> {
            if (position == 0) {
                tab.setText("Топ");
            } else if (position == 1) {
                tab.setText("Категории");
            } else if (position == 2) {
                tab.setText("Избранное");
            }
        });
        tabLayoutMediator.attach();
    }

    private List<Fragment> getFragmentList() {
        List<Fragment> list = new ArrayList<>();
        list.add(new CategoryTopFragment());
        list.add(new CatCategoryFragment());
        list.add(new CategorySelectedFragment());
        return list;
    }
}
