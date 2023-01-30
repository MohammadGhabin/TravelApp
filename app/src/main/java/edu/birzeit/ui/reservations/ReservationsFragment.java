package edu.birzeit.ui.reservations;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Comparator;
import java.util.List;

import edu.birzeit.DestinationActivity;
import edu.birzeit.MainActivity;
import edu.birzeit.R;

public class ReservationsFragment extends Fragment {
    private ReservationsViewModel reservationsViewModel;
    private ListView listView;
    List<DestinationActivity> destinationActivities = MainActivity.destinationActivities;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_reservations, container, false);

        String mTitle[] = new String[100];
        String mDescription[] = new String[100];
        int i = 0;

        List<DestinationActivity> destinationActivitiesSorted = ascSort();
        while ( destinationActivitiesSorted.size()>i){
            mTitle[i] = destinationActivitiesSorted.get(i).getCity();
            mDescription[i] = destinationActivitiesSorted.get(i).getCountry() + "" + destinationActivitiesSorted.get(i).getCost();
            i++;
        }
        listView = root.findViewById(R.id.listview_Sort);
        ReservationsFragment.MyAdapter adapter = new ReservationsFragment.MyAdapter(getContext(), mTitle, mDescription);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
            // ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);
            return row;
        }
    }
    private  List<DestinationActivity>  ascSort() {
         destinationActivities.sort(new Comparator<DestinationActivity>() {
           @Override
           public int compare(DestinationActivity destinationActivity, DestinationActivity t1) {
               if( destinationActivity.getCost()>t1.getCost())
                   return  1;
               else if ( destinationActivity.getCost()<t1.getCost())
                   return -1;
               else
                   return 0;
           }
       });
         return destinationActivities;
    }

    private  List<DestinationActivity>  desSort() {
        destinationActivities.sort(new Comparator<DestinationActivity>() {
            @Override
            public int compare(DestinationActivity destinationActivity, DestinationActivity t1) {
                if( destinationActivity.getCost()>t1.getCost())
                    return  -1;
                else if ( destinationActivity.getCost()<t1.getCost())
                    return 1;
                else
                    return 0;
            }
        });
        return destinationActivities;
    }
}
