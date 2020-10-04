package com.example.loulydatingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity10 extends AppCompatActivity {

    EditText txtCardNum, txtCardName, txtDate, txtCvv, txtEmail;
    Button btnSave;
    DatabaseReference dbRef;
    Payment pmt;
    long maxId = 0;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);

        txtCardNum = findViewById(R.id.etCardNumber);
        txtCardName = findViewById(R.id.etCardName);
        txtDate = findViewById(R.id.etDate);
        txtCvv = findViewById(R.id.etCvv);
        txtEmail = findViewById(R.id.etEmail);

        btnSave = findViewById(R.id.btnSaveCardDetails);


        pmt = new Payment();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Payment");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    maxId = (dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                try {
                    if (TextUtils.isEmpty(txtCardNum.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Card Number", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtCardName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtDate.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Expire Date", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtCvv.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter CVV", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtEmail.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter E-mail", Toast.LENGTH_SHORT).show();
                    else {

                        if(!txtCardNum.getText().toString().trim().matches("[0-9]{16}"))
                            txtCardNum.setError("Invalid Card Number");
                        else if (!txtCardName.getText().toString().trim().matches("[a-zA-z- ]+"))
                            txtCardName.setError("Invalid Name");
                        else if(!txtDate.getText().toString().trim().matches("[0-9]{4}"))
                            txtDate.setError("Invalid Date");
                        else if(!txtCvv.getText().toString().trim().matches("[0-9]{3}"))
                            txtCvv.setError("Invalid CVV");
                        else if (!txtEmail.getText().toString().trim().matches(emailPattern))
                            txtEmail.setError("InValid Email Address");

                        else {
                            pmt.setCardNum(Long.parseLong(txtCardNum.getText().toString().trim()));
                            pmt.setCardName(txtCardName.getText().toString().trim());
                            pmt.setDate(Integer.parseInt(txtDate.getText().toString().trim()));
                            pmt.setCvv(Integer.parseInt(txtCvv.getText().toString().trim()));
                            pmt.setEmail(txtEmail.getText().toString().trim());


                            dbRef.child(String.valueOf(maxId + 1)).setValue(pmt);
                            Toast.makeText(getApplicationContext(), "Data Inserted Successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(MainActivity10.this, MainActivity8.class);
                            startActivity(intent);

                        }


                    }

                }catch (NumberFormatException nfe){
                    Toast.makeText(getApplicationContext(), "Invalid Date", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}