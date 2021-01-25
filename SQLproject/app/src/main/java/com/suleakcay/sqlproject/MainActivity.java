package com.suleakcay.sqlproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase database=this.openOrCreateDatabase("Musicans",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS musicans(id INTEGER PRIMARY KEY ,name VARCHAR,age INT)");

         //  database.execSQL("INSERT INTO musicans(name , age)VALUES ('James',50)");
           //database.execSQL("INSERT INTO musicans(name , age)VALUES ('Larce',20)");
            database.execSQL("UPDATE musicans SET  age=20  WHERE name ='Larce'");
            //okumak için
            Cursor cursor=database.rawQuery("SELECT*FROM musicans WHERE age=50",null);
            int nameIx=cursor.getColumnIndex("name");
            int ageIx=cursor.getColumnIndex("age");
            int idIx=cursor.getColumnIndex("id");
            while(cursor.moveToNext()){
                System.out.println("Name:"+cursor.getString(nameIx));
                System.out.println("Age:"+cursor.getInt(ageIx));
                System.out.println("Id:"+cursor.getInt(idIx));

            }
            cursor.close();


        }catch (Exception e){
            e.printStackTrace();
        }




    }
}
