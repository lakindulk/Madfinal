package com.example.datingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {

    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    TextView t5;
    TextView t6;
    TextView t7;
    ImageView imageButton;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    static String photo;
    DatabaseReference myRef;

    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        t1 = (TextView) findViewById(R.id.pname);
        t2 = (TextView) findViewById(R.id.pemail);
        t3 = (TextView) findViewById(R.id.pid);
        t4 = (TextView) findViewById(R.id.pphone);
        t5 = (TextView) findViewById(R.id.page);
        t6 = (TextView) findViewById(R.id.pgender);
        t7 = (TextView) findViewById(R.id.search);
        button1 = (Button) findViewById(R.id.pfav);
        button2 = (Button) findViewById(R.id.pfeedback);
        button3 = (Button) findViewById(R.id.pchangeprofile);
        button4 = (Button) findViewById(R.id.presetpass);
        button5 = (Button) findViewById(R.id.logout);
        button6 = (Button) findViewById(R.id.pic);
        imageButton = (ImageView) findViewById(R.id.imageButton);

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

                t1.setText("Name:- " + name);
                t2.setText("Email:- " + email);
                t3.setText("ID:- " + id);
                t4.setText("Phone No:- " + phone);
                t5.setText("Age:- " + age);
                t6.setText("Gender:- " + gender);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfile.this, Favourite.class);
                startActivity(intent);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(UserProfile.this, MainActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfile.this, Feedback.class);
                startActivity(intent);
            }
        });

        t7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfile.this, Search.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfile.this, ChangeProfile.class);
                startActivity(intent);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

    }

    public void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            imageButton.setImageURI(imageUri);
        }

    }
}
