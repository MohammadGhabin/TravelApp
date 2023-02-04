package edu.birzeit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class DestinationActivity extends AppCompatActivity {
    private Destination destination;
    private DescriptionFragment descriptionFragment;
    private ImageFragment imageFragment;
    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        Intent intent = getIntent();
        FragmentManager mFragmentManager = getSupportFragmentManager();
        destination = (Destination) intent.getSerializableExtra("destination");

        // Set the name of the destination
        TextView destinationName = findViewById(R.id.destination_name);
        destinationName.setText(destination.getCity());

        // Find the buttons
        Button descriptionButton = findViewById(R.id.description_button);
        Button imageButton = findViewById(R.id.image_button);
        Button mapButton = findViewById(R.id.map_button);

        // Initialize the fragments
        descriptionFragment = new DescriptionFragment();
        imageFragment = new ImageFragment();
        mapFragment = new MapFragment();

        descriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                if(imageFragment.isAdded()){
                    transaction.remove(imageFragment);
                    imageButton.setText("Show Image");
                }
                if(mapFragment.isAdded()){
                    transaction.remove(mapFragment);
                    mapButton.setText("Show Map");
                }
                if (descriptionFragment.isAdded()) {
                    transaction.remove(descriptionFragment);
                    descriptionButton.setText("Show Description");
                } else {
                    transaction.add(R.id.fragment_container, descriptionFragment, destination.getDescription());
                    descriptionButton.setText("Hide Description");
                }
                transaction.commit();
            }
        });

        // Show/hide the image fragment when the second button is clicked
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                if (descriptionFragment.isAdded()) {
                    transaction.remove(descriptionFragment);
                    descriptionButton.setText("Show Description");
                }
                if(mapFragment.isAdded()){
                    transaction.remove(mapFragment);
                    mapButton.setText("Show Map");
                }
                if (imageFragment.isAdded()) {
                    transaction.remove(imageFragment);
                    imageButton.setText("Show Image");
                } else {
                    transaction.add(R.id.fragment_container, imageFragment, destination.getImg());
                    imageButton.setText("Hide Image");
                }
                transaction.commit();
            }
        });

        // Show the map fragment when the third button is clicked
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                if (descriptionFragment.isAdded()) {
                    transaction.remove(descriptionFragment);
                    descriptionButton.setText("Show Description");
                }
                if (imageFragment.isAdded()) {
                    transaction.remove(imageFragment);
                    imageButton.setText("Show Image");
                }
                if (mapFragment.isAdded()) {
                    transaction.remove(mapFragment);
                    mapButton.setText("Show Map");
                } else {
                    transaction.add(R.id.fragment_container, mapFragment);
                    mapButton.setText("Hide Map");
                }
                transaction.commit();
            }
        });
    }

    public void onBackButtonClicked(View view) {
        finish();
    }
}