package com.suleakcay.sqlliteornekproje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText etAd,etSoyad,etTel;
    private Button btnKaydet,btnListele,btnSil,btnDuzenle;
    private ListView VeriListele;
    int idBul=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) { //asagida tanımlamarımzı yaptık
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etAd=(EditText) findViewById(R.id.etAd);
        etSoyad=(EditText) findViewById(R.id.etSoyad);
        etTel=(EditText) findViewById(R.id.etTel);
        btnKaydet=(Button) findViewById(R.id.btnKaydet);
        btnListele=(Button)findViewById(R.id.btnListele);
        VeriListele=(ListView)findViewById(R.id.VeriListele);
        btnSil=(Button)findViewById(R.id.btnSil);
        btnDuzenle=(Button)findViewById(R.id.btnDuzenle);

        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String incomingName=etAd.getText().toString();
                String incomingSurname=etSoyad.getText().toString();
                String incomingTel=etTel.getText().toString();

                VeriTabaniOlustur vt=new VeriTabaniOlustur(MainActivity.this);
                vt.DataItem(incomingName,incomingSurname,incomingTel);
            }
        });
        btnSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VeriTabaniOlustur vt = new VeriTabaniOlustur(MainActivity.this);
                vt.VeriSil(idBul);

                Listele();
            }
        });

        btnListele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Listele();
            }
        });

        btnDuzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ListView Tıkladığımızda otomatik dolan EditTextlerimizi Stringlere atıyalım
                String gelenAd = etAd.getText().toString();
                String gelenSoyad = etSoyad.getText().toString();
                String gelenTel = etTel.getText().toString();

                // Veritabanı bağlantımızı açalım ver ardından gerekli bilgileri VeriDuzenle metotuna gönderelim
                VeriTabaniOlustur vt = new VeriTabaniOlustur(MainActivity.this);
                vt.VeriDuzenle(idBul,gelenAd,gelenSoyad,gelenTel);
                Listele();
            }
        });


    }

    public void Listele(){
        VeriTabaniOlustur vt=new VeriTabaniOlustur(MainActivity.this);
        List<String> list=vt.VeriListele();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1,list);
        VeriListele.setAdapter(adapter);

    }
    public void ListViewItem(){
        VeriListele.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Tıklanan verimizi alıyoruz
                String item=VeriListele.getItemAtPosition(position).toString();
                //- gore boluyourz
                String[] itemBol=item.split(" - ");

                //id'mizi aliyoruz
                idBul = Integer.valueOf(itemBol[0].toString());
                etAd.setText(itemBol[1].toString());
                etSoyad.setText(itemBol[2].toString());
                etTel.setText(itemBol[3].toString());

            }
        });
    }
}
