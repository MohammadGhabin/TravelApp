package edu.birzeit.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.birzeit.Destination;
import edu.birzeit.MainActivity;
import edu.birzeit.R;

public class HomeFragment extends Fragment {
    List<Destination> destinationActivities = MainActivity.destinations;
    int randomDestinationIndex = getRandomInteger(0,destinationActivities.size()-1);
    Destination destinationActivity = destinationActivities.get(randomDestinationIndex);
    private HomeViewModel homeViewModel;
    private TextView textViewCity, textViewCountry, textViewContinent, textViewLongitude, textViewLatitude, textViewCost, textViewDescription;
    private ImageView imageView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        textViewCity = root.findViewById(R.id.textViewCity);
        textViewCountry = root.findViewById(R.id.textViewCountry);
        textViewContinent = root.findViewById(R.id.textViewContinent);
        textViewLongitude = root.findViewById(R.id.textViewLongitude);
        textViewLatitude = root.findViewById(R.id.textViewLatitude);
        textViewCost = root.findViewById(R.id.textViewCost);
        textViewDescription = root.findViewById(R.id.textViewDescription);
        imageView = root.findViewById(R.id.imageView);

        textViewCity.setText(destinationActivity.getCity());
        textViewCountry.setText(destinationActivity.getCountry());
        textViewContinent.setText(destinationActivity.getContinent());
        textViewLongitude.setText(destinationActivity.getLongitude() + "");
        textViewLatitude.setText(destinationActivity.getLatitude() + "");
        textViewCost.setText(destinationActivity.getCost() + "");
        textViewDescription.setText(destinationActivity.getDescription());
        Picasso.get().load(destinationActivity.getImg()).into(imageView);

        return root;
    }

    private int getRandomInteger(int min, int max){
        return (int)Math.floor(Math.random() * (max - min + 1) + min);
    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String rTitle[];
        String rDescription[];
        MyAdapter (Context c, String title[], String description[]) {
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);
            return row;
        }
    }
}