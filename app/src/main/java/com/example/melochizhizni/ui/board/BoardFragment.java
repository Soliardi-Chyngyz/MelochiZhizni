package com.example.melochizhizni.ui.board;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.melochizhizni.R;
import com.example.melochizhizni.Prefs;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

public class BoardFragment extends Fragment {
    private ViewPager viewPager;
    private TextView back, skip;
    private DotsIndicator dotsIndicator;
    private BoardAdapter boardAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        boardAdapter.setOnStartClickListener(() -> {
            openPhoneFragment();
        });

        back.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Клик", Toast.LENGTH_SHORT).show();
            if (viewPager.getCurrentItem() > 0)
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        });
        skip.setOnClickListener(v -> openPhoneFragment());
    }


    private void init(View view) {
        dotsIndicator = view.findViewById(R.id.dots_indicator);
        viewPager = view.findViewById(R.id.view_pager);
        boardAdapter = new BoardAdapter();
        viewPager.setAdapter(boardAdapter);
        dotsIndicator.setViewPager(viewPager);

        back = view.findViewById(R.id.back);
        skip = view.findViewById(R.id.skip);
    }

    private void openPhoneFragment() {
        Prefs.instance.saveShowState();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_boardFragment_to_navigation_home);
    }
}