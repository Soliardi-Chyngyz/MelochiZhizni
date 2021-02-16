package com.example.melochizhizni.ui.fragments.main;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.example.melochizhizni.App;
import com.example.melochizhizni.Prefs;
import com.example.melochizhizni.R;
import com.example.melochizhizni.ui.adapter.FragmentAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.BuildConfig;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private FloatingActionButton addBtn;
    private TextView text;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);

        addBtn.setOnClickListener(v -> Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_navigation_main_to_formFragment));

    }


    private void init(View view) {
        addBtn = view.findViewById(R.id.add);
    }
}