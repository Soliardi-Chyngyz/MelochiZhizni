package com.example.melochizhizni.ui.fragments.dashboard.pagerFragments;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class CatCatViewModel extends ViewModel {
    private MutableLiveData<QuerySnapshot> snapshot = new MutableLiveData<>();

    public LiveData<QuerySnapshot> getData(){
        return snapshot;
    }
    public void setData(FirebaseFirestore fb) {
        fb
                .collection("Items")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            snapshot.setValue(task.getResult());
                        }
                    }
                });
    }
}
