package com.example.melochizhizni.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.melochizhizni.R;
import com.example.melochizhizni.data.models.Item;
import com.example.melochizhizni.databinding.CatItemBinding;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class CatCatItemAdapter extends RecyclerView.Adapter<CatCatItemAdapter.ViewHolder> {

    private List<Item> list;
    private OnItemClickListener listener;

    public CatCatItemAdapter(List<Item> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    public void setList(List<Item> list) {
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CatCatItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CatItemBinding binding = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_item, parent, false));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CatCatItemAdapter.ViewHolder holder, int position) {
        holder.onBind(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(list.get(position), position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CatItemBinding binding;

        public ViewHolder(@NonNull CatItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Item item) {
            binding.setModel(item);
            binding.setListener(listener);
            binding.setImgUrl(item.getPhoto());
        }

    }

    public void setOnItemListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClicked(Item v, int position);
    }
}
