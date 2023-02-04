package edu.birzeit.ui.all;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.Console;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import edu.birzeit.Destination;
import edu.birzeit.DestinationActivity;
import edu.birzeit.MainActivity;
import edu.birzeit.NavigationDrawerActivity;
import edu.birzeit.R;

public class AllFragment extends Fragment {

    private ListView listView;
    List<Destination> destinationActivities = MainActivity.destinations;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_all, container, false);

        Collections.sort(destinationActivities);
        String mTitle[] = new String[100];
        String mDescription[] = new String[100];
        for(int i = 0 ; i < destinationActivities.size() ; i++){
            mTitle[i] = destinationActivities.get(i).getCity();
            mDescription[i] = destinationActivities.get(i).getCountry();
        }

        listView = root.findViewById(R.id.listview);
        MyAdapter adapter = new MyAdapter(getContext(), mTitle, mDescription);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Destination destination = destinationActivities.get(position);
                Intent intent = new Intent(getContext(), DestinationActivity.class);
                intent.putExtra("destination", (Serializable) destination);

//                intent.putExtra("city", destination.getCity());
//                intent.putExtra("description", destination.getDescription());
//                intent.putExtra("image", destination.getImg());
//                intent.putExtra("longitude", destination.getLongitude());
//                intent.putExtra("latitude", destination.getLatitude());
                getContext().startActivity(intent);
            }
        });
        return root;
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