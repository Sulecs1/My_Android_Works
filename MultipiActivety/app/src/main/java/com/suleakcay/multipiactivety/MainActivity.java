package com.suleakcay.multipiactivety;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
     String userName;
     EditText editTexT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTexT=findViewById(R.id.editText);
        userName="";

    }
    public void changeActivity(View view){
        userName =editTexT.getText().toString();
        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
        intent.putExtra("userInput",userName);
        startActivity(intent);

    }
}
