package com.example.melochizhizni.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.adapters.AdapterViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.melochizhizni.R;
import com.example.melochizhizni.data.models.Item;
import com.example.melochizhizni.databinding.CatItemBinding;
import com.example.melochizhizni.databinding.ItemViewBinding;

import java.util.ArrayList;
import java.util.List;

public class CatCatItemFullAdapter extends RecyclerView.Adapter<CatCatItemFullAdapter.ViewHolder> {
    private List<Item> list;

    public CatCatItemFullAdapter(List<Item> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CatCatItemFullAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         ItemViewBinding binding = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false));
         return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull CatCatItemFullAdapter.ViewHolder holder, int position) {
        holder.onBind(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemViewBinding binding;
        public ViewHolder(@NonNull ItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Item item, int pos){
            binding.setModel(item);
        }
    }
}
