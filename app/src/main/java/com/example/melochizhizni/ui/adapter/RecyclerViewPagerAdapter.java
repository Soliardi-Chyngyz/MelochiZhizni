package com.example.melochizhizni.ui.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewPagerAdapter extends RecyclerView.Adapter<RecyclerViewPagerAdapter.ViewPagerHolder> {
    private List<String> list;

    public RecyclerViewPagerAdapter (List<String> list){
        this.list = list;
    }
    @NonNull
    @Override
    public ViewPagerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewPagerHolder extends RecyclerView.ViewHolder {
        public ViewPagerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
