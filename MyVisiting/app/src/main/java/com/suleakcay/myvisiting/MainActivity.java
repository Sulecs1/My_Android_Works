package com.suleakcay.myvisiting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.yerekle,menu);

        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.yerekle){
            Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView=(ListView)findViewById(R.id.ListView);
    }




}
