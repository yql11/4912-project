package com.example.smart_agriculture_sys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Redirecttologin extends AppCompatActivity {
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirect_to_login);

        //Initializing button and setting it to false
        login = findViewById(R.id.login);
        login.setEnabled(false);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Redirecttologin.this, Userlogin.class);
                startActivity(intent);
            }
        });
    }

}
