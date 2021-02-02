package com.example.melochizhizni.data.models;

import java.util.ArrayList;

public class ExpandableCategory {
    private String name;
    private String image;
    private ArrayList<String> items = new ArrayList<>();

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public ExpandableCategory(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ExpandableCategory{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", items=" + items +
                '}';
    }
}
