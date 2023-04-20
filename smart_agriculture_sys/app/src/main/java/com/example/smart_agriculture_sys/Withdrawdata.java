package com.example.smart_agriculture_sys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import java.io.File;
import java.io.IOException;

public class Withdrawdata extends AppCompatActivity {
    private TextView gps, temperature_and_humidity;
    private Button refresh,back;
    private ImageView photo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refresh = findViewById(R.id.refresh);
        back = findViewById(R.id.back);
        //photo = findViewById(R.id.photo);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Withdrawdata.this, Withdrawdata.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Withdrawdata.this, FunctionalApplication.class);
                startActivity(intent);
            }
        });

        gps = findViewById(R.id.gps);
        temperature_and_humidity = findViewById(R.id.temperature_and_humidity);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();


        //StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("currentImage");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String gps_1 = dataSnapshot.child("GPS").child("currentGPS").getValue().toString();
                String temp_and_hum = dataSnapshot.child("Temperture&Humidity").child ( "currentT&H" ).getValue().toString();
                gps.setText(gps_1);
                temperature_and_humidity.setText(temp_and_hum);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }

        });

        /*try{
            final File localFile =File.createTempFile("currentImage","jpeg");
            storageRef.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(Withdrawdata.this,"Picture Retrieved",Toast.LENGTH_SHORT).show();
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            photo.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Withdrawdata.this,"Error Occurred",Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (IOException e){
            e.printStackTrace();
        }*/

    }
}
