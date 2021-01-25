package com.suleakcay.onek1;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView textView;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {  //uygulama açılında çalışan metot
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView)findViewById(R.id.textViem1);
        editText=(EditText)findViewById(R.id.editText1);
        button=(Button)findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(editText.getText());
            }
        });

    }
}
