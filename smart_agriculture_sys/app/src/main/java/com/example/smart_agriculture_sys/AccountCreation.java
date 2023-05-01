package com.example.smart_agriculture_sys;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AccountCreation extends AppCompatActivity {

    private EditText username, password, confirmPassword, email;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        email = findViewById(R.id.email);
        createAccountButton = findViewById(R.id.create_account_button);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference accountsRef = database.child("accounts");


        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get input values
                String username_input = username.getText().toString();
                String password_input = password.getText().toString();
                String confirmPassword_input = confirmPassword.getText().toString();
                String email_input = email.getText().toString();

                // Check if all fields are filled in
                if (username_input.equals("") || password_input.equals("") || confirmPassword_input.equals("") || email_input.equals("")) {
                    // Display error message if any field is empty
                    Toast.makeText(AccountCreation.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Check if password and confirm password match
                    if (password_input.equals(confirmPassword_input)) {
                        Account account = new Account(username_input, password_input, email_input);
                        accountsRef.child(username_input).setValue(account);
                        Intent intent = new Intent(AccountCreation.this, Redirecttologin.class);
                        startActivity(intent);
                    } else {
                        // Display error message if password and confirm password do not match
                        Toast.makeText(AccountCreation.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
