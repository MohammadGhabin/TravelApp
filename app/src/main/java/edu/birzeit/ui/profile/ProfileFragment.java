package edu.birzeit.ui.profile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.regex.Pattern;


import edu.birzeit.DataBaseHelper;
import edu.birzeit.LoginFragment;
import edu.birzeit.LoginRegistrationActivity;

import edu.birzeit.R;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private EditText firstName,lastName,password,confirmPassword,phoneNumber;
    private Button saveChanges,viewAll,logout;
    private Spinner genderSpinner;

    String spin_val;
    String[] gender = {"Male", "Female"};

    private static final Pattern[] inputRegexes = new Pattern[4];
    static {
        inputRegexes[0] = Pattern.compile(".*[A-Z].*");
        inputRegexes[1] = Pattern.compile(".*[a-z].*");
        inputRegexes[2] = Pattern.compile(".*\\d.*");
        inputRegexes[3] = Pattern.compile(".*[`~!@#$%^&*()\\-_=+\\\\|\\[{\\]};:'\",<.>/?].*");
    }
    
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        final TextView textView = root.findViewById(R.id.text_profile);
        profileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        firstName = root.findViewById(R.id.newFirstNameText);
        lastName = root.findViewById(R.id.newLastNameText);
        password = root.findViewById(R.id.newPasswordText);
        confirmPassword = root.findViewById(R.id.newConfirmPasswordText);
        phoneNumber = root.findViewById(R.id.newPhoneText);
        genderSpinner = root.findViewById(R.id.spinnerGender);
        saveChanges = root.findViewById(R.id.saveChangesBTN);
        viewAll = root.findViewById(R.id.viewAllBTN);
        logout = root.findViewById(R.id.LogoutButton);

        final String phonePattern = "^05.*";

        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spin_val = gender[position];//saving the value selected
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        ArrayAdapter<String> spin_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, gender);
        genderSpinner.setAdapter(spin_adapter);


        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = LoginFragment.emailLogedIn;
                String newFirstName = firstName.getText().toString().trim();
                String newLastName = lastName.getText().toString().trim();
                String newPassword = password.getText().toString();
                String newConfirmPassword = confirmPassword.getText().toString();
                String newPhone = phoneNumber.getText().toString().trim();
                String newGender = genderSpinner.getSelectedItem().toString().trim();

                if (TextUtils.isEmpty(newFirstName)) {
                    firstName.setError("First Name is Required.");
                    return;
                }
                if (newFirstName.length() < 2) {
                    firstName.setError("First Name length must be at least 2 characters.");
                    return;
                }
                if (TextUtils.isEmpty(newLastName)) {
                    lastName.setError("Last Name is Required.");
                    return;
                }
                if (newLastName.length() < 2) {
                    lastName.setError("Last Name length must be at least 2 characters.");
                    return;
                }

                if (newPassword.length() < 6) {
                    password.setError("Password must be more or equal to 6 Characters.");
                    return;
                }
                if (!isMatchingRegex(newPassword)) {
                    password.setError("Password must contain at least one upper case,one lower case,one special character,one digit");
                    return;
                }
                if (!newPassword.equals(newConfirmPassword)){
                    confirmPassword.setError("password and confirm password must be matched");
                    return;
                }
                if(newPhone.length() != 10){
                    phoneNumber.setError("Invalid Phone Number, Phone Number must be 10 digits.");
                    return;
                }
                if(!newPhone.matches(phonePattern)){
                    phoneNumber.setError("Invalid Phone Number, Phone Number must start with 05...");
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(),"BZU",null,1);
                boolean isUpdate = dataBaseHelper.updateData(email,newFirstName,newLastName,newGender,newPassword,newPhone);

                if(isUpdate == true)
                    Toast.makeText(getContext(),"Data Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(),"Failed Update Data ", Toast.LENGTH_SHORT).show();
            }
        });

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(),"BZU",null,1);
                Cursor result = dataBaseHelper.getAllCustomers();
                if(result.getCount() == 0){
                    showMessage("Error","Nothing Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while(result.moveToNext()){
                    buffer.append("Email:" + result.getString(0) + "\n");
                    buffer.append("First Name:" + result.getString(1) + "\n");
                    buffer.append("Last Name:" + result.getString(2) + "\n");
                    buffer.append("Gender:" + result.getString(3) + "\n");
                    buffer.append("Password:" + result.getString(4) + "\n");
                    buffer.append("Phone:" + result.getString(5) + "\n\n");
                }
                showMessage("Data",buffer.toString());
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("checkbox", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("remember", "false");
                editor.apply();
                getActivity().finish();
            }
        });


        return root;
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

    public static boolean isMatchingRegex(String input) {
        boolean inputMatches = true;
        for (Pattern inputRegex : inputRegexes) {
            if (!inputRegex.matcher(input).matches()) {
                inputMatches = false;
            }
        }
        return inputMatches;
    }
}