package com.example.smart_agriculture_sys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Userlogin extends AppCompatActivity {
    private EditText username,emailaddress,password,lastname,firstname;
    private Button login,account_creation;

    private FirebaseDatabase mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        emailaddress = findViewById(R.id.email_address);
        firstname = findViewById(R.id.first_name);
        lastname = findViewById(R.id.last_name);
        login = findViewById(R.id.login);
        account_creation = findViewById(R.id.account_creation);
        FirebaseDatabase FirebaseAuth = null;
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getReference() != null)
            startActivity(new Intent(Userlogin.this, MainActivity.class));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(Userlogin.this, "Please fill in all required information", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(username.getText().toString(), password.getText().toString());
                }
            }
        });

        account_creation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Userlogin.this, AccountCreation.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    private void loginUser(String email, String password) {


    }
    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }


    };
}
