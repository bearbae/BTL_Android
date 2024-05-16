package com.example.btl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername, edEmail, edPassword, edCfPassword ;
    Button btn ;
    TextView tv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edUsername = findViewById(R.id.LTBFullName) ;
        edEmail = findViewById(R.id.LTBCode) ;
        edPassword = findViewById(R.id.LTBSdt) ;
        edCfPassword = findViewById(R.id.LTBDiaChi) ;
        btn = findViewById(R.id.LTBButton) ;
        tv = findViewById(R.id.textViewExit) ;
        // chuyen trang dang nhap
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
        // bat su kienj button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString() ;
                String email = edEmail.getText().toString() ;
                String password = edPassword.getText().toString() ;
                String cfpassword = edCfPassword.getText().toString() ;
                Database db = new Database(getApplicationContext(),"healthCare", null, 1);


                if(username.length() == 0 & password.length() == 0 & cfpassword.length() == 0 & email.length() == 0 ){
                    Toast.makeText(getApplicationContext(), "Điền đầy đủ thông tin!",Toast.LENGTH_LONG).show();

                }
                else
                {
                    if(password.compareTo(cfpassword) == 0){
                        if(IsValid(password)){
                            db.register(username,email,password);
                            Toast.makeText(getApplicationContext(), "Đăng ký thành công!",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Mật khẩu phải có 8 ký tự trở lên, có chữ hoa, chữ thường và số!",Toast.LENGTH_LONG).show();

                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Mật khẩu chưa đồng bộ. Hãy thử lại!",Toast.LENGTH_LONG).show();

                    }

                }
            }
        });
    }

    // check mat khau
    public static boolean IsValid(String password){
        int a1 = 0, a2 = 0 , a3 = 0 ;
        if(password.length() < 8){
            return false ;
        }
        else{
            for(int p = 0 ; p < password.length() ; p++){
                if(Character.isLetter(password.charAt(p))){
                    a1 = 1 ;
                }
            }
            for(int r = 0 ; r < password.length() ; r++){
                if(Character.isLetter(password.charAt(r))){
                    a2  = 1 ;
                }
            }
            for(int s = 0 ; s < password.length() ; s++){
                char c  = password.charAt(s) ;
                if(c >= 33 & c <= 46 || c == 64){
                    a3 = 1 ;
                }
            }
            if(a1 ==1 & a2 == 1 & a3 == 1) return true ;
            return false ;
        }
    }
}