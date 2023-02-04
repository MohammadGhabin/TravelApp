package edu.birzeit.ui.sorted;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Comparator;
import java.util.List;

import edu.birzeit.Destination;
import edu.birzeit.MainActivity;
import edu.birzeit.R;

public class SortedFragment extends Fragment {
    private SortedViewModel reservationsViewModel;
    private ListView listView;
    private ToggleButton toggleButton;
    List<Destination> destinationActivities = MainActivity.destinations;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_sorted, container, false);
        listView = root.findViewById(R.id.listview_Sort);
        listView.setScrollBarSize(destinationActivities.size());
        ImageView dollarSign = root.findViewById(R.id.dollar_sign);

        String mTitle[] = new String[100];
        String mDescription[] = new String[100];
        int i = 0;
        destinationActivities = ascSort();
        while ( destinationActivities.size()>i){
            mTitle[i] = destinationActivities.get(i).getCity();
            mDescription[i] = destinationActivities.get(i).getCountry();
            i++;
        }



        toggleButton = root.findViewById(R.id.sortButton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    destinationActivities = desSort();
                    int i = 0;
                    while ( destinationActivities.size()>i){
                        mTitle[i] = destinationActivities.get(i).getCity();
                        mDescription[i] = destinationActivities.get(i).getCountry();
                        i++;
                    }
                    SortedFragment.MyAdapter adapter = new SortedFragment.MyAdapter(getContext(), mTitle, mDescription);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        }
                    });
                    ObjectAnimator ascendingAnimation = ObjectAnimator.ofFloat(dollarSign, "translationY", 0f, 1550f);
                    ascendingAnimation.setDuration(1000);
                    ascendingAnimation.setInterpolator(new LinearInterpolator());

                    ObjectAnimator ascendingScale = ObjectAnimator.ofFloat(dollarSign, "scaleY", 1f, 4f);
                    ascendingScale.setDuration(1000);
                    ascendingScale.setInterpolator(new LinearInterpolator());

                    AnimatorSet ascendingSet = new AnimatorSet();
                    ascendingSet.playTogether(ascendingAnimation, ascendingScale);
                    ascendingSet.start();
                } else {
                    destinationActivities = ascSort();
                    int i = 0;
                    while ( destinationActivities.size()>i){
                        mTitle[i] = destinationActivities.get(i).getCity();
                        mDescription[i] = destinationActivities.get(i).getCountry();
                        i++;
                    }
                    SortedFragment.MyAdapter adapter = new SortedFragment.MyAdapter(getContext(), mTitle, mDescription);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        }
                    });
                    ObjectAnimator descendingAnimation = ObjectAnimator.ofFloat(dollarSign, "translationY", 1550f, 0f);
                    descendingAnimation.setDuration(1000);
                    descendingAnimation.setInterpolator(new LinearInterpolator());

                    ObjectAnimator descendingScale = ObjectAnimator.ofFloat(dollarSign, "scaleY", 4f, 1f);
                    descendingScale.setDuration(1000);
                    descendingScale.setInterpolator(new LinearInterpolator());

                    AnimatorSet descendingSet = new AnimatorSet();
                    descendingSet.playTogether(descendingAnimation, descendingScale);
                    descendingSet.start();
                }
            }
        });


        SortedFragment.MyAdapter adapter = new SortedFragment.MyAdapter(getContext(), mTitle, mDescription);
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
    private List<Destination> ascSort() {
         destinationActivities.sort(new Comparator<Destination>() {
           @Override
           public int compare(Destination destinationActivity, Destination t1) {
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

    private  List<Destination>  desSort() {
        destinationActivities.sort(new Comparator<Destination>() {
            @Override
            public int compare(Destination destinationActivity, Destination t1) {
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
