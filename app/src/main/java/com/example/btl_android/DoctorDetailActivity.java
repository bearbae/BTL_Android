package com.example.btl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailActivity extends AppCompatActivity {

    private String[][] doctor_details1={
            {"Tên Bác Sĩ : Nguyễn Mạnh Tuấn", "Bệnh Viện: Hà Nội", "Kinh nghiệm: 5 năm", "SDT: 01232242", "600"},
            {"Tên Bác Sĩ : Đặng Hoài Nam", "Bệnh Viện: Đà Nẵng", "Kinh nghiệm: 4 năm", "SDT: 0232334242", "800"},
            {"Tên Bác Sĩ : Vũ Thị Cẩm Ly", "Bệnh Viện: Phú Thọ", "Kinh nghiệm: 19 năm", "SDT:012872242", "1000"},
            {"Tên Bác Sĩ : Bùi Đức Vinh", "Bệnh Viện: Hà Nam", "Kinh nghiệm: 10 năm", "SDT: 0752232242", "1500"},
            {"Tên Bác Sĩ : Nguyễn Hồng Trà", "Bệnh Viện: Nam Định", "Kinh nghiệm: 23 năm", "SDT: 0873632242", "5000"},
    } ;
    private String[][] doctor_details2={
            {"Tên Bác Sĩ : Vũ Như Ngọc", "Bệnh Viện: Hà Nội", "Kinh nghiệm: 1 năm", "SDT: 098785622", "300"},
            {"Tên Bác Sĩ : Nguyễn Trung Quân", "Bệnh Viện: Hà Nội", "Kinh nghiệm: 2 năm", "SDT: 0674839264", "500"},
            {"Tên Bác Sĩ : Đoàn Thị Hương", "Bệnh Viện: Hà Nội", "Kinh nghiệm: 3 năm", "SDT: 094253768459", "800"},
            {"Tên Bác Sĩ : Đỗ Thành Lợi", "Bệnh Viện: Hà Nội", "Kinh nghiệm: 4 năm", "SDT: 01122334455", "1000"},
            {"Tên Bác Sĩ : PHạm Văn Quân", "Bệnh Viện: Hà Nội", "Kinh nghiệm: 5 năm", "SDT: 098765456", "200"},
    } ;
    private String[][] doctor_details3={
            {"Tên Bác Sĩ : Bùi Hoàng Linh", "Bệnh Viện: Hà Nội", "Kinh nghiệm: 1 năm", "SDT: 0563746868", "600"},
            {"Tên Bác Sĩ : Nguyễn Sơn Tùng", "Bệnh Viện: Hà Nội", "Kinh nghiệm: 5 năm", "SDT: 0274768946", "100"},
            {"Tên Bác Sĩ :  Trần Thái Linh", "Bệnh Viện: Hà Nội", "Kinh nghiệm: 6 năm", "SDT: 0986547865", "1200"},
            {"Tên Bác Sĩ : Vũ Huyền Trang", "Bệnh Viện: Hà Nội", "Kinh nghiệm: 3 năm", "SDT: 0987567584", "2900"},
            {"Tên Bác Sĩ : Đỗ Thị Hoài", "Bệnh Viện: Hà Nội", "Kinh nghiệm: 9 năm", "SDT: 0963452d100", "3700"},
    } ;
    private String[][] doctor_details4={
            {"Tên Bác Sĩ : Vũ Hồng Gấm", "Bệnh Viện: Hà Nội", "Kinh nghiệm: 8 năm", "SDT: 0363537684", "600"},
            {"Tên Bác Sĩ : Phạm Như Quỳnh", "Bệnh Viện: Hà Nội", "Kinh nghiệm: 7 năm", "SDT: 0364532788", "9800"},
            {"Tên Bác Sĩ : Trần Trung Nam", "Bệnh Viện: Hà Nội", "Kinh nghiệm: 2 năm", "SDT: 0986723527", "2300"},
            {"Tên Bác Sĩ : Bùi Hoàng Việt Anh", "Bệnh Viện: Hà Nội", "Kinh nghiệm: 12 năm", "SDT: 0923635267", "10000"},
            {"Tên Bác Sĩ : Nguyễn Trọng Bắc", "Bệnh Viện: Hà Nội", "Kinh nghiệm: 7 năm", "SDT: 02373524378", "780"},
    } ;
    private String[][] doctor_details5={
            {"Tên Bác Sĩ : Nguyễn Trọng Hoàng", "Bệnh Viện: Hà Nội", "Kinh nghiệm: 23 năm", "SDT: 07584635476", "120000"},
            {"Tên Bác Sĩ : Nguyễn Văn Toàn", "Bệnh Viện: Hà Nội", "Kinh nghiệm: 10 năm", "SDT: 09274536487", "1000"},
            {"Tên Bác Sĩ : Đặng Văn Lâm", "Bệnh Viện: Hà Nội", "Kinh nghiệm: 10 năm", "SDT: 01827456378", "10000"},
            {"Tên Bác Sĩ : Nguyễn Quang Hải", "Bệnh Viện: Hà Nội", "Kinh nghiệm: 19 năm", "SDT: 09873654738", "23900"},
            {"Tên Bác Sĩ : Hà Đức Chinh", "Bệnh Viện: Hà Nội", "Kinh nghiệm: 12 năm", "SDT: 01937564738", "28335"},
    } ;
    TextView tv ;
    Button btn ;
    String[][] doctor_details = {} ;
    ArrayList list ;
    SimpleAdapter sa ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        tv = findViewById(R.id.tvDDTitle2) ;
        btn = findViewById(R.id.buttonDDBack) ;

        // lay ten title
        Intent it = getIntent() ;
        String title  = it.getStringExtra("title") ;
        tv.setText(title);
        if(title.compareTo("Bác Sĩ Gia Đình") == 0){
            doctor_details =doctor_details1 ;
        }
        else
        if(title.compareTo("Bác Sĩ Đa Khoa") == 0){
            doctor_details =doctor_details2 ;
        }
        else
        if(title.compareTo("Nha Sĩ") == 0){
            doctor_details =doctor_details3 ;
        }
        else
        if(title.compareTo("Bác Sĩ Phẫu Thuật") == 0) {
            doctor_details = doctor_details4;
        }
        else
        {
            doctor_details =doctor_details5 ;
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailActivity.this, FindDoctorActivity.class));
            }
        });

        // in danh sach list view
        list = new ArrayList() ;
        for(int i = 0 ; i< doctor_details.length ; i++) {
            HashMap<String, String> item = new HashMap<String, String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Giá: " + doctor_details[i][4] + "/$");
            list.add(item);
        }
            sa = new SimpleAdapter(this, list,
                    R.layout.multi_lines,
                    new String[]{"line1", "line2", "line3", "line4", "line5"},
                    new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
            );
            ListView lv = findViewById(R.id.listviewDD);
            lv.setAdapter(sa);

            // đặt lịch hẹn
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                    Intent it = new Intent(DoctorDetailActivity.this, BookActivity.class);
                    it.putExtra("text1", title);
                    it.putExtra("text2", doctor_details[i][0]);
                    it.putExtra("text3", doctor_details[i][1]);
                    it.putExtra("text4", doctor_details[i][3]);
                    it.putExtra("text5", doctor_details[i][4]);
                    startActivity(it);

                }
            });
    }
}