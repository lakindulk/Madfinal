package com.example.loulydatingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.RestrictionEntry;
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

public class MainActivity11 extends AppCompatActivity {

    EditText txtCardNum, txtCardName, txtDate, txtCvv, txtEmail;
    Button btnSaveCardDetails,btnDeleteCardDetails;
    DatabaseReference dbRef;
    Payment pmt;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);

        txtCardNum = findViewById(R.id.etCardNumber);
        txtCardName = findViewById(R.id.etCardName);
        txtDate = findViewById(R.id.etDate);
        txtCvv = findViewById(R.id.etCvv);
        txtEmail = findViewById(R.id.etEmail);
        btnSaveCardDetails = findViewById(R.id.btnSaveCardDetails);
        btnDeleteCardDetails = findViewById(R.id.btnDeleteCardDetails);
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

              btnSaveCardDetails.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      dbRef = FirebaseDatabase.getInstance().getReference().child("Payment");
                      dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                          @Override
                          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                              if (dataSnapshot.hasChild("1")){

                                  try {

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

                                          dbRef = FirebaseDatabase.getInstance().getReference().child("Payment").child("1");
                                          dbRef.setValue(pmt);

                                          Toast.makeText(getApplicationContext(), "Card Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                      }

                                  }catch (NumberFormatException nfe){
                                      Toast.makeText(getApplicationContext(), "Invalid Date", Toast.LENGTH_SHORT).show();
                                  }
                              }
                              else
                                  Toast.makeText(getApplicationContext(), "No Source To Update", Toast.LENGTH_SHORT).show();

                          }

                          @Override
                          public void onCancelled(@NonNull DatabaseError databaseError) {

                          }
                      });

                      Intent intent = new Intent(MainActivity11.this, MainActivity9.class);
                      startActivity(intent);

                  }
              });


        btnDeleteCardDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Payment");
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("1")){
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Payment").child(String.valueOf("1"));
                            dbRef.removeValue();
                            Toast.makeText(getApplicationContext(), "Card Details Deleted Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(), "No Source to Delete", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent intent = new Intent(MainActivity11.this , MainActivity9.class);
                startActivity(intent);
            }
        });
    }
}