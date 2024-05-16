package com.example.btl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class LabTestBookActivity extends AppCompatActivity {
    EditText edname , edaddress, edcode, edcontact ;
    Button btnBook ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        edname = findViewById(R.id.LTBFullName) ;
        edaddress = findViewById(R.id.LTBDiaChi) ;
        edcode = findViewById(R.id.LTBCode) ;
        edcontact =  findViewById(R.id.LTBSdt) ;
        btnBook = findViewById(R.id.LTBButton) ;


        Intent it = getIntent() ;
        String[] price = it.getStringExtra("price").toString().split(Pattern.quote(":")) ;
        String date = it.getStringExtra("date" );
        String time = it.getStringExtra("time" );

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE) ;
                String username =  sharedPreferences.getString("username","") ;
                Database db = new Database(getApplicationContext(), "healthCare", null, 1) ;
                db.addOrder(username,edname.getText().toString(),edaddress.getText().toString(),edcontact.getText().toString(),Integer.parseInt(edcode.getText().toString()),date.toString(),time.toString(),Float.parseFloat(price[1].toString()),"Khám bệnh");
                db.remove(username, "Khám bệnh");
                Toast.makeText(getApplicationContext(),"Đặt mua thành công", Toast.LENGTH_LONG).show(); ;
                startActivity(new Intent(LabTestBookActivity.this, HomeActivity.class));
            }
        });
    }
}