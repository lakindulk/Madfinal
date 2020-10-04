package com.example.loulyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewUser extends AppCompatActivity {

    EditText txtID, txtName, txtAge, txtGender, txtArea, txtPW;
    Button butAdd;
    DatabaseReference dbRef;
    User usr;
    private String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);

        txtID = findViewById(R.id.inputID);
        txtName = findViewById(R.id.inputName);
        txtAge = findViewById(R.id.inputAge);
        txtGender = findViewById(R.id.inputGender);
        txtArea = findViewById(R.id.InputArea);
        txtPW = findViewById(R.id.inputPW);

        butAdd = findViewById(R.id.button10);

        usr = new User();

        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbRef = FirebaseDatabase.getInstance().getReference().child("User");

                try {
                    if(TextUtils.isEmpty(txtID.getText().toString()))
                        Toast.makeText(getApplicationContext(), "ID Feild Is Empty", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Name Feild Is Empty", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtAge.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Age Feild Is Empty", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtGender.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Gender Feild Is Empty", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtArea.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Province Feild Is Empty", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtPW.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Password Feild Is Empty", Toast.LENGTH_SHORT).show();
                    else {
                        usr.setID(txtID.getText().toString().trim());
                        usr.setName(txtName.getText().toString().trim());
                        usr.setAge(Integer.parseInt(txtAge.getText().toString().trim()));
                        usr.setGender(txtGender.getText().toString().trim());
                        usr.setArea(txtArea.getText().toString().trim());
                        usr.setPW(txtPW.getText().toString().trim());

                        ID = usr.getID();

                        dbRef.child(ID).setValue(usr);
                        Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();

                        String data1 = txtID.getText().toString();
                        String data2 = txtName.getText().toString();
                        String data3 = txtAge.getText().toString();
                        String data4 = txtGender.getText().toString();
                        String data5 = txtArea.getText().toString();
                        String data6 = txtPW.getText().toString();

                        Intent i = new Intent(getApplicationContext(), ViewSummary.class);
                        clearControls();

                        i.putExtra("id", data1);
                        i.putExtra("name", data2);
                        i.putExtra("age", data3);
                        i.putExtra("gender", data4);
                        i.putExtra("area", data5);
                        i.putExtra("pw", data6);

                        startActivity(i);

                    }
                }
                catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid Number Format Entered", Toast.LENGTH_SHORT).show();
                }


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