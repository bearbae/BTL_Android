package com.example.btl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookActivity extends AppCompatActivity {

    TextView tv ;
    EditText ed1, ed2, ed3, ed4 ;
    Button datebutton, timebutton, btnBook, btnBack ;
    private DatePickerDialog  datePickerDialog;
    private TimePickerDialog timePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        tv = findViewById(R.id.tvLTB) ;
        ed1 = findViewById(R.id.LTBFullName) ;
        ed2 = findViewById(R.id.LTBDiaChi) ;
        ed3 = findViewById(R.id.LTBSdt) ;
        ed4 = findViewById(R.id.LTBGia) ;
        datebutton= findViewById(R.id.BCartDate)  ;
        timebutton = findViewById(R.id.BCartTime) ;
        btnBook = findViewById(R.id.LTBButton) ;
        btnBack = findViewById(R.id.doctorBack) ;

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it = getIntent() ;
        String title = it.getStringExtra("text1") ;
        String fullname = it.getStringExtra("text2") ;
        String address = it.getStringExtra("text3") ;
        String contact = it.getStringExtra("text4") ;
        String fee = it.getStringExtra("text5") ;

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Giá: "+fee+"$");

        // datePicket
        initDatePicker();
        datebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        // timePicker
        initTimePicker();
        timebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        // quay laij
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookActivity.this,FindDoctorActivity.class));
            }
        });

        // button dat lich
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE) ;
                String username =  sharedPreferences.getString("username","") ;
                Database db = new Database(getApplicationContext(), "healthCare", null, 1) ;
                Log.d("ten",username) ;
                if(db.checkBookExit(username,title+" => "+fullname,address,contact,datebutton.getText().toString(),timebutton.getText().toString(),"Bac si")==1){
                    Toast.makeText(getApplicationContext(),"Bac si da duoc dat",Toast.LENGTH_LONG).show();
                }
//                if(db.checkBookExit(username,"oke","Nam dinh","0123832323","10/20/348","10:20,","Bac si")==1){
//                    Toast.makeText(getApplicationContext(),"Da datj roi",Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(BookActivity.this, HomeActivity.class));
//                }
//                else{
//                                    db.addOrder(username,"tuyetvoi","0-2932","nam dinh",0,"10/20/348","10:20,",20,"Bac si");
//                Toast.makeText(getApplicationContext(),"dajt thanh cong",Toast.LENGTH_LONG).show();
//                }

                else {
                    db.addOrder(username,title+" => "+fullname,address,contact,0,datebutton.getText().toString(),timebutton.getText().toString(),Float.parseFloat(fee),"Bac si");
                    Toast.makeText(getApplicationContext(),"Dat lich hen thanh cong",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(BookActivity.this, HomeActivity.class));
                }
            }

        });

    }


        // set datePicket

        private void initDatePicker(){
            DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()   {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    month = month + 1;
                    datebutton.setText(day+"/"+month+"/"+year);
                }
            };
            Calendar  cal  = Calendar.getInstance() ;
            int year = cal.get(Calendar.YEAR) ;
            int month = cal.get(Calendar.MONTH) ;
            int day = cal.get(Calendar.DAY_OF_MONTH) ;

            // ep kieu
            int style = AlertDialog.THEME_HOLO_DARK ;
            datePickerDialog = new DatePickerDialog(this,style,  dateSetListener, year,month,day);
            datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+ 86400000);

        }

        // timePicker
    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int h, int m) {
                timebutton.setText(h + ": " + m);
            }

        };

        Calendar cal= Calendar.getInstance() ;
        int h = cal.get(Calendar.HOUR) ;
        int m = cal.get(Calendar.MINUTE) ;
        int style = AlertDialog.THEME_HOLO_DARK ;
        timePickerDialog = new TimePickerDialog(this, style, timeSetListener, h, m,true);
    }




}