package com.example.melochizhizni.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.melochizhizni.data.models.CumulativePoints;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class AuthViewModel extends ViewModel {
    private MutableLiveData<Boolean> navigate = new MutableLiveData<>();

    public LiveData<Boolean> getNavState (){
        return navigate;
    }

    public void setRealTimeData(String name, String number, int points, FirebaseFirestore db){
        CumulativePoints cumulativePoints = new CumulativePoints(name, number, points);
        db
                .collection("Users")
                .document(number)
                .set(cumulativePoints).addOnSuccessListener(aVoid -> navigate.setValue(true));
    }
}
