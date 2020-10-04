package com.example.loulydatingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity9 extends AppCompatActivity {

    TextView tvName1, tvCard1, tvName2, tvCard2, tvName3, tvCard3;
    Button btnEditMethod, btnAddMethod;
    Payment pmt;
    DatabaseReference dbRef;
    String value;
    String packTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        Intent inte = getIntent();

         value = inte.getStringExtra("value");
        packTotal = inte.getStringExtra("packTotal");


        tvName1 = findViewById(R.id.tvName1);
        tvCard1 = findViewById(R.id.tvCard1);
        tvName2 = findViewById(R.id.tvName2);
        tvCard2 = findViewById(R.id.tvCard2);
        tvName3 = findViewById(R.id.tvName3);
        tvCard3 = findViewById(R.id.tvCard3);
        btnAddMethod = findViewById(R.id.btnAddMethod);
        btnEditMethod = findViewById(R.id.btnEditMethod);
        pmt = new Payment();



        dbRef = FirebaseDatabase.getInstance().getReference().child("Payment").child("1");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()){

                    tvName1.setText(dataSnapshot.child("cardName").getValue().toString());
                    tvCard1.setText(dataSnapshot.child("cardNum").getValue().toString());
                }
                else
                    Toast.makeText(getApplicationContext(), "No Data Source", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        tvName1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity9.this, MainActivity12.class);
                intent.putExtra("value",value);
                intent.putExtra("packTotal",packTotal);
                startActivity(intent);
            }
        });
        tvCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity9.this, MainActivity12.class);
                intent.putExtra("value",value);
                intent.putExtra("packTotal",packTotal);
                startActivity(intent);
            }
        });



        btnAddMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity9.this , MainActivity10.class);
                startActivity(intent);
            }
        });

        btnEditMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity9.this, MainActivity11.class);
                startActivity(intent);
            }
        });


    }
}