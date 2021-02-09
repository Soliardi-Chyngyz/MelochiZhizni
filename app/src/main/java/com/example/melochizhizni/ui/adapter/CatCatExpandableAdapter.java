package com.example.melochizhizni.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.melochizhizni.R;
import com.example.melochizhizni.data.models.ExpandableCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.WHITE;

public class CatCatExpandableAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<ExpandableCategory> list;
    private LayoutInflater inflater;


    public CatCatExpandableAdapter(Context context, ArrayList<ExpandableCategory> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return this.list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getItems().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getItems().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ExpandableCategory listTitle = (ExpandableCategory) getGroup(groupPosition);
        String name = listTitle.getName();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.parent_expand_header, null);
        }
        TextView listTitleTextView = convertView.findViewById(R.id.expand_parent);
        ImageView imageView = convertView.findViewById(R.id.expand_logo);
        listTitleTextView.setText(name);

        if (name.equals("Кухня"))
            imageView.setImageResource(R.drawable.cooking);
        else if (name.equals("Ванная"))
            imageView.setImageResource(R.drawable.bath);
        else if (name.equals("Для детей"))
            imageView.setImageResource(R.drawable.toys);
        else if (name.equals("Бытовая техника"))
            imageView.setImageResource(R.drawable.iron);

        convertView.setBackgroundColor(WHITE);
        return convertView;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.expand_child, null);
        }
        String child = (String) getChild(groupPosition, childPosition);
        TextView name = convertView.findViewById(R.id.expand_child);
        ImageView img = convertView.findViewById(R.id.expand_img);
        name.setText(child);

        if (child.equals("Чашки"))
            img.setImageResource(R.drawable.cup);
        else if (child.equals("Посуда"))
            img.setImageResource(R.drawable.plate);
        else if (child.equals("Хлебница"))
            img.setImageResource(R.drawable.bread);
        else if (child.equals("Сушилка для посуды"))
            img.setImageResource(R.drawable.cleaner);
        else if (child.equals("Вазы"))
            img.setImageResource(R.drawable.vase);
        else if (child.equals("Столовые приборы"))
            img.setImageResource(R.drawable.cutlery);
        else if (child.equals("Кухонные аксессуары"))
            img.setImageResource(R.drawable.accessory);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
