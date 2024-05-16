package com.example.btl_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class OrderDetailActivity extends AppCompatActivity {

    private String[][] orderDetails = {} ;

    HashMap<String,String> item  ;
    ArrayList list  ;
    SimpleAdapter sa  ;
    ListView lv ;
    Button btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        lv = findViewById(R.id.OdListView) ;
        btn = findViewById(R.id.ODBack) ;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailActivity.this, HomeActivity.class));
            }
        });

        Database db = new Database(getApplicationContext(),"healthCare",null,1) ;
        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE) ;
        String username = sharedPreferences.getString("username","").toString() ;

        ArrayList dbData = db.getOrderData(username) ;

        orderDetails = new String[dbData.size()][] ;
        for(int i = 0 ; i < orderDetails.length  ;i++){
                orderDetails[i] = new String[5] ;
                String arrData = dbData.get(i).toString() ;
                String[] strData = arrData.split(Pattern.quote("$")) ;
                orderDetails[i][0] = strData[0] ;
                orderDetails[i][1] = strData[1] ;
                if(strData[7].compareTo("Mua Thuốc") == 0){
                    orderDetails[i][3] = "Ngày: " + strData[4] ;
                }
                else {
                    orderDetails[i][3] = "Ngày: " + strData[4] + "    " + "Thời Gian: "+ strData[5];

                }
                orderDetails[i][2]  = "Tổng Tiền: "+ strData[6]   ;
                orderDetails[i][4] = strData[7] ;
        }

        list = new ArrayList() ;
        for(int i = 0 ; i< orderDetails.length ; i++) {
            HashMap<String, String> item = new HashMap<String, String>();
            item.put("line1",orderDetails[i][0]) ;
            item.put("line2","Địa Chỉ: "+ orderDetails[i][1]) ;
            item.put("line3",orderDetails[i][2] + "$") ;
            item.put("line4",orderDetails[i][3]) ;
            item.put("line5",orderDetails[i][4]);
            list.add(item) ;
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_f,R.id.line_e}
        );
        lv.setAdapter(sa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy ra mục được chọn từ danh sách
                HashMap<String, String> selectedItem = (HashMap<String, String>) parent.getItemAtPosition(position);

                // Lấy ra thông tin quan trọng từ mục được chọn
                String type = selectedItem.get("line5");


                ArrayList<String> relatedData = db.getCartData2(username,type);


                // Ví dụ: Hiển thị thông báo
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderDetailActivity.this);
                builder.setTitle("Đơn hàng: " + "'"+type+"'");
                StringBuilder message = new StringBuilder();
                for (String data : relatedData) {
                    message.append(data).append("\n");
                }
                builder.setMessage(message.toString());
                builder.setPositiveButton("OK", null);
                builder.show();
            }
        });
    }

}