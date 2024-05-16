package com.example.btl_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class FindDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        // btnback
        CardView exit = findViewById(R.id.cardFindBack) ;
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(FindDoctorActivity.this,HomeActivity.class));
            }
        });

        // hien thi trang chi tiet item
        CardView familyphysician  = findViewById(R.id.cardFindFamilyPhysician) ;
        familyphysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity.this, DoctorDetailActivity.class) ;
                it.putExtra("title","Bác Sĩ Gia Đình") ;
                startActivity(it);
            }
        });

        CardView dietician  = findViewById(R.id.cardFindDietician) ;
        dietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity.this, DoctorDetailActivity.class) ;
                it.putExtra("title","Bác Sĩ Đa Khoa") ;
                startActivity(it);
            }
        });

        CardView dentist  = findViewById(R.id.cardFindDentist) ;
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity.this, DoctorDetailActivity.class) ;
                it.putExtra("title","Nha Sĩ") ;
                startActivity(it);
            }
        });

        CardView sugion  = findViewById(R.id.cardFindSugion) ;
        sugion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity.this, DoctorDetailActivity.class) ;
                it.putExtra("title","Bác Sĩ Phẫu Thuật") ;
                startActivity(it);
            }
        });

        CardView familymedician  = findViewById(R.id.cardFindCadiologitic) ;
        familymedician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity.this, DoctorDetailActivity.class) ;
                it.putExtra("title","Thần Y") ;
                startActivity(it);
            }
        });
    }
}