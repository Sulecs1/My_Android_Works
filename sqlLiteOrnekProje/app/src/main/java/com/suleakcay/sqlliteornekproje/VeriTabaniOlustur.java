package com.suleakcay.sqlliteornekproje;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class VeriTabaniOlustur extends SQLiteOpenHelper {

    private  static  final String databaseName="customers";
    private static final int databaseVersion=1;
    private static final  String tablePersons="persons";
    private  static final String rowID="id";
    private  static final String rowName="name";
    private static final String rowSurname="surname";
    private static final String rowTel="tel";

    public VeriTabaniOlustur( Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //tablomuzu olusturduk tek bir tablo olusturduk
        db.execSQL("CREATE TABLE "+tablePersons+ "("
              +rowID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
              +rowName+ " TEXT NOT NULL, "
              +rowSurname+" TEXT NOT NULL, "
              +rowTel+" TEXT NOT NULL)"

        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ tablePersons);
        onCreate(db);
    }

    public void  DataItem(String name,String surname,String tel){
        SQLiteDatabase db=this.getWritableDatabase();
        try{
            ContentValues content=new ContentValues();
            content.put(rowName,name);
            content.put(rowSurname,surname);
            content.put(rowTel,tel);
            db.insert(tablePersons,null,content);
        }catch (Exception ex){

        }
        db.close();
    }
    public List<String> VeriListele(){
        List<String> veriler=new ArrayList<String>();
        SQLiteDatabase db=this.getWritableDatabase();
        try{
            String[] stunlar={rowID,rowName,rowSurname,rowTel};
            Cursor cursor=db.query(tablePersons,stunlar,null,null,null,null,null);
            while(cursor.moveToNext()){
                veriler.add(cursor.getInt(0)
                        +" - "
                        +cursor.getString(1)
                        +" - "
                        +cursor.getString(2)
                        +" - "
                        +cursor.getString(3));


            }
        }catch (Exception ex){

        }
        db.close();
        return veriler;
    }
    public void VeriSil(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            //verimizi id ye gore siliyoruz
         //   String where = rowID + " = " + id ;
            db.delete(tablePersons,rowID+ "="+id,null);
        }catch (Exception ex){

        }db.close();
    }


    public void VeriDuzenle(int id, String ad, String soyad, String tel){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put(rowName, ad);
            cv.put(rowSurname, soyad);
            cv.put(rowTel, tel);
            String where = rowID +" = '"+ id + "'";
            db.update(tablePersons,cv,where,null);
        }catch (Exception e){

        }
        db.close();
    }

}

