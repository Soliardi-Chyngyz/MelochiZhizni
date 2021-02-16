package com.example.melochizhizni.ui.fragments.dashboard.pagerFragments.CatCatRecycler;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.melochizhizni.data.models.Item;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CatCatRecyclerViewModel extends ViewModel {
    private MutableLiveData<List<Item>> snapshot = new MutableLiveData<>();

    public LiveData<List<Item>> getData() {
        return snapshot;
    }

    public void setData(FirebaseFirestore fb, String parentName) {
        fb
                .collection("Items")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            sortData(parentName, task);
                        }
                    }
                });
    }

    private void sortData(String parentName, Task<QuerySnapshot> task) {
        List<Item> itemList = new ArrayList<>();
        for (QueryDocumentSnapshot doc : task.getResult()) {
            if (parentName.equals(doc.getString("category"))) {
                Item item = new Item(
                        doc.getString("title"),
                        doc.getString("article"),
                        doc.getString("photo"),
                        doc.getString("features"),
                        doc.getString("size"),
                        doc.getString("descriptions"),
                        doc.getString("sizeInBox"),
                        doc.getString("weight"),
                        doc.getString("price"),
                        doc.getString("category")
                );
                itemList.add(item);
            }
        }
        snapshot.setValue(itemList);
    }
}
