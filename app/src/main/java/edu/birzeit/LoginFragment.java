package edu.birzeit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginFragment extends Fragment {
    private EditText emailLogin,passwordLogin;
    private CheckBox rememberMe;
    private Button login;
    private TextView goToRegisterPage;
    public static String emailLogedIn;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        emailLogin = view.findViewById(R.id.emailLoginEditText);
        passwordLogin = view.findViewById(R.id.passwordLoginEditText);
        rememberMe = view.findViewById(R.id.rememberMeCheckBox);
        login = view.findViewById(R.id.loginButton);
        goToRegisterPage = view.findViewById(R.id.goToRegisterPageTextView);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailLogin.getText().toString().trim();
                String password = passwordLogin.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    emailLogin.setError("Email Address is Required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    passwordLogin.setError("Password is Required.");
                    return;
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(),"1183377a",null,1);
                Cursor customerdata = dataBaseHelper.getDataLoginFromDB(email,password);

                if(customerdata.getCount() == 0){
                    Toast toast =Toast.makeText(getContext(), "Error in Email or in Password, No data found",Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                customerdata.moveToFirst();
                String emailFromDB = customerdata.getString(0);
                String passwordFromDB = customerdata.getString(4);

                if(emailFromDB.equals(email)){
                    if(passwordFromDB.equals(password)){
                        Toast toast =Toast.makeText(getContext(), "Successfully Logged in",Toast.LENGTH_SHORT);
                        toast.show();
                        emailLogin.setText("");
                        passwordLogin.setText("");
                        rememberMe.setChecked(false);
                        Intent intent = new Intent(getContext(), NavigationDrawerActivity.class);
                        getContext().startActivity(intent);
                        //LoginRegistrationActivity.this.startActivity(intent);
                    }else {
                        Toast toast =Toast.makeText(getContext(), "password false",Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }else{
                    Toast toast =Toast.makeText(getContext(), "email false",Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

        goToRegisterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainer, new RegisterFragment());
                ft.commit();
            }
        });
        return view;
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}