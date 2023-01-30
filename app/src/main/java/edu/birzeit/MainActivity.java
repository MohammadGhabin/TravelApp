package edu.birzeit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button button;
    //LinearLayout linearLayout;
    public static List<DestinationActivity> destinationActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setProgress(false);
        button = (Button) findViewById(R.id.getStartedButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionAsyncTask connectionAsyncTask = new ConnectionAsyncTask(MainActivity.this);
                connectionAsyncTask.execute("https://run.mocky.io/v3/d1a9c002-6e88-4d1e-9f39-930615876bca?fbclid=IwAR1VX0Y4Zx8HtfUSh0J9STgw57u6UVSo8yFo3q45-peksEyjBA2ubjqxf_c");
                openLoginActivity();
            }
        });
        //linearLayout = (LinearLayout) findViewById(R.id.layout);
    }
    public void openLoginActivity(){
        Intent intent = new Intent(MainActivity.this, LoginRegistrationActivity.class);
        MainActivity.this.startActivity(intent);
        finish();
    }


    public void setButtonText(String text) {
        button.setText(text);
    }

    /*
    public void fillStudents(List<Student> students) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout);
        linearLayout.removeAllViews();
        for (int i = 0; i < students.size(); i++) {
            TextView textView = new TextView(this);
            textView.setText(students.get(i).toString());
            linearLayout.addView(textView);
        }
    }
    public void setProgress (boolean progress){
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        if (progress) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }*/

}
