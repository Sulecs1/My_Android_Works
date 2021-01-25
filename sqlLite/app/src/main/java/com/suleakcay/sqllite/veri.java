package com.suleakcay.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class veri extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "musteriler";
    private static final String TABLO_KISILER = "kisiler";
    private static final String ROM_ID_ = "id";
    private static final String ROW_AD_ = "ad";
    private static final String ROW_SOYAD_ = "soyad";
    private static final String ROW_TEL_ = "tel";
    private static final int DATABASE_VERSION = 1;


    public veri(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLO_KISILER + "("
                + ROM_ID_ + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ROW_AD_ + " TEXT NOT NULL, "
                + ROW_SOYAD_ + " TEXT NOT NULL, "
                + ROW_TEL_ + " TEXT NOT NULL)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // db.execSQL("DROP TABLE IF EXISTS "+TABLO_KISILER);
        onCreate(db);
    }

    public void VeriEkle(String ad, String soyad, String tel) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put(ROW_AD_, ad);
            cv.put(ROW_SOYAD_, soyad);
            cv.put(ROW_TEL_, tel);
            db.insert(TABLO_KISILER, null, cv);

        } catch (Exception e) {

        }
        db.close();
    }

    public List<String> VeriListele() {
        List<String> veriler = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String[] sutunler = {ROM_ID_, ROW_AD_, ROW_SOYAD_, ROW_TEL_};
            Cursor cursor = db.query(TABLO_KISILER,
                    sutunler, null, null, null, null, null);
            while (cursor.moveToNext()) {
                veriler.add(cursor.getInt(0)
                        + " - "
                        + cursor.getString(1)
                        + " - "
                        + cursor.getString(2)
                        + " - "
                        + cursor.getString(3));
            }
        } catch (Exception e) {
        }
        db.close();

        return veriler;
    }
}