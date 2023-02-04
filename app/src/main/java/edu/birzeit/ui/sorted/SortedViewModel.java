package edu.birzeit.ui.sorted;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SortedViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    public SortedViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is reservations fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}