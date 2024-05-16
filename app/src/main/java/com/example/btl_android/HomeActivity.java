package com.example.btl_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //        // hien thi thong bao chao nguoid ugn
        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE) ;
        String username =  sharedPreferences.getString("username","") ;
        Log.d("SharedPreferences",username) ;
        Toast.makeText(getApplicationContext(), "Xin Chao "+ username, Toast.LENGTH_SHORT).show();

        // dang xuat
        CardView exit = findViewById(R.id.cardExit) ;
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit() ;
                editor.clear() ;
                editor.apply();
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
            }
        });


        // xu ly trang findoctor
        CardView finddoctor = findViewById(R.id.cardFindDoctor) ;
        finddoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,FindDoctorActivity.class));

            }
        });

        // xu ly trang labtest
        CardView labtest = findViewById(R.id.cardLabTest) ;
        labtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,LabTestActivity.class));

            }
        });

        CardView orderDetail = findViewById(R.id.cardOrderDetails);
        orderDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, OrderDetailActivity.class));
            }
        });

        CardView buyMedicine = findViewById(R.id.cardBuyMedicine);
        buyMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, BuyMedicineActivity.class));
            }
        });
    }
}