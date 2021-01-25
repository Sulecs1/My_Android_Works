package com.suleakcay.catchcharacter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timeText;
    TextView scoreText;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] ImageDizi;
    Handler handler;
    Runnable runnable;

    int sayac=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeText=(TextView)findViewById(R.id.timeText);
        scoreText=(TextView)findViewById(R.id.scoreText);
        imageView=(ImageView)findViewById(R.id.imageView);
        imageView2=(ImageView)findViewById(R.id.imageView2);
        imageView3=(ImageView)findViewById(R.id.imageView3);
        imageView4=(ImageView)findViewById(R.id.imageView4);
        imageView5=(ImageView)findViewById(R.id.imageView5);
        imageView6=(ImageView)findViewById(R.id.imageView6);
        imageView7=(ImageView)findViewById(R.id.imageView7);
        imageView8=(ImageView)findViewById(R.id.imageView8);
        imageView9=(ImageView)findViewById(R.id.imageView9);
        ImageDizi=new ImageView[]{imageView,imageView2,imageView3,imageView4,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        Reset();
        new CountDownTimer(30000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time:"+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(),"Finished",Toast.LENGTH_LONG).show();
                timeText.setText("Timeoff!");
                handler.removeCallbacks(runnable);
                for(ImageView image:ImageDizi){//asagıda hepsinin gorunmez yapacaktır
                    image.setVisibility(View.INVISIBLE);
                }
                scoreText.setText("Finished");
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart");
                alert.setMessage("Are you sure restart game?");
                alert.setPositiveButton("Yees!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Game Over!",Toast.LENGTH_LONG).show();
                    }
                });
                    alert.show();
            }
        }.start();

    }

    public void increaseScore(View view){
        scoreText.setText("Score :"+(sayac++));
    }
    public void Reset(){
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for(ImageView image:ImageDizi){//asagıda hepsinin gorunmez yapacaktır
                    image.setVisibility(View.INVISIBLE);
                }
                Random rand=new Random();
                int i=rand.nextInt(9);
                ImageDizi[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,100);
            }
        };

        handler.post(runnable);
    }
}
