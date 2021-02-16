package com.example.melochizhizni.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.melochizhizni.R;
import com.example.melochizhizni.data.models.Item;
import com.example.melochizhizni.databinding.CatItemBinding;
import com.example.melochizhizni.databinding.SelectedItemBinding;

import java.util.List;

public class CatSelectedItemAdapter extends RecyclerView.Adapter<CatSelectedItemAdapter.ViewHolder> {

        private List<Item> listItem;
        private OnItemClickListener listener;

        public CatSelectedItemAdapter(List<Item> list, OnItemClickListener listener) {
            this.listItem = list;
            this.listener = listener;
        }

        public void setList(List<Item> list) {
            listItem.clear();
            this.listItem = list;
            notifyDataSetChanged();
        }

        public void deleteByPos(int pos){
            listItem.remove(pos);
            notifyDataSetChanged();
        }

        public void deleteAll(){
            listItem.clear();
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public CatSelectedItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            SelectedItemBinding binding = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_item, parent, false));
            return new ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull CatSelectedItemAdapter.ViewHolder holder, int position) {
            holder.onBind(listItem.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(listItem.get(position), position);
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return listItem.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private SelectedItemBinding binding;

            public ViewHolder(@NonNull SelectedItemBinding binding) {
                super(binding.getRoot());
                this.binding = binding;

            }

            public void onBind(Item item) {
                binding.setModel(item);
                binding.setListener(listener);
                binding.setImgUrl(item.getPhoto());
                binding.etTrash.setOnClickListener(v -> listener.onViewClicked(item, getBindingAdapterPosition()));
            }

        }
        public void setOnItemListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        public interface OnItemClickListener {
            void onItemClicked(Item v, int position);
            void onViewClicked(Item v, int position);
        }
    }
