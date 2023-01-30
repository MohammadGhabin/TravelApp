package edu.birzeit.ui.callUs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import edu.birzeit.R;

public class CallUsFragment extends Fragment {

    private CallUsViewModel callUsViewModel;
    private Button callUs,findUs,emailUs;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        callUsViewModel =
                ViewModelProviders.of(this).get(CallUsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_call_us, container, false);
        final TextView textView = root.findViewById(R.id.text_callUs);
        callUsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        callUs = root.findViewById(R.id.callUsBTN);
        findUs = root.findViewById(R.id.findUsBTN);
        emailUs = root.findViewById(R.id.emailUsBTN);

        callUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent();
                dialIntent.setAction(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:+970599000000"));
                startActivity(dialIntent);
            }
        });

        findUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapsIntent = new Intent();
                mapsIntent.setAction(Intent.ACTION_VIEW);
                mapsIntent.setData(Uri.parse("geo:31.903617, 35.203405"));
                startActivity(mapsIntent);
            }
        });

        emailUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gmailIntent = new Intent();
                gmailIntent.setAction(Intent.ACTION_SENDTO);
                gmailIntent.setType("message/rfc822");
                gmailIntent.setData(Uri.parse("mailto:"));
                gmailIntent.putExtra(Intent.EXTRA_EMAIL,"TravelGuide@Travel.com");
                gmailIntent.putExtra(Intent.EXTRA_SUBJECT,"My Subject");
                gmailIntent.putExtra(Intent.EXTRA_TEXT,"Content of the message");
                startActivity(gmailIntent);
            }
        });


        return root;
    }
}