package com.example.loulyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import static com.google.firebase.auth.FirebaseAuth.*;

public class DeleteUser extends AppCompatActivity {

    EditText txtID;
    Button butDelete;
    DatabaseReference dbRef;
    User usr;
    private String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        txtID = findViewById(R.id.deleteID);

        butDelete = findViewById(R.id.button2);

        usr = new User();

        butDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usr.setID(txtID.getText().toString().trim());
                ID = usr.getID();

                dbRef = FirebaseDatabase.getInstance().getReference().child("User").child(ID);
                dbRef.removeValue();
                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }
}