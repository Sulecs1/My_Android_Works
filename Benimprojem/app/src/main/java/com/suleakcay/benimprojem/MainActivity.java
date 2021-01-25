package com.suleakcay.benimprojem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private Button button1;
    private Button button2;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //yukarıda tanımladıgımız nesnelerimizi baglıyoruz

        textView1=(TextView)findViewById(R.id.textView1);
        textView2=(TextView)findViewById(R.id.textView2);
        textView3=(TextView)findViewById(R.id.textView3);
        textView4=(TextView)findViewById(R.id.textView4);
        editText1=(EditText)findViewById(R.id.editText1);
        editText2=(EditText)findViewById(R.id.editText2);
        editText3=(EditText)findViewById(R.id.editText3);
        editText4=(EditText)findViewById(R.id.editText4);
        button1=(Button)findViewById(R.id.button1);


        button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setText(editText1.getText());

                textView2.setText(editText2.getText());
                textView3.setText(editText3.getText());
                textView4.setText(editText4.getText());


            }


        });
     button1.clearComposingText();
    }

}
