package com.example.smart_agriculture_sys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FunctionalApplication extends AppCompatActivity {
    private Button withdraw_button, compare_button, delete_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functional_page);

        //Initializing button and setting it to false
        withdraw_button = findViewById(R.id.withdraw_button);
        compare_button = findViewById(R.id.compare_button);
        delete_button = findViewById(R.id.delete_button);
        withdraw_button.setEnabled(false);

        withdraw_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FunctionalApplication.this, Withdrawdata.class);
                startActivity(intent);
            }
        });
    }
}
