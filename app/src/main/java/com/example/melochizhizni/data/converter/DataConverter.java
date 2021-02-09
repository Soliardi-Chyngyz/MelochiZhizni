package com.example.melochizhizni.data.converter;

import androidx.room.TypeConverter;

import com.example.melochizhizni.data.models.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DataConverter {
    @TypeConverter
    public String fromItemList(List<Item> itemList){
        if(itemList == null){
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Item>>() {}.getType();
        String json = gson.toJson(itemList, type);
        return json;
    }
    @TypeConverter
    public List<Item> toItemList(String itemString) {
        if (itemString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Item>>() {}.getType();
        List<Item> itemList = gson.fromJson(itemString, type);
        return itemList;
    }
}
