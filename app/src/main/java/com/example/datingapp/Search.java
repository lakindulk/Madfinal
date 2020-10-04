package com.example.datingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = (SearchView) findViewById(R.id.searchView);
        listView = (ListView) findViewById(R.id.lv1);

        list = new ArrayList<>();
        list.add("Amal Perera");
        list.add("Dilshan Madushanka");
        list.add("Ashan Nadeesha");
        list.add("Praveen Perera");
        list.add("Lihini Dasanayake");
        list.add("Hashini Sandunika");
        list.add("Malshi Silva");
        list.add("Kaveen Lakshitha");
        list.add("Mihidu Ariyarathne");
        list.add("Kumari Siriwardana");
        list.add("Pathum");
        list.add("Siril");
        list.add("Banuka");
        list.add("Dhnu");
        list.add("lakindu");
        list.add("lkla");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(Search.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Search.this, "Added to favourite list!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}