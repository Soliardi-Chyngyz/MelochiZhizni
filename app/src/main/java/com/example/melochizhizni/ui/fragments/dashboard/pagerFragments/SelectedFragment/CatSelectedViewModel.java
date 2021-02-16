package com.example.melochizhizni.ui.fragments.dashboard.pagerFragments.SelectedFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.melochizhizni.App;

public class CatSelectedViewModel extends ViewModel {
    private MutableLiveData <Boolean> confirmYes = new MutableLiveData<>();

    public LiveData <Boolean> getConfirmValue(){
        return confirmYes;
    }

    public void setDeleteAdapter(boolean value){
        confirmYes.setValue(value);
        App.database.itemSelectedDao().deleteAll();
    }
}
