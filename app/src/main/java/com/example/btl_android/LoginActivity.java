package com.example.btl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edUsername, edPassword ;
    Button btn ;
    TextView tv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.LTBFullName) ;
        edPassword = findViewById(R.id.LTBSdt) ;
        btn = findViewById(R.id.LTBButton) ;
        tv = findViewById(R.id.textViewNewUser) ;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                String username = edUsername.getText().toString() ;
                String password = edPassword.getText().toString() ;
                Database db = new Database(getApplicationContext(), "healthCare", null, 1) ;
                if(username.length() == 0 & password.length() == 0){
                    Toast.makeText(getApplicationContext(), "Điền đầy đủ thông tin!",Toast.LENGTH_LONG).show();

                }
                else{
                    if(db.login(username,password) == 1){
                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công",Toast.LENGTH_LONG).show();

                        SharedPreferences sharedPreferences = getSharedPreferences("save", Context.MODE_PRIVATE) ;
                        SharedPreferences.Editor editor = sharedPreferences.edit() ;
                        editor.putString("username", username) ;
                        editor.commit() ;
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Tài khoản hoặc mật khẩu không hợp lệ!",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, LoginActivity.class));


                    }

                }
            }
        });

        // chuyen trang dang ky
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }
}