package com.suleakcay.runnablehanmer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//Kronometre oluşturup kaç saniye sürdüğünü gördükten sonra tıklayacagız ve sıfırlanacak
public class MainActivity extends AppCompatActivity {
    TextView textView;
    int number;
    Runnable runnable;
    Handler handler;
    Button button2;
    private Object Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textView);
        Button=(Button)findViewById(R.id.button2);
        number=0;

    }
    public void start(View view)  {
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {

                    textView.setText("Time:"+number);
                    number++;
                    textView.setText("Time:"+number);
                    handler.postDelayed(runnable,1000);


            }
        };
        handler.post(runnable);


    }
    public void stop(View view){

    }
}
