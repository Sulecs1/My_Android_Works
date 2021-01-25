package com.suleakcay.ornek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaily);
        ImageView imageView=findViewById(R.id.imageView);
        TextView LandMarkName=findViewById(R.id.LandMarkName);
        TextView LandMarkName2=findViewById(R.id.landMarkName2);

        Intent intent=getIntent();
       String landmarkNamedizi= intent.getStringExtra("name");
        LandMarkName.setText(landmarkNamedizi);
    }
}
