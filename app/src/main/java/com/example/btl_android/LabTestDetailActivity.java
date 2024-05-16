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

public class LabTestDetailActivity extends AppCompatActivity {

    TextView tvDTTitle , tvTotal ;
    EditText   edDetails ;
    Button btnAddToCard, btnBack ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_detail);

        tvDTTitle = findViewById(R.id.CartTitle3) ;
        tvTotal = findViewById(R.id.CartTotal) ;
        edDetails  = findViewById(R.id.CartListView) ;
        btnAddToCard = findViewById(R.id.CartCheckOut);
        btnBack = findViewById(R.id.CartBack) ;

        edDetails.setKeyListener(null);

        Intent intent = getIntent() ;
        tvDTTitle.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotal.setText("Thành Tiền: "+ intent.getStringExtra("text3"));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestDetailActivity.this, LabTestActivity.class));
            }
        });
        // them vao gio hang
        btnAddToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("save", Context.MODE_PRIVATE) ;
                String username = sharedPreferences.getString("username","").toString() ;
                Log.d("x",username) ;
                String packet = tvDTTitle.getText().toString() ;
                float price = Float.parseFloat(intent.getStringExtra("text3").toString()) ;

                Database db = new Database(getApplicationContext(), "healthCare", null, 1) ;
                if(db.checkcart(username,packet) == 1){
                    Toast.makeText(getApplicationContext(), "Gói dịch vụ đã được thêm. Hãy thêm gói khác! ", Toast.LENGTH_SHORT).show();
                }else{
                    db.addtoCart(username,packet,price,"Khám bệnh");
                    Toast.makeText(getApplicationContext(), "Gói dịch vụ đã được thêm thành công!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailActivity.this,LabTestActivity.class));
                }

            }
        });
    }
}