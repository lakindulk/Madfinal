package com.example.loulyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class ViewSummary extends AppCompatActivity {

    TextView txtID, txtName, txtAge, txtGender, txtArea, txtPW;
    private Button butEdit,butSave;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_summary);

        butSave = (Button) findViewById(R.id.buttonSave);
        butSave.setOnClickListener((view) -> {
            Intent intent = new Intent(ViewSummary.this,MainActivity.class);
            startActivity(intent);

            Context context = getApplicationContext();

            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(getApplicationContext(), "Back To Admin Page", Toast.LENGTH_SHORT).show();

        });

        txtID = findViewById(R.id.sumID);
        txtName = findViewById(R.id.sumName);
        txtAge = findViewById(R.id.sumAge);
        txtGender = findViewById(R.id.sumGender);
        txtArea = findViewById(R.id.sumArea);
        txtPW = findViewById(R.id.sumPW);

        String id = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("name");
        String age = getIntent().getStringExtra("age");
        String gender = getIntent().getStringExtra("gender");
        String area = getIntent().getStringExtra("area");
        String pw = getIntent().getStringExtra("pw");

        txtID.setText(id);
        txtName.setText(name);
        txtAge.setText(age);
        txtGender.setText(gender);
        txtArea.setText(area);
        txtPW.setText(pw);

        butEdit = findViewById(R.id.buttonEdit);
        butEdit.setOnClickListener((view) -> {

            String data1 = txtID.getText().toString();
            String data2 = txtName.getText().toString();
            String data3 = txtAge.getText().toString();
            String data4 = txtGender.getText().toString();
            String data5 = txtArea.getText().toString();
            String data6 = txtPW.getText().toString();

            Intent in = new Intent(getApplicationContext(), UpdateUser.class);
            clear();

            in.putExtra("id", data1);
            in.putExtra("name", data2);
            in.putExtra("age", data3);
            in.putExtra("gender", data4);
            in.putExtra("area", data5);
            in.putExtra("pw", data6);

            startActivity(in);

        });

    }

    private void clear(){
        txtID.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtGender.setText("");
        txtArea.setText("");
        txtPW.setText("");
    }

}