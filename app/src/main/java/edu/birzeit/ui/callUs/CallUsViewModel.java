package edu.birzeit.ui.callUs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CallUsViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public CallUsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is call us fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
