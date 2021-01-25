package com.suleakcay.sqliteornek2;



import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by um on 10.09.2015.
 */
public class TumKayitlar extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv=(TextView)findViewById(R.id.tvTumKayitlar);

        Veritabani db=new Veritabani(TumKayitlar.this);
        try {
            db.baglantiyiAc();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String tumKayitlar=db.tumKayitlar();

        db.baglantiyiKapat();




        tv.setText(tumKayitlar);


    }

}