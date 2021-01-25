package com.suleakcay.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText etAd,etSoyad,etTel;
    private Button btnKaydet;
    private Button Listele;
    private Button btnSil;
    private Button btnListele;
    private Button btnDuzenle;
    private ListView veriListele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etAd=(EditText)findViewById(R.id.etAd);
        etSoyad=(EditText)findViewById(R.id.etSoyad);
        etTel=(EditText)findViewById(R.id.etTel);
        btnKaydet=(Button)findViewById(R.id.btnKaydet);
        btnListele =(Button)findViewById(R.id.btnListele);
        btnDuzenle=(Button)findViewById(R.id.btnDuzenle);
        btnSil=(Button)findViewById(R.id.btnSil);
        veriListele=(ListView)findViewById(R.id.VeriListele);
        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gelenAd=etAd.getText().toString();
                String gelenSoyad=etSoyad.getText().toString();
                String gelenTel=etTel.getText().toString();

                veri vt=new veri(MainActivity.this);
                vt.VeriEkle(gelenAd,gelenSoyad,gelenTel);
            }
        });

        btnListele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Listele();
            }
        });

    }
    public void Listele(){
        veri vt = new veri(MainActivity.this);
        List<String> list = vt.VeriListele();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,android.R.id.text1,list);
        veriListele.setAdapter(adapter);
    }


}
