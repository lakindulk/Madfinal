package com.example.datingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ChangeProfile extends AppCompatActivity {

    TextView t1;
    EditText e1;
    EditText e2;
    EditText e3;
    EditText e4;
    EditText e5;
    ImageView imageView;
    Button button1;
    Button button2;
    DatabaseReference myRef;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile);

        e1 = (EditText)findViewById(R.id.cname);
        e2=(EditText)findViewById(R.id.cemail);
        t1 = (TextView) findViewById(R.id.cid);
        e3=(EditText)findViewById(R.id.cphone);
        e4=(EditText)findViewById(R.id.cage);
        e5=(EditText)findViewById(R.id.cgender);
        button1=(Button)findViewById(R.id.subc);
//        button2=(Button)findViewById(R.id.ch);

//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openGallery();
//            }
//        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String userid = user.getUid();

        myRef = FirebaseDatabase.getInstance().getReference().child("Members").child(userid);
        System.out.println(userid);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue().toString();
                String email = snapshot.child("email").getValue().toString();
                String id = snapshot.child("id").getValue().toString();
                String phone = snapshot.child("phone").getValue().toString();
                String age = snapshot.child("age").getValue().toString();
                String gender = snapshot.child("gender").getValue().toString();

                e1.setText(name);
                e2.setText(email);
                t1.setText(id);
                e3.setText(phone);
                e4.setText(age);
                e5.setText(gender);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = e1.getText().toString();
                String email = e2.getText().toString();
                String id = t1.getText().toString();
                String phone = e3.getText().toString();
                String age = e4.getText().toString();
                String gender = e5.getText().toString();
//
//                HashMap<String, Object> hashMap = new HashMap<String, Object>();
//                hashMap.put("name",name);
//                hashMap.put("email",email);
//                hashMap.put("id",id);
//                hashMap.put("phone",phone);
//                hashMap.put("age",age);
//                hashMap.put("gender",gender);
//
//                myRef.child(userid).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
//                    @Override
//                    public void onSuccess(Object o) {
//                        Toast.makeText(ChangeProfile.this, "Data updated successfully!", Toast.LENGTH_SHORT).show();
//                    }
//                });
                if(name.equals("")) {
                    e1.setError("Name is required");
                }if(email.equals("")){
                    e2.setError("ID is required");
                }if(id.equals("")){
                    t1.setError("Email is required");
                }if(phone.equals("") || phone.length() < 10){
                    e3.setError("Phone number is required");
                }if(age.equals("")){
                    e4.setError("Age is required");
                }if(gender.equals("")) {
                    e5.setError("Gender is required");
                }
                else {

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Members").child(userid);

                HashMap map = new HashMap();
                map.put("name", name);
                map.put("email",email);
                map.put("id",id);
                map.put("phone",phone);
                map.put("age",age);
                map.put("gender",gender);
                ref.updateChildren(map);

                Toast.makeText(ChangeProfile.this, "Data updated successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChangeProfile.this, UserProfile.class);
                    startActivity(intent);
                }
            }
        });

    }

//    private void openGallery() {
//        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//        startActivityForResult(gallery, PICK_IMAGE);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
//            imageUri = data.getData();
//            imageView.setImageURI(imageUri);
//        }
//
//    }
}