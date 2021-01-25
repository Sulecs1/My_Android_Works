package com.suleakcay.radyomake;

import androidx.appcompat.app.AppCompatActivity;
;


import android.media.AudioManager;
import android.media.MediaPlayer;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;



public class MainActivity extends AppCompatActivity {

    private String streamUrl = "http://fenomenoriental.listenfenomen.com/fenomenpop/128/icecast.audio ";
    private Button startBtn;
    private Button stopBtn;
    private MediaPlayer player;
    public static boolean isAlreadyPlaying = false;
    private AudioManager audioManager;
    private SeekBar volumeBar;

    @Override
    protected void onResume() {
        super.onResume();

        if(isAlreadyPlaying){

            playRadioPlayer();
        }else{
            stopRadioPlayer();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioManager = (AudioManager)getSystemService(getApplicationContext().AUDIO_SERVICE);

        volumeBar = (SeekBar) findViewById(R.id.seekBar) ;
        volumeBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_RING));
        volumeBar.setKeyProgressIncrement(10);
        volumeBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_RING));

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // uyku modunu kapatma

        startBtn = (Button) findViewById(R.id.buttonPlay);
        stopBtn = (Button) findViewById(R.id.buttonStopPlay);
        stopBtn.setEnabled(false);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                isAlreadyPlaying = true;
                playRadioPlayer();


            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isAlreadyPlaying = false;
                stopRadioPlayer();

            }
        });
        initializeMediaPlayer();

        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void playRadioPlayer() {

        stopBtn.setEnabled(true);
        startBtn.setEnabled(false);
        player.prepareAsync();
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mp) {
                player.start();
            }
        });
    }

    private void stopRadioPlayer() {

        if (player.isPlaying()) {
            player.stop();
            player.release();
            initializeMediaPlayer();
        }

        startBtn.setEnabled(true);
        stopBtn.setEnabled(false);
    }

    private void initializeMediaPlayer() {
        player = new MediaPlayer();
        try {
            player.setDataSource(streamUrl);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player.isPlaying()) {
            player.stop();
        }
    }
}