package com.android.penyiramotomatis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView sensor1;
    TextView sensor2;
    FirebaseDatabase database;
    DatabaseReference sensor_1;
    DatabaseReference sensor_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensor1 = findViewById(R.id.sensor1);
        sensor2 = findViewById(R.id.sensor2);
        database = FirebaseDatabase.getInstance();
        sensor_1 =database.getReference("sensor_1");
        sensor_2=database.getReference("sensor_2");
        getSensor1();
        getSensor2();
    }

    public void getSensor1(){
        sensor_1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = Integer.valueOf(String.valueOf(dataSnapshot.getValue()));
                sensor1.setText(value+"%");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    public void getSensor2(){
        sensor_2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = Integer.valueOf(String.valueOf(dataSnapshot.getValue()));
                sensor2.setText(value+"%");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void InputTanaman(View view){
        Intent intent = new Intent(MainActivity.this,Input_Tanaman.class);
        startActivity(intent);
    }
    public void ListTanaman(View view){
        Intent intent = new Intent(MainActivity.this,ListTanaman.class);
        startActivity(intent);
    }
}
