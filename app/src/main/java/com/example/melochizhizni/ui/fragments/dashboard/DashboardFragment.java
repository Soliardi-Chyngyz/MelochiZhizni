package com.example.melochizhizni.ui.fragments.dashboard;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.melochizhizni.R;
import com.example.melochizhizni.databinding.FragmentDashboardBinding;
import com.example.melochizhizni.ui.adapter.FragmentAdapter;
import com.example.melochizhizni.ui.fragments.dashboard.pagerFragments.CatCategory.CatCategoryFragment;
import com.example.melochizhizni.ui.fragments.dashboard.pagerFragments.SelectedFragment.CategorySelectedFragment;
import com.example.melochizhizni.ui.fragments.dashboard.pagerFragments.CategoryTopFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import static com.example.melochizhizni.data.ConstantKeys.TRANSFER_YES;

public class DashboardFragment extends Fragment {
    private ViewPager2 viewPager;
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_dashboard, container, false));
        assert binding != null;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.view_pager_dash);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(this, getFragmentList());
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
            viewPager.setUserInputEnabled(false);
        }
        viewPager.setAdapter(fragmentAdapter);
        init(view);
        getTransfer();
    }

    private void getTransfer() {
        if (getArguments() != null) {
            boolean transfer = getArguments().getBoolean(TRANSFER_YES);
            if (transfer)
                viewPager.setCurrentItem(2, true);
        }
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
