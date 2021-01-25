package com.suleakcay.ornek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listView=findViewById(R.id.listView);
        //Data
        final ArrayList<String> landmarkNames=new ArrayList<>();

        landmarkNames.add("Turkiye");
        landmarkNames.add("Danimarka");
        landmarkNames.add("Almanya");
        landmarkNames.add("İskoçya");
        landmarkNames.add("Fransa");
        landmarkNames.add("Afrika");
        landmarkNames.add("Çin");
        landmarkNames.add("Rusya");


        final ArrayList <String> countrypopulation=new ArrayList<>();
        countrypopulation.add("80.000");
        countrypopulation.add("20.000");
        countrypopulation.add("75.000");
        countrypopulation.add("22.000");
        countrypopulation.add("30.000");
        countrypopulation.add("10.000");
        countrypopulation.add("100.000");
        countrypopulation.add("88.888");

        Bitmap turk= BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.turk);
        Bitmap danimarka=BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.danimarka);
        Bitmap almanya=BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.almanya);
        Bitmap iskocya=BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.iskocya);
        Bitmap fransa=BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.fransa);
        Bitmap afrika=BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.afrika);
        Bitmap cin=BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.cin);
        Bitmap rusya=BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.rusya);

        final ArrayList<Bitmap> landmarkImages=new ArrayList<>();
        landmarkImages.add(turk);
        landmarkImages.add(danimarka);
        landmarkImages.add(almanya);
        landmarkImages.add(iskocya);
        landmarkImages.add(fransa);
        landmarkImages.add(afrika);
        landmarkImages.add(cin);
        landmarkImages.add(rusya);


        ArrayAdapter arrayAdapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,landmarkNames);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                //Herhangi itema tıklanacagında ne olacagının gosterir
             //   System.out.println(landmarkNames.get(i));
               // System.out.println(countrypopulation.get(i));
                Intent intent=new Intent(getApplicationContext(),DetailyActivity.class);
                intent.putExtra("name",landmarkNames.get(i));
                startActivity(intent);

            }
        });

    }
}
