package com.suleakcay.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    //Bu kısımda bileşenlerimizi tanımlıyoruz.
    private TextView textView;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Bu metod uygulama açıldığında çalıştırılan metod.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bu kısımda yukarıda tanımladığımız bileşenlerle xml olarak hazırladığımız bileşenleri birbirlerine bağlıyoruz.
        textView = (TextView)findViewById(R.id.textView1);
        editText = (EditText)findViewById(R.id.editText1);
        button = (Button)findViewById(R.id.button1);

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) { //Burada Buttona tıklandığında çalıştırılacak kodlar yer alıyor.

                //textView'e kullanıcının girdiği yazı set ediliyor.
                textView.setText(editText.getText());

            }
        });
    }
}