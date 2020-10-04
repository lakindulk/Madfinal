package com.example.loulydatingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity8 extends AppCompatActivity {

    private double packageTotal;
    Packages pack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        Button btnMonth1 = findViewById(R.id.btnMonth1);
        btnMonth1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pack = new Packages();
                Intent intent = new Intent(MainActivity8.this, MainActivity9.class);
                packageTotal = pack.getPackgeTotal(1);

                intent.putExtra("value", "Package Price Rs.2000.00/Mo");
                intent.putExtra("packTotal", "Package Total = "+packageTotal);

                startActivity(intent);
            }
        });

        Button btnMonth6 = findViewById(R.id.btnMonth6);
        btnMonth6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pack = new Packages();
                Intent intent = new Intent(MainActivity8.this, MainActivity9.class);
                packageTotal = pack.getPackgeTotal(2);

                intent.putExtra("value", "Package Price Rs.2000.00/Mo + 10% Discount");
                intent.putExtra("packTotal", "Package Total = "+packageTotal);

                startActivity(intent);
            }
        });

        Button btnMonth12 = findViewById(R.id.btnMonth12);
        btnMonth12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pack = new Packages();
                Intent intent = new Intent(MainActivity8.this, MainActivity9.class);
                packageTotal = pack.getPackgeTotal(3);

                intent.putExtra("value", "Package Price Rs.2000.00/Mo + 15% Discount");
                intent.putExtra("packTotal","Package Total = "+packageTotal);

                startActivity(intent);
            }
        });
    }
}