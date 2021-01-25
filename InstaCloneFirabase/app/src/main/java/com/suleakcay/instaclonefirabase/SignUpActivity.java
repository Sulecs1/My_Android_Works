package com.suleakcay.instaclonefirabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
        Button btnsignin;
        Button btnsignUp;
        EditText emailText;
        EditText paswordText;
        private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnsignin=(Button)findViewById(R.id.btnsignin);
        btnsignUp=(Button)findViewById(R.id.btnsignUp);
        emailText=(EditText)findViewById(R.id.emailText);
        paswordText=(EditText)findViewById(R.id.paswordText);
        firebaseAuth=FirebaseAuth.getInstance();

        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();  //içeri giriş yapmış birisi varsa kullanıcıya gösteirr
        if(firebaseUser!=null){
            Intent intent=new Intent(SignUpActivity.this,FeedActivity.class);
            startActivity(intent);
            finish(); //geri yapınca geri  dönmemek için
        }


    }
    public void signInClick(View view){
        String email=emailText.getText().toString();
        String password=paswordText.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Intent intent=new Intent(SignUpActivity.this,FeedActivity.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
               Toast.makeText(SignUpActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();

            }
        });

    }
    public void signupClick(View view){
       //kullanıcı kayıt etme
        String email=emailText.getText().toString();
        String password=paswordText.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(SignUpActivity.this,"Kullanıcı olusturuldU!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(SignUpActivity.this,FeedActivity.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Firabeseden hata mesajı gelir
                Toast.makeText(SignUpActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();


            }
        });
    }
}
