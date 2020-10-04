package com.example.datingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Feedback extends AppCompatActivity {

    EditText editText;
    Button button1;
    Button button2,button3,button4,button5;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    Feedback fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        editText=(EditText)findViewById(R.id.review);
        button1=(Button)findViewById(R.id.submit);
        button2=(Button)findViewById(R.id.contactus);
        button3=(Button)findViewById(R.id.fview);



        firebaseAuth=FirebaseAuth.getInstance();

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Feedback.this, ContactUs.class);
                startActivity(intent);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Feedback");

                final String feedback = editText.getText().toString();

                if (feedback.equals("")) {

                    editText.setError("Feedback is required");



                }
                    else {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    final String userid = user.getUid();

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Feedback");

                    myRef.child(userid).setValue(feedback);
                    Toast.makeText(Feedback.this, "Review submission complete! Thank you...", Toast.LENGTH_SHORT).show();
                    editText.getText().clear();

                }
            }

        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Feedback.this, ideas.class));

            }
        });



    }

}