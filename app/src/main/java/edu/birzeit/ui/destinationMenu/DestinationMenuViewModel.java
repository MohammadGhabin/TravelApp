package edu.birzeit.ui.destinationMenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.birzeit.R;

public class DestinationMenuViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DestinationMenuViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Travel Guide App");
    }

    public LiveData<String> getText() {
        return mText;
    }

}

