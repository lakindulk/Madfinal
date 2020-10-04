package com.example.loulyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class ManageUser extends AppCompatActivity {
    public Button button1;
    public Button button2;
    public Button button3;
    public Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user2);

        button1 = (Button) findViewById(R.id.button);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageUser.this,AddNewUser.class);
                startActivity(intent);
            }
        });

        button2 = (Button) findViewById(R.id.button8);

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageUser.this,UpdateUser.class);
                startActivity(intent);
            }
        });

        button3 = (Button) findViewById(R.id.button5);

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageUser.this,DeleteUser.class);
                startActivity(intent);
            }
        });

        button4 = (Button) findViewById(R.id.button6);

        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageUser.this,ViewUsers.class);
                startActivity(intent);
            }
        });
    }
}