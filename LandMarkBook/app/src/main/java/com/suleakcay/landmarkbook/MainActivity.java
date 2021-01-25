package com.suleakcay.landmarkbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView=findViewById(R.id.listView);
        ArrayList<String> landmarkNames=new ArrayList<>();

        landmarkNames.add("Eiffel");
        landmarkNames.add("Pisa");
        landmarkNames.add("Colleseca");
        landmarkNames.add("London Bridge");

        ArrayAdapter arrayAdapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,landmarkNames);
        listView.setAdapter(arrayAdapter);



    }
}
