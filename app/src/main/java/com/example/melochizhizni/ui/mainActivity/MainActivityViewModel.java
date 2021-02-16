package com.example.melochizhizni.ui.mainActivity;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.melochizhizni.data.models.CumulativePoints;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<CumulativePoints> snapshot = new MutableLiveData<>();

    public LiveData<CumulativePoints> getData() {
        return snapshot;
    }

    public void setData(FirebaseFirestore fb, String phoneNumber) {
        fb
                .collection("Users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            filter(task, phoneNumber);
                        }
                    }
                });
    }

    private void filter(Task<QuerySnapshot> task, String phoneNumber) {
        for (QueryDocumentSnapshot doc : Objects.requireNonNull(task.getResult())) {
            if (phoneNumber.equals(doc.getString("phoneNumber"))) {
                CumulativePoints cumulativePoints = new CumulativePoints(
                        doc.getString("nameUser"),
                        doc.getString("phoneNumber"),
                        Objects.requireNonNull(doc.getLong("points")).intValue()
                );
                snapshot.setValue(cumulativePoints);
            }
        }
    }
}
