package com.suleakcay.artbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//bir xml yaptıgınzda onu actity içinde göstermek için Inflater kullanılır
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add_art,menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add_art_item){
            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
