package com.example.loulyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.TextUtils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateUser extends AppCompatActivity {

    EditText txtID, txtName, txtAge, txtGender, txtArea, txtPW;
    Button butUpdate;
    DatabaseReference db;
    User usr;
    private String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        txtID = findViewById(R.id.updateID);
        txtName = findViewById(R.id.updateName);

        txtAge = findViewById(R.id.updateAge);
        txtGender = findViewById(R.id.updateGender);
        txtArea = findViewById(R.id.updateArea);
        txtPW = findViewById(R.id.updatePW);

        butUpdate = findViewById(R.id.button13);

        usr = new User();

        butUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db = FirebaseDatabase.getInstance().getReference();
                usr.setID(txtID.getText().toString().trim());
                ID = usr.getID();

                db.child("User").child(ID).child("id").setValue(txtID.getText().toString().trim());
                db.child("User").child(ID).child("name").setValue(txtName.getText().toString().trim());
                db.child("User").child(ID).child("age").setValue(Integer.parseInt(txtAge.getText().toString().trim()));
                db.child("User").child(ID).child("gender").setValue(txtGender.getText().toString().trim());
                db.child("User").child(ID).child("area").setValue(txtArea.getText().toString().trim());
                db.child("User").child(ID).child("pw").setValue(txtPW.getText().toString().trim());

                Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getApplicationContext(), MainActivity.class);
                clearControls();
            }
        });


    }
    private void clearControls(){
        txtID.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtGender.setText("");
        txtArea.setText("");
        txtPW.setText("");
    }
}