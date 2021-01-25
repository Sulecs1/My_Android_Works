package com.suleakcay.mediaplayer;

import java.util.concurrent.TimeUnit;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    public TextView songName,startTimeField,endTimeField;
    private MediaPlayer mediaPlayer;
    private double startTime = 0;
    private double finalTime = 0;
    private Handler myHandler = new Handler();;
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private SeekBar seekbar;
    private ImageButton playButton,pauseButton;
    public static int oneTimeOnly = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Controller elementlerimizi tanımlıyoruz...
        songName = (TextView)findViewById(R.id.textView4);
        startTimeField =(TextView)findViewById(R.id.textView1);
        endTimeField =(TextView)findViewById(R.id.textView2);
        seekbar = (SeekBar)findViewById(R.id.seekBar1);
        playButton = (ImageButton)findViewById(R.id.imageButton1);
        pauseButton = (ImageButton)findViewById(R.id.imageButton2);
        songName.setText("beyoncesingleladies.mp3");
        ///res dizinin altına raw adında bir klasor acıp buraya çalıcak muzigimizi koyduk
        // ve burdada muzigi cagırarark MediaPlayer bu degeri gonderdik ...
        mediaPlayer = MediaPlayer.create(this, R.drawable.beyoncesingleladies);
        seekbar.setClickable(false);
        pauseButton.setEnabled(false);

    }
    //**Play butonuna basıldıgında çalısacak methodu yazıyoruz...

    public void play(View view){
        //Muziğin başladıgına dair Toast ile uyarı yazdırıyoruz..
        Toast.makeText(getApplicationContext(), "Müzik çalınıyor.", Toast.LENGTH_SHORT).show();
        // MediaPlayer başlatıyoruz...
        mediaPlayer.start();
        //Sesin millisaniye  cinsinden toplam süresini öğreniyoruz...
        finalTime = mediaPlayer.getDuration();
        startTime = mediaPlayer.getCurrentPosition();
        if(oneTimeOnly == 0){
            seekbar.setMax((int) finalTime);
            oneTimeOnly = 1;
        }
        //Muziğin toplamda ne kadar süre oldugunu  endTimeField controller ına yazdırıyoruz...
        endTimeField.setText(String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                toMinutes((long) finalTime)))
        );
        //Muziğin başladıgı andan itibaren gecen süreyi ,startTimeField controller ına yazdırıyoruz...
        startTimeField.setText(String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                toMinutes((long) startTime)))
        );
        //Muziğin hangi sürede oldugunu gostermek icin, seekbar kullarak gosteriyoruz...
        seekbar.setProgress((int)startTime);
        myHandler.postDelayed(UpdateSongTime,100);
        //Muzik suan calan durumnda oldugu icin , pause true yapıyoruz(durdurulabilir olması icin...)
        pauseButton.setEnabled(true);
        playButton.setEnabled(false);
    }
    //Muziğin çalma süresini gunceleyerek,sesin çalmasını devam etmesini saglıyoruz...
    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            startTimeField.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            //Muziğin hangi sürede oldugunu gostermek icin, seekbar kullarak gosteriyoruz...
            seekbar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };
    //Muzik çalarken, duraklatmayı saglayan method
    public void pause(View view){
        //Muziğin duraklatıldıgına  dair Toast ile uyarı yazdırıyoruz..
        Toast.makeText(getApplicationContext(), "Müzik durduruldu",Toast.LENGTH_SHORT).show();
        // MediaPlayer durduyoruz
        mediaPlayer.pause();
        //ve butonlarımızı pasif ,aktif durumunu düzenliyoruz..
        pauseButton.setEnabled(false);
        playButton.setEnabled(true);
    }
    //İleri butonuna bastgınızda,muzigin çalış süresini 5 saniye artırarak muzigi  ilerletir
    public void forward(View view){
        int temp = (int)startTime;
        if((temp+forwardTime)<=finalTime){
            startTime = startTime + forwardTime;
            mediaPlayer.seekTo((int) startTime);
        }
        else{

            //Muzigin çalıs suresi son 5 saniye geldiginde ,ileri tusuna basarsanız kosulu icin uyarı yazdıyoruz

            Toast.makeText(getApplicationContext(), "Son 5 saniyedeyken muziği ilerletemezsiz",
                    Toast.LENGTH_SHORT).show();
        }

    }
    //Geri butonuna bastgınızda,muzigin çalış süresini 5 saniye azaltarak muzigi  geriye alır
    public void rewind(View view){
        int temp = (int)startTime;
        if((temp-backwardTime)>0){
            startTime = startTime - backwardTime;
            mediaPlayer.seekTo((int) startTime);
        }
        else{
            //Muzigin çalıs suresi ilk 5 saniye gelmeden ,geri tusuna basarsanız kosulu icin uyarı yazdıyoruz
            Toast.makeText(getApplicationContext(),"İlk 5 saniyedeyken muziği geri alamazsınız",
                    Toast.LENGTH_SHORT).show();
        }

    }


}