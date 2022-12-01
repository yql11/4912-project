package com.example.smart_agriculture_sys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import org.w3c.dom.Text;

/*
    Class for the main welcome screen of the app
    Can choose to login or create an account
 */

public class Loginpage extends AppCompatActivity {

    //Class Variables
    private Button loginButton,createAccount;
    private EditText emailInput,passwordInput;
    private String email,password;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initializing fields and setting login button to false;
        loginButton = findViewById(R.id.login);

        createAccount = findViewById(R.id.account_creation);



        emailInput = findViewById(R.id.email_address);
        passwordInput = findViewById(R.id.password);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Loginpage.this, FunctionalApplication.class);
                startActivity(intent);
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Loginpage.this, AccountCreation.class);
                startActivity(intent);
            }
        });


    }

    //Method called when Create Account button is clicked, opens new activity


    //Textwatcher used to check EditText fields
    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            //Checks if the email and password are valid
            if(emailInput.getText().toString().length() == 0 || passwordInput.getText().toString().length() == 0){
                loginButton.setEnabled(false);
            }
            else{
                email = emailInput.getText().toString();
                password = passwordInput.getText().toString();
                loginButton.setEnabled(true);
            }
        }
    };

    // Add progress bar functionality, and copy email validation for next deliverable
    //Method to log user in
    public void userLogin(View view){

        //Checks fields are valid
        if(email.isEmpty()){
            emailInput.setError("Email is required");
            emailInput.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailInput.setError("Please enter a valid email");
            emailInput.requestFocus();
            return;
        }

        if(password.isEmpty()){
            passwordInput.setError("Password is required");
            passwordInput.requestFocus();
            return;
        }

        if(password.length()<6){
            passwordInput.setError("Minimum length of password is 6");
            passwordInput.requestFocus();
            return;
        }



        /*
            If statements to find what type of object the user is and initizalize it if it's found
            If there is a match passwords are then compared
            if passwords match the object is passed to the appropriate activity
         */

    }
}



