package com.example.melochizhizni.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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

public class CatCatExpandableAdapter extends BaseExpandableListAdapter {
    private final Context context;
    private ArrayList<ExpandableCategory> list;


    public CatCatExpandableAdapter(Context context, ArrayList<ExpandableCategory> list) {
        this.context = context;
        this.list = list;
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
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.parent_expand_header, null);
        }
        TextView listTitleTextView = convertView.findViewById(R.id.expand_parent);
        listTitleTextView.setText(name);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String child = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.expand_child, null);
        }
        TextView name = convertView.findViewById(R.id.expand_child);
        ImageView img = convertView.findViewById(R.id.expand_img);
        name.setText(child);

        String titleName = getGroup(groupPosition).toString();
        if(titleName.equals("Кухня")){
            if(child.equals("Чашки"))
                img.setImageResource(R.drawable.ic_image);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
