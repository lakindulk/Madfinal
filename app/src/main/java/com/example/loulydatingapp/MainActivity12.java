package com.example.loulydatingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity12 extends AppCompatActivity {

    TextView tvPakDetails,tvPakTot,txtCardNum, txtCardName, txtDate, txtCvv, txtEmail;
    Button btnBuyNow;
    DatabaseReference dbRef;
    Payment pmt;
    String value;
    String packTotal;
    String pack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);

        final Intent inte = getIntent();

        value = inte.getStringExtra("value");
        packTotal = inte.getStringExtra("packTotal");
        //pack = packTotal.toString();

        txtCardNum = findViewById(R.id.tvCardNumber);
        txtCardName = findViewById(R.id.tvCardName);
        txtDate = findViewById(R.id.tvDate);
        txtCvv = findViewById(R.id.tvCvv);
        txtEmail = findViewById(R.id.tvEmail);
        tvPakDetails = findViewById(R.id.tvPakDetails);
        tvPakDetails.setText(value);
        tvPakTot = findViewById(R.id.tvPakTot);

        btnBuyNow = findViewById(R.id.btnBuyNow);
        pmt = new Payment();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Payment").child(String.valueOf("1"));
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txtCardNum.setText(dataSnapshot.child("cardNum").getValue().toString());
                txtCardName.setText(dataSnapshot.child("cardName").getValue().toString());
                txtDate.setText(dataSnapshot.child("date").getValue().toString());
                txtCvv.setText(dataSnapshot.child("cvv").getValue().toString());
                txtEmail.setText(dataSnapshot.child("email").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        tvPakTot.setText(packTotal);

        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity12.this, MainActivity8.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Package Buy Successful", Toast.LENGTH_SHORT).show();
            }
        });

    }
}