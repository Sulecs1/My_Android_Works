package com.suleakcay.sharedornek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;  //prefences için bir nesne tanımladık
    SharedPreferences.Editor editor;

    EditText editname;
    EditText editsurname;
    Button btnsave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //veri silmek için
        preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor=preferences.edit();
        editname=(EditText) findViewById(R.id.editname);
        editsurname=(EditText)findViewById(R.id.editsurname);
        //ad bilgisi kayıtlı ise dil_bilgisine aktıracak degil ise Ad giriniz stringi aktarılacak
        String ad_bilgisi=preferences.getString("adi","Ad Giriniz");
        String soyad_bilgisi=preferences.getString("soyadi","Soyad girin");

        editname.setText(ad_bilgisi);
        editname.setText(soyad_bilgisi);

        btnsave=(Button)findViewById(R.id.btnsave);
        btnsave.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                editor.putString("adi",editname.getText().toString());
                editor.putString("soyadi",editsurname.getText().toString());
                editor.commit();  //bilgileri ekle ve kaydet

            }
        });

    }
    public boolean onCreateOptionsMEnu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_main,menu);
        return true;

    }
}
