package edu.birzeit.ui.favorites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class favoritesViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public favoritesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is favourites fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
