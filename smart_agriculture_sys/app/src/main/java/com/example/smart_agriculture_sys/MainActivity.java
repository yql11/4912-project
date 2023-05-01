package com.example.smart_agriculture_sys;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextUtils;


public class MainActivity extends AppCompatActivity {

    private EditText username, password;
    private Button loginButton,createAccountButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login_button);
        createAccountButton = (Button) findViewById(R.id.create_account_button);



        loginButton.setOnClickListener(view -> {

            String username_input = username.getText().toString();
            String password_input = password.getText().toString();
            // Check if username and password are valid
            if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                Toast.makeText(MainActivity.this, "Please fill in all required information", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, FunctionalApplication.class);
                startActivity(intent);

            } else if (isValid(username_input, password_input)) {
                Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, FunctionalApplication.class);
                startActivity(intent);

            } else {
                // If invalid, show an error message
                Toast.makeText(getApplicationContext(), "Invalid username or password!", Toast.LENGTH_SHORT).show();
            }
        });

        createAccountButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AccountCreation.class);
            startActivity(intent);
            finish();
        });
    }

    private boolean isValid(String username, String password) {
        // Check if the username and password are valid
        if (username != null && password != null){
            return username.equals(username) && password.equals(password);
        }
        return username.equals("admin") && password.equals("password");
    }
}




