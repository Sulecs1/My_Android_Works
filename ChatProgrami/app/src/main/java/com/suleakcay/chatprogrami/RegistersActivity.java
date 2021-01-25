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

//import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistersActivity extends AppCompatActivity {


   private Toolbar actionbarRegister;
   private EditText txtUsername,txtEmail,txtPassword;
   private Button btnRegister,btnloginRegister;

   private FirebaseAuth auth;

    private void init(){
        actionbarRegister=(Toolbar)findViewById(R.id.actionbarRegister);
        setSupportActionBar(actionbarRegister);
        getSupportActionBar().setTitle("Hesap Oluştur");
        auth=FirebaseAuth.getInstance();

        txtUsername=(EditText)findViewById(R.id.txtUsernameReg);
        txtEmail=(EditText)findViewById(R.id.txtEmailReg);
        txtPassword=(EditText)findViewById(R.id.txtpasswordRegisters);
        btnRegister=(Button)findViewById(R.id.btnRegister);
        btnloginRegister=(Button)findViewById(R.id.btnloginRegister);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registers);
        init();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();

            }
        });
        btnloginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLoginActivity();
            }
        });
    }

    private void goToLoginActivity() {
        Intent loginintent=new Intent(RegistersActivity.this,LoginActivity.class);
        startActivity(loginintent);
        finish();
    }

    private void createNewAccount() {
        String username=txtUsername.getText().toString();  //yukarıdakı verileri çekeceğiz stringe çevirdik çünkü sayısal değerler de girilebilir
        String email=txtEmail.getText().toString();
        String password=txtPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Bu Alan Boş Olamaz!",Toast.LENGTH_LONG).show();  //uyarı mesajı yazdık

//activity context türü olduğu için this yazabiliyoruz ancak aşağıda yazamayız
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Bu Alan Boş Olamaz!",Toast.LENGTH_LONG).show();

        }else{
            //this olmaz çünkü içinde değil
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegistersActivity.this,"Bu Alan Boş Olamaz!",Toast.LENGTH_LONG).show();  //uyarı mesajı yazdık
                        Intent loginintent=new Intent(RegistersActivity.this,LoginActivity.class);
                        startActivity(loginintent);
                        finish();

                    }else{
                        Toast.makeText(RegistersActivity.this,"Bir Hata Oluştu !",Toast.LENGTH_LONG).show();
                    }
                }
            });

        }





    }
}
