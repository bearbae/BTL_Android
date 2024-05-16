package com.example.btl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class MedicineDetailActivity extends AppCompatActivity {
    TextView tvMdTitle , tvMdTotal ;
    EditText edMdDetails ;
    Button btnMdAdd, btnMdBack ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_detail);
        tvMdTitle = findViewById(R.id.MDTitle3) ;
        tvMdTotal  =findViewById(R.id.MDTotal) ;
        edMdDetails = findViewById(R.id.MDListView) ;
        btnMdBack  = findViewById(R.id.MDBack) ;
        btnMdAdd = findViewById(R.id.MdAdd) ;

        edMdDetails.setKeyListener(null);

        Intent intent = getIntent() ;
        tvMdTitle.setText(intent.getStringExtra("text1"));
        edMdDetails.setText(intent.getStringExtra("text2"));
        tvMdTotal.setText("Thành Tiền: "+ intent.getStringExtra("text3"));

        btnMdBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MedicineDetailActivity.this, BuyMedicineActivity.class));
            }
        });
        // them vao gio hang
        btnMdAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("save", Context.MODE_PRIVATE) ;
                String username = sharedPreferences.getString("username","").toString() ;
                Log.d("x",username) ;
                String medicine = tvMdTitle.getText().toString() ;
                float price = Float.parseFloat(intent.getStringExtra("text3").toString()) ;

                Database db = new Database(getApplicationContext(), "healthCare", null, 1) ;
                if(db.checkcart(username,medicine) == 1){
                    Toast.makeText(getApplicationContext(), "Loại thuốc này đã được thêm. Hãy thêm gói khác! ", Toast.LENGTH_SHORT).show();
                }else{
                    db.addtoCart(username,medicine,price,"Mua thuốc");
                    Toast.makeText(getApplicationContext(), "Mua thuốc thành công!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MedicineDetailActivity.this, BuyMedicineActivity.class));
                }

            }
        });

    }
}