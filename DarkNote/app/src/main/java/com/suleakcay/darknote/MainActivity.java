package com.suleakcay.darknote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView nav_view;
    RecyclerView noteList;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawer);
        noteList=findViewById(R.id.notelist);
        nav_view=findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            default:
                Toast.makeText(this,"Çok Yakında.",Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.settings){
            Toast.makeText(this,"Ayarlara tıklandı.",Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }
}
