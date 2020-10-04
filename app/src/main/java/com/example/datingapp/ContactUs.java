package com.example.datingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.datingapp.MemberDetails.ContactDetails;
import com.example.datingapp.MemberDetails.Members;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactUs extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    EditText editText3;
    Button button;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        editText1=(EditText)findViewById(R.id.conname);
        editText2=(EditText)findViewById(R.id.conemail);
        editText3=(EditText)findViewById(R.id.conproblem);
        button=(Button)findViewById(R.id.consubmit);

        firebaseAuth=FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText1.getText().toString();
                String email = editText2.getText().toString();
                String problem = editText3.getText().toString();

                if (name.equals("")) {
                    editText1.setError("Name is required");
                }if (email.equals("")) {
                    editText2.setError("Email is required");
                }if (problem.equals("")) {
                    editText3.setError("Problem is required");
                }else{
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    final String userid = user.getUid();

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Contact Us");

                    ContactDetails contactDetails = new ContactDetails(name,email,problem);
                    myRef.child(userid).setValue(contactDetails);
                    Toast.makeText(ContactUs.this, "Problem sent! We'll contact you soon...", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}