package com.example.btl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {
    private String[][] medicines=
            {
                    {"Thuốc hạ sốt, kháng viêm","","","","500"},
                    {"Thuốc da liễu","","","","2000"},
                    {"Thuốc sát trùng","","","","1000"},
                    {"Thuốc giảm đau","","","","3000"},
                    {"Nước muối sinh lý","","","","4000"},
            };
    private String[] medicines_detail=
            {
                    "Các loại thuốc hạ sốt và kháng viêm thường dùng là Paracetamol (Tylenol, Hapacol, Efferalgant...) hay Ibuprofen, Mobic,…Khi sử dụng thuốc hạ sốt, cần lưu ý về liều dùng vì dùng quá liều có thể gây ngộ độc, đồng thời không nên sử dụng nhiều loại thuốc hạ sốt trong một đợt sốt.",
                    "Đối với loại thuốc này, có thể dự trữ Pantenol dạng kem bôi hoặc dạng xịt là một trong những loại thuốc da liễu trị bỏng hữu hiệu. Dùng Pantenol ngay sau khi bị bỏng nhẹ giúp vết bỏng không bị phồng rộp.",
                    "Betadine: dùng để sát trùng ngoài da đối với tổn thương trên da như xây xước nhẹ hoặc có chảy máu. Thuốc có tác dụng chống nhiễm trùng. Ngoài ra, Betadine dùng cho sát trùng tổn thương ở niêm mạc môi và miệng.",
                    "Cũng là Paracetamol hay các thuốc Alexan,…Các gel bôi như Salonpas, các miếng dán giảm đau. Túi chườm nóng, lạnh: giúp làm giảm cơn đau bụng, giảm sưng, hạ sốt.",
                    "Vệ sinh mắt, mũi hàng ngày sau khi đi đường có nhiều bụi bẩn hoặc vào thời điểm có nhiều dịch bệnh.Rửa mắt khi bị dị vật (hạt bụi, côn trùng, cát...) bay vào mắt. Tác dụng của nước muối sinh lý trong trường hợp này là để đẩy dị vật ra khỏi mắt."
            };

    HashMap<String, String> item ;
    ArrayList list ;
    SimpleAdapter sa ;
    Button btngoToCart, btnBack ;
    ListView lv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);
        btngoToCart = findViewById(R.id.BMAdd) ;
        btnBack = findViewById(R.id.BMBack) ;
        lv = findViewById(R.id.BMListView);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class));
            }
        });

        // in danh sasch cac loai thuoc
        list = new ArrayList() ;
        for(int i = 0 ; i< medicines.length ; i++) {
            HashMap<String, String> item = new HashMap<String, String>();
            item.put("line1",medicines[i][0]) ;
            item.put("line2",medicines[i][1]) ;
            item.put("line3",medicines[i][2]) ;
            item.put("line4",medicines[i][3]) ;
            item.put("line5", "Tổng Tiền: " + medicines[i][4]+"/$");
            list.add(item) ;
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        lv.setAdapter(sa);

        // chon mot loai thuoc
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(BuyMedicineActivity.this, MedicineDetailActivity.class) ;
                it.putExtra("text1", medicines[i][0]);
                it.putExtra("text2", medicines_detail[i]);
                it.putExtra("text3", medicines[i][4]);
                startActivity(it);
            }
        });

        // den gio hang
        btngoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
            }
        });
    }
}