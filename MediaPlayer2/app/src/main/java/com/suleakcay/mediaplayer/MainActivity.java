package com.suleakcay.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    Button buton_play;
    Button buton_pause;
    Button buton_stop;
    MediaPlayer mediaPlayer;
    int pauseCurrentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buton_play=(Button)findViewById(R.id.buton_play);
        buton_pause=(Button)findViewById(R.id.buton_pause);
        buton_stop=(Button)findViewById(R.id.buton_stop);

        buton_play.setOnClickListener(this);
        buton_pause.setOnClickListener(this);
        buton_stop.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buton_play:
                if(mediaPlayer==null){
                  mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.music);
                  mediaPlayer.start();
                }
                else if (!mediaPlayer.isPlaying()){
                    mediaPlayer.seekTo(pauseCurrentPosition);  //çalmanın tekrar devam edebilmesi için seekTo() kullandık
                    mediaPlayer.start();
                }

            break;

            case R.id.buton_pause:
                if(mediaPlayer!=null){
                    mediaPlayer.pause();
                    pauseCurrentPosition=mediaPlayer.getCurrentPosition(); // getCurrentPosition(), çalma ilerlemesini takip etmesi gereken Müzik çalar gibi uygulamalar için yararlı olan bir çağrı ile alınabilir .
                }
            break;

            case R.id.buton_stop:
           if(mediaPlayer!=null){
               mediaPlayer.stop();
               mediaPlayer=null;
           }
            break;

        }

    }
}
