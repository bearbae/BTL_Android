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

public class BuyMedicineBookActivity extends AppCompatActivity {
    EditText edname , edaddress, edcode, edcontact ;
    Button btnBook ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edname = findViewById(R.id.BookBmFullName) ;
        edaddress = findViewById(R.id.BookBmDiaChi) ;
        edcode = findViewById(R.id.BookBMCode) ;
        edcontact =  findViewById(R.id.BookBMSDT) ;
        btnBook = findViewById(R.id.BookBmButton) ;


        Intent it = getIntent() ;
        String[] price = it.getStringExtra("price").toString().split(Pattern.quote(":")) ;
        String date = it.getStringExtra("date" );


        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE) ;
                String username =  sharedPreferences.getString("username","") ;
                Database db = new Database(getApplicationContext(), "healthCare", null, 1) ;
                db.addOrder(username,edname.getText().toString(),edaddress.getText().toString(),edcontact.getText().toString(),Integer.parseInt(edcode.getText().toString()),date.toString(),"0",Float.parseFloat(price[1].toString()),"Mua thuốc");
//                db.addOrder(username,edname.getText().toString(),edcontact.getText().toString(),edcontact.getText().toString(),Integer.parseInt(edcode.getText().toString()),date.toString(),time.toString(),2736,"Lab");
                db.remove(username, "Mua Thuốc");
                Toast.makeText(getApplicationContext(),"Đặt mua thành công", Toast.LENGTH_LONG).show(); ;
                startActivity(new Intent(BuyMedicineBookActivity.this, HomeActivity.class));
            }
        });
    }
}