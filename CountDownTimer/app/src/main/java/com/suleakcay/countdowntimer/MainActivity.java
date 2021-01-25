package com.suleakcay.countdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textView);
        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {  //Her 1000 saniyede 1000 ne yapayım ?
                textView.setText("Left:"+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() { //bitince ne yapayım
                Toast.makeText(getApplicationContext(),"Bitti!",Toast.LENGTH_LONG).show();
                textView.setText("Finish");
            }
        }.start();
    }
}
