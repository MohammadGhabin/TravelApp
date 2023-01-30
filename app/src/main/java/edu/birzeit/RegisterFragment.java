package edu.birzeit;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.io.Console;
import java.sql.SQLDataException;
import java.util.regex.Pattern;


public class RegisterFragment extends Fragment {
    private EditText mfirstName, mlastName, memailAddress, mpassword, mconfirmPassword, mphone;
    private Button register;
    private Spinner genderSpinner;
    private TextView goToLoginPage;
    private static final Pattern[] inputRegexes = new Pattern[4];

    static {
        inputRegexes[0] = Pattern.compile(".*[A-Z].*");
        inputRegexes[1] = Pattern.compile(".*[a-z].*");
        inputRegexes[2] = Pattern.compile(".*\\d.*");
        inputRegexes[3] = Pattern.compile(".*[`~!@#$%^&*()\\-_=+\\\\|\\[{\\]};:'\",<.>/?].*");
    }

    String spin_val;
    String[] gender = {"Male", "Female"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        mfirstName = view.findViewById(R.id.firstNameEditText);
        mlastName = view.findViewById(R.id.lastNameEditText);
        memailAddress = view.findViewById(R.id.emailRegisterEditText);
        mpassword = view.findViewById(R.id.passwordRegisterEditText);
        mconfirmPassword = view.findViewById(R.id.confirmPasswordRegisterEditText);
        mphone = view.findViewById(R.id.phoneEditText);
        register = view.findViewById(R.id.registerButton);
        genderSpinner = view.findViewById(R.id.spinnerGender);
        goToLoginPage = view.findViewById(R.id.goToLoginPageTextView);

        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        final String phonePattern = "^05.*";

        genderSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
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


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = mfirstName.getText().toString().trim();
                String lastName = mlastName.getText().toString().trim();
                String email = memailAddress.getText().toString().trim();
                String password = mpassword.getText().toString();
                String confirmPassword = mconfirmPassword.getText().toString();
                String phone = mphone.getText().toString().trim();
                String gender = genderSpinner.getSelectedItem().toString().trim();

                if (TextUtils.isEmpty(firstName)) {
                    mfirstName.setError("First Name is Required.");
                    return;
                }
                if (firstName.length() < 2) {
                    mfirstName.setError("First Name length must be at least 2 characters.");
                    return;
                }
                if (TextUtils.isEmpty(lastName)) {
                    mlastName.setError("Last Name is Required.");
                    return;
                }
                if (lastName.length() < 2) {
                    mlastName.setError("Last Name length must be at least 2 characters.");
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    memailAddress.setError("Email is Required.");
                    return;
                }
                if (!email.matches(emailPattern)) {
                    memailAddress.setError("Invalid Email Address, Please Enter a Valid Email Address.");
                }

                if (password.length() < 6) {
                    mpassword.setError("Password must be more or equal to 6 Characters.");
                    return;
                }
                if (!isMatchingRegex(password)) {
                    mpassword.setError("Password must contain at least one upper case,one lower case,one special character,one digit");
                    return;
                }
                if (!password.equals(confirmPassword)){
                    mconfirmPassword.setError("password and confirm password must be matched");
                    return;
                }
                if(phone.length() != 10){
                    mphone.setError("Invalid Phone Number, Phone Number must be 10 digits.");
                    return;
                }
                if(!phone.matches(phonePattern)){
                    mphone.setError("Invalid Phone Number, Phone Number must start with 05...");
                }

                Customer customer = new Customer();
                customer.setEmail(email);
                customer.setFirstName(firstName);
                customer.setLastName(lastName);
                customer.setGender(gender);
                customer.setPassword(password);
                customer.setPhone(phone);

                DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(),"1183377a",null,1);
                Cursor customerdata = dataBaseHelper.getEmailFromDB(email);
                System.out.println(customerdata);
                if(customerdata.getCount() != 0){
                    Toast toast =Toast.makeText(getContext(), "This Email is already Registered",Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }else {
                    if(dataBaseHelper.insertCustomer(customer)){
                        //if created successfully then do the following
                        Toast toast =Toast.makeText(getContext(), "Successfully Registered",Toast.LENGTH_SHORT);
                        toast.show();
                        mfirstName.setText("");
                        mlastName.setText("");
                        memailAddress.setText("");
                        mpassword.setText("");
                        mconfirmPassword.setText("");
                        mphone.setText("");
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.fragmentContainer, new LoginFragment());
                        ft.commit();
                    }else{
                        Toast toast =Toast.makeText(getContext(), "Error in Registration in DataBase",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

            }
        });

        goToLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainer, new LoginFragment());
                ft.commit();
            }
        });
        return view;
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