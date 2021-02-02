package com.example.melochizhizni.ui.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.melochizhizni.R;

public class BoardAdapter extends PagerAdapter {
    private String[] titles = new String[]{"Простота", "Удобство", "Экономия"};
    private String[] descs = new String[]{"Какой то текст", "Еще какой то текст", "Какой то там текст"};
    private int[] images = new int[]{R.drawable.aa, R.drawable.ab, R.drawable.ac};
    private OnStartClickListener onStartClickListener;



    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.page_board, container, false);
        onBindPageAdapter(view, position);
        container.addView(view);
        return view;
    }

    private void onBindPageAdapter(View view, int position) {
        Button btn = view.findViewById(R.id.btn);
        btn.setOnClickListener(v -> onStartClickListener.onCLick());

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textTitle = view.findViewById(R.id.textTitle);
        TextView textDesc = view.findViewById(R.id.textDesc);
        textTitle.setText(titles[position]);
        textDesc.setText(descs[position]);
        imageView.setImageResource(images[position]);
        if (position < 2)
            btn.setVisibility(View.GONE);
        else
            btn.setVisibility(View.VISIBLE);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void setOnStartClickListener(OnStartClickListener onStartClickListener) {
        this.onStartClickListener = onStartClickListener;
    }

    public interface OnStartClickListener {
        void onCLick();
    }
}
