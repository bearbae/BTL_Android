package com.example.btl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Pattern;

public class CartBuyMedicineActivity extends AppCompatActivity {
    HashMap<String,String> item ;
    ArrayList list;
    SimpleAdapter sa ;
    TextView tvTotal ;
    ListView lv  ;
    private DatePickerDialog datePickerDialog ;
    private Button dateButton, btnCheckout, btnBack ;
    private String[][] packages={} ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_buy_medicine);

        dateButton = findViewById(R.id.CartMedicineDate) ;
        btnCheckout = findViewById(R.id.CartMedicineCheckOut) ;
        btnBack = findViewById(R.id.CartMedicineBack) ;
        tvTotal = findViewById(R.id.CartMedicineTotal) ;
        lv = findViewById(R.id.CartMedicineListView) ;
        // hien thi ra gio hang
        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE) ;
        String username =  sharedPreferences.getString("username","") ;
        Database db = new Database(getApplicationContext(), "healthCare", null, 1) ;


        float Total = 0 ;
        ArrayList dbData  = db.getCartData(username,"Mua thuốc") ;
        packages = new String[dbData.size()][] ;
        for(int i = 0 ; i< packages.length ; i++){
            packages[i] = new String[5] ;
        }
        for(int i = 0 ; i < dbData.size();i++){
            String arrData  = dbData.get(i).toString() ;
//            Toast.makeText(getApplicationContext(),arrData,Toast.LENGTH_LONG).show();
            String[] strData = arrData.split(Pattern.quote("$")) ;
            packages[i][0] = strData[0] ;
            packages[i][4] = "Giá : " + strData[1]  ;
            Total = Total + Float.parseFloat(strData[1]) ;
        }
        tvTotal.setText("Thành Tiền: "+ Total);

        // hien thi thong tin item trong gio hang
        list = new ArrayList() ;
        for(int i = 0 ; i < packages.length; i++){
            item = new HashMap<String, String>() ;
            item.put("line1",packages[i][0]) ;
            item.put("line2",packages[i][1]) ;
            item.put("line3",packages[i][2]) ;
            item.put("line4",packages[i][3]) ;
            item.put("line5",packages[i][4]) ;
            list.add(item) ;
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        lv.setAdapter(sa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Xác định mục đã chọn
                HashMap<String, String> selectedItem = (HashMap<String, String>) parent.getItemAtPosition(position);
                // Xóa mục đã chọn khỏi danh sách dữ liệu
                list.remove(position);
                float newTotal = calculateTotal();
                // Cập nhật tổng số tiền mới vào TextView
                tvTotal.setText("Thành Tiền: " + newTotal);
                // Cập nhật giao diện
                sa.notifyDataSetChanged();
                String packet = selectedItem.get("line1");

                int isExist = db.checkcart(username, packet);
                if (isExist == 1) {
                    db.deleteCartItem(username, packet);
                    Toast.makeText(getApplicationContext(),"Xoa thanh cong",Toast.LENGTH_LONG).show();
                } else {

                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartBuyMedicineActivity.this, BuyMedicineActivity.class));
            }
        });

        // lưu gia tri da chon trong Intent
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(CartBuyMedicineActivity.this, BuyMedicineBookActivity.class) ;
                it.putExtra("price",tvTotal.getText()) ;
                it.putExtra("date",dateButton.getText()) ;
                startActivity(it);
            }
        });
        // datePicket
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

    }

    // set datePicket

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()   {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                dateButton.setText(day+"/"+month+"/"+year);
            }
        };
        Calendar cal  = Calendar.getInstance() ;
        int year = cal.get(Calendar.YEAR) ;
        int month = cal.get(Calendar.MONTH) ;
        int day = cal.get(Calendar.DAY_OF_MONTH) ;

        // ep kieu
        int style = AlertDialog.THEME_HOLO_DARK ;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+ 86400000);
    }

    private float calculateTotal() {
        float x = 0;
        for (int i = 0; i < list.size(); i++) {
            HashMap<String, String> item = (HashMap<String, String>) list.get(i);
            String priceString = item.get("line5");
            // Loại bỏ chuỗi "Giá : " để lấy giá trị số
            String priceWithoutPrefix = priceString.replace("Giá : ", "");
            float price = Float.parseFloat(priceWithoutPrefix);
            x += price;
        }
        return x;
    }


}