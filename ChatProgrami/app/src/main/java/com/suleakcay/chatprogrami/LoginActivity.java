package com.suleakcay.chatprogrami;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    private Toolbar actionbarlogin;
    private EditText txtEmail,txtPassword;
    private Button btnlogin,btnRegisterlogin;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private void init(){
        actionbarlogin=(Toolbar)findViewById(R.id.actionbarlogin);
        setSupportActionBar(actionbarlogin);
        getSupportActionBar().setTitle("Giriş Yap");
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //geri okunu aktif ettim
        auth=FirebaseAuth.getInstance();
        currentUser=auth.getCurrentUser();
        txtEmail=(EditText)findViewById(R.id.txtEmaillogin);
        txtPassword=(EditText)findViewById(R.id.txtPasswordlogin);
        btnlogin=(Button)findViewById(R.id.btnlogin);
        btnRegisterlogin=(Button)findViewById(R.id.btnRegisterlogin);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();


            }
        });
        btnRegisterlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           goToRegisterActivity();
                
            }
        });
    }

    private void goToRegisterActivity() {
        Intent registeritem=new Intent(LoginActivity.this,RegistersActivity.class);
        startActivity(registeritem);
        finish();
    }

    private void loginUser() {
        String email=txtEmail.getText().toString();
        String password=txtPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Bu alan boş olamaz!",Toast.LENGTH_LONG).show();

        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Bu alan boş olamaz!",Toast.LENGTH_LONG).show();
        }else{ //her iki alanda doluysa
            btnlogin.setEnabled(false); //üst üste tıklanamaz
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this,"Giriş Başarılı!",Toast.LENGTH_LONG).show();
                        Intent newintent=new Intent(LoginActivity.this,MainActivity.class);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this,"Giriş Başarısız tekrar deneyiniz!",Toast.LENGTH_LONG).show();
                            btnlogin.setEnabled(true);  //kullanılmazsa buton tekrar aktif
                    }

                }
            });
        }

    }
}
