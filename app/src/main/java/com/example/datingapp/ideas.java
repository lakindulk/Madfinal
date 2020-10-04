package com.example.datingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.datingapp.MemberDetails.ContactDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.PublicKey;


public class ideas extends AppCompatActivity {

    EditText id,contact;
    Button b1,b2,b4,b5;
    DatabaseReference dbRef;
    iideas ii;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideas1);

        id=(EditText) findViewById(R.id.idea);
        contact=(EditText) findViewById(R.id.contact);
        b1=(Button) findViewById(R.id.sssave);
        b2=(Button) findViewById(R.id.vview);
        b4=(Button) findViewById(R.id.ddelete);
        b5=(Button) findViewById(R.id.uupdate);

        ii=new iideas();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("iideas");
                try {
                    if(TextUtils.isEmpty(id.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Please Enter an idea", Toast.LENGTH_SHORT).show();

                    } else {
                        //take inputs and assign them
                        ii.setContac(contact.getText().toString().trim());

                        ii.setIdea(id.getText().toString().trim());

                        //Inserting data into the database
                        //dbRef.push().setValue(std);
                        dbRef.child("ii").setValue(ii);
                        //Feedback through the Toast message.
                        Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                } catch (java.lang.NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               DatabaseReference readRef=FirebaseDatabase.getInstance().getReference().child("iideas").child("ii");
               readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                   @java.lang.Override
                   public void onDataChange(DataSnapshot snapshot) {
                       if (snapshot.hasChildren()){
                           id.setText(snapshot.child("idea").getValue().toString());
                           contact.setText(snapshot.child("contac").getValue().toString());

                       }
                       else
                           Toast.makeText(getApplicationContext(), "No source", Toast.LENGTH_SHORT).show();
                   }

                   @java.lang.Override
                   public void onCancelled(DatabaseError error) {

                   }
               });
            }
        });


        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef=FirebaseDatabase.getInstance().getReference().child("iideas");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @java.lang.Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.hasChild("ii")){
                            dbRef=FirebaseDatabase.getInstance().getReference().child("iideas").child("ii");
                            dbRef.removeValue();
                            clearControls();
                            Toast.makeText(getApplicationContext(), "Value removed", Toast.LENGTH_SHORT).show();

                        }
                        else
                            Toast.makeText(getApplicationContext(), "No source", Toast.LENGTH_SHORT).show();
                    }

                    @java.lang.Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updRef=FirebaseDatabase.getInstance().getReference().child("iideas");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @java.lang.Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if(snapshot.hasChild("ii")){
                            try{
                                ii.setContac(contact.getText().toString().trim());

                                ii.setIdea(id.getText().toString().trim());

                                dbRef=FirebaseDatabase.getInstance().getReference().child("iideas").child("ii");
                                dbRef.setValue(ii);
                                clearControls();
                                Toast.makeText(getApplicationContext(), "Value updated", Toast.LENGTH_SHORT).show();

                            }catch (java.lang.NumberFormatException e){
                                Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                            Toast.makeText(getApplicationContext(), "no source", Toast.LENGTH_SHORT).show();

                    }

                    @java.lang.Override
                    public void onCancelled(DatabaseError error) {

                    }
                });

            }
        });



    }

    public void clearControls(){
        id.setText("");
        contact.setText("");
    }




}