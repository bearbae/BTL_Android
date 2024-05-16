package com.example.btl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {
    private String[][] packets=
            {
                    {"Gói bệnh : Siêu âm tuyến giáp","","","","500"},
                    {"Gói bệnh : Siêu âm tim","","","","2000"},
                    {"Gói bệnh : Kiểm tra tai, mũi, họng ","","","","3000"},
                    {"Gói bệnh : Kiểm tra cân nặng và huyết áp","","","","1000"},
                    {"Gói bệnh : Kiểm tra tổng quát","","","","4000"},
            };
    private String[] packets_detail=
            {
                    "Phát hiện các bệnh lý của tuyến giáp như:nang tuyến giáp, bướu giáp nhân, ung thư tuyến giáp, viêm tuyến giáp tự miễn cường giáp...",
                    "Phát hiện bệnh lý của tim mạch như:hở van hai lá hẹp hở van ba lá chức năng tim...",
                    "Quá trình kiểm tra sẽ bao gồm việc kiểm tra họng để phát hiện các dấu hiệu của vi khuẩn, vi rút,hoặc vi khuẩn gây viêm họng.Họ cũng có thể sử dụng một cây nạo để lấy mẫu từ họng hoặc mũi để kiểm tra các tác nhân gây bệnh như vi khuẩn streptococcus hay virus cảm lạnh.",
                    "Kiểm tra cân nặng giúp đánh giá xem bạn có đang ở trọng lượng lý tưởng hay không,từ đó đưa ra các khuyến nghị về chế độ ăn uống và lối sống lành mạnh.Ngoài ra, kiểm tra huyết áp quan trọng để đánh giá sức khỏe tim mạch.Áp lực máu cao có thể là dấu hiệu của các vấn đề như tăng huyết áp,đặc biệt là nếu nó được kết hợp với các triệu chứng khác như đau đầu hoặc mệt mỏi. ",
                    "Gói kiểm tra tổng quát là một cách hiệu quả để đánh giá sức khỏe tổng thể và phát hiện sớm các vấn đề y tế tiềm ẩn. Kết quả từ gói kiểm tra này có thể giúp bạn và bác sĩ đưa ra kế hoạch chăm sóc sức khỏe phù hợp và đề xuất các biện pháp cải thiện lối sống để duy trì sức khỏe tốt nhất"
            };

    HashMap<String, String> item ;
    ArrayList list ;
    SimpleAdapter sa ;
    Button btngoToCart, btnBack ;
    ListView listView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);
        btngoToCart  = findViewById(R.id.buttonGoToCard)  ;
        btnBack = findViewById(R.id.LTBack) ;
        listView = findViewById(R.id.listViewLT) ;
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
            }
        });

        // in danh sasch cac goi
        list = new ArrayList() ;
        for(int i = 0 ; i< packets.length ; i++) {
            HashMap<String, String> item = new HashMap<String, String>();
            item.put("line1",packets[i][0]) ;
            item.put("line2",packets[i][1]) ;
            item.put("line3",packets[i][2]) ;
            item.put("line4",packets[i][3]) ;
            item.put("line5", "Tổng Tiền: " + packets[i][4]+"/$");
            list.add(item) ;
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        listView.setAdapter(sa);

        // chon mot goi
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(LabTestActivity.this, LabTestDetailActivity.class) ;
                it.putExtra("text1", packets[i][0]);
                it.putExtra("text2", packets_detail[i]);
                it.putExtra("text3", packets[i][4]);
                startActivity(it);
            }
        });

        // den gio hang
        btngoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this,CardLabActivity.class));
            }
        });
    }
}