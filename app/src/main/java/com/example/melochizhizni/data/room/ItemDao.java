package com.example.melochizhizni.data.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.melochizhizni.data.models.Item;

import java.util.List;
@Dao
public interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addListItem(List<Item> listItem);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addItem(Item item);

    @Delete
    void deleteItem(Item item);

    @Query("SELECT * FROM Item")
    List<Item> getAllItems();

    @Update
    void updateItem(Item item);

}
