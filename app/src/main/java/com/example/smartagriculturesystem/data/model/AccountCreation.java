package com.example.smartagriculturesystem.data.model;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PathEffect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.text.TextWatcher;
import android.widget.Spinner;
import android.widget.Toast;
import android.text.TextUtils;

import com.example.smartagriculturesystem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.*;


public class AccountCreation extends AppCompatActivity{

    //Declaring buttons and EdiTexts
    private Button createAccount;
    private EditText firstName,lastName,emailAddress,passwordCreate,confirmPassword,userName;
    private int spinnerPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);

        //Initializing button and setting it to false
        createAccount = findViewById(R.id.CreateAccount);
        createAccount.setEnabled(true);


        //Initializing Edittext fields
        firstName = findViewById(R.id.FirstName);
        lastName = findViewById(R.id.LastName);
        emailAddress = findViewById(R.id.Email);
        passwordCreate = findViewById(R.id.Password);
        confirmPassword = findViewById(R.id.ConfirmPassword);
        userName = findViewById(R.id.UserName);

        //Adding textwatcher to all the editTexts
        firstName.addTextChangedListener(watcher);
        lastName.addTextChangedListener(watcher);
        emailAddress.addTextChangedListener(watcher);
        passwordCreate.addTextChangedListener(watcher);
        confirmPassword.addTextChangedListener(watcher);
        userName.addTextChangedListener(watcher);




    }

    //TextWatcher used to check if any EditText fields have changed
    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        //Checking if all fields are filled and valid and then enabling button
        public void afterTextChanged(Editable s) {
            if(!isEmpty(firstName) && !isEmpty(lastName) && !isEmpty(emailAddress) && !isEmpty(passwordCreate) && !isEmpty(confirmPassword) && !isEmpty(userName)){
                if(validEmail(emailAddress)){
                    if(validUsername(userName)){
                        if(uniqueUsername(userName,spinnerPos)){
                            if(isMatch(passwordCreate,confirmPassword)){
                                createAccount.setEnabled(true);
                            }
                        }
                    }
                }
            }
        }
    };


    //Checks if the passed EditText is empty
    public boolean isEmpty(EditText editText){
        return (editText.getText().toString().length() == 0) ? true : false;
    }

    //Checks if the email filled in is valid
    @Override
    public boolean validEmail(EditText editText) {
        String email = editText.getText().toString().trim();
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return true;
        }
        else{
            return false;
        }
    }

    //Method to check that the username passes rudementary checks to make a useful string
    public boolean validUsername(EditText editText){
        String username = editText.getText().toString().trim();

        if(username.length() < 5){
            return false;
        }
        else if(username.equals(username.toLowerCase())){
            return false;
        }
        return true;
    }


    public boolean uniqueUsername(EditText editText,int search){

        String username = editText.getText().toString().trim();

        }
    }


    //Checks if the two EditText fields passed are the same
    public boolean isMatch(EditText editTextOne, EditText editTextTwo){
        String wordOne = editTextOne.getText().toString();
        String wordTwo = editTextTwo.getText().toString();

        return (wordOne.equals(wordTwo)) ? true:false;
    }

    //Method called when registering a user
    public void registerUser(View view){

        String email = emailAddress.getText().toString().trim();
        String password = passwordCreate.getText().toString().trim();

        //Various checks to make sure all fields are valid
        if(!email.isEmpty()){

            if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){

                if(!password.isEmpty()){

                    if(password.length()>6){
                        //Checks that the email has not already been used

                        }
                        else{
                            emailAddress.setError("Email is already in use, please enter a unique address");
                            emailAddress.requestFocus();
                        }
                    }
                    else{
                        passwordCreate.setError("Minimum length of password is 6");
                        passwordCreate.requestFocus();
                    }
                }
                else{
                    passwordCreate.setError("Password is required");
                    passwordCreate.requestFocus();
                }
            }
            else{
                emailAddress.setError("Please enter a valid email");
                emailAddress.requestFocus();
            }
        }
        else{
            emailAddress.setError("Email is required");
            emailAddress.requestFocus();
        }

    }