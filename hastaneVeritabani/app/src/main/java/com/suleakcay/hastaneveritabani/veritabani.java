package com.suleakcay.hastaneveritabani;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class veritabani {

    private static final String databaseName="Hastalar";

    private static final String tablePerson="tumhastalar";
    private static final int databaseVersion=1;

    private final Context context;
    private SQLiteDatabase veritabani;
    private VeritabaniHelper veritabanihelper;

    //Oluturulacak hastaListesinin sutunları
    public static final String KEY_ROW_ID="id";
    public static final String KEY_ISIM = "isim";
    public static final String KEY_TELEFON = "telefon";
    public static final String KEY_DURUM="durum";


    public veritabani(Context c) {
        this.context = c;
    }

    public veritabani baglantiyiAc() throws  Exception{
        veritabanihelper=new VeritabaniHelper(context);
        veritabani=veritabanihelper.getWritableDatabase();
        return this;
    }
    public void baglantiyikapa(){
        veritabanihelper.close();
    }



    private static class VeritabaniHelper extends SQLiteOpenHelper{

        public VeritabaniHelper(Context context) {
            super(context,databaseName,null,databaseVersion);

        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "+ tablePerson +" (" + KEY_ROW_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_ISIM
            +" TEXT NOT NULL, " + KEY_TELEFON + " TEXT NOT NULL, " +KEY_DURUM
             +" TEXT NOT NULL);");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+tablePerson);
            onCreate(db);

        }
        // isim yaz bilgisini kaydettte kaldım

    }

}
