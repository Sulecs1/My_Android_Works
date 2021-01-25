package com.suleakcay.chatprogrami;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button hesabim,yenihesap;
    public void init(){
        hesabim=(Button)findViewById(R.id.hesabim);
        yenihesap=(Button)findViewById(R.id.yenihesap);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        hesabim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentgiris=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intentgiris);
                finish();

            }
        });
        yenihesap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentyenihesap=new Intent(MainActivity.this,RegistersActivity.class);
                startActivity(intentyenihesap);
                finish();
            }
        });
    }
}
