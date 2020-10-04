package com.example.loulydatingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    EditText txtPhone;
    Button btn5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtPhone = findViewById(R.id.etPhone);
        btn5 = findViewById(R.id.btnContinue);

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(txtPhone.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();

                else if(!txtPhone.getText().toString().trim().matches("[0-9]{10}"))
                    txtPhone.setError("Invalid Card Number");

                else {

                    Intent intent = new Intent(Main2Activity.this, MainActivity3.class);
                    startActivity(intent);
                    clearContent();
                }
            }
        });
    }

    public void clearContent(){

        txtPhone.setText("");

    }
}