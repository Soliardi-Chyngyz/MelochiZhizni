package com.example.melochizhizni.data.room;

import androidx.room.RoomDatabase;

import com.example.melochizhizni.data.models.Item;

@androidx.room.Database(entities = {Item.class}, version = 4)
public abstract class Database extends RoomDatabase {

    public abstract ItemBasketDao itemBasketDao();

    public abstract ItemSelectedDao itemSelectedDao();
}
