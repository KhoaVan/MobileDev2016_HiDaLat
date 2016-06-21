package vn.hidalat.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.hidalat.R;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import vn.hidalat.R;

public class DetailTourActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "DetailTourAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tour);
        setTour();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void setTour()
    {

        String name = "Tour Đà Lạt 1N: Khám Phá Đà Lạt Ngàn Hoa";
        String intro = "Nơi khởi hành: Đà Lạt | Trong Ngày | Phương tiện : 16 chỗ";
        String price = "195000 VND";
        String schedule = "Sáng 8h20’ – 8h40’ xe và hướng dẫn viên sẽ đón quý khách tại khách sạn quý khách lưu trú, bắt đầu khởi hành chương trình tham quan." + "\n" +

        "8h45’: Dinh Bảo Đại - Ngôi dinh thự cổ được chính vua Bảo Đại – vị vua cuối cùng của Việt Nam xây dựng để nghỉ ngơi và làm việc tại Đà Lạt, được nhà nước xếp hạng di tích quốc gia." + "\n"+

        "8h45’: Cáp treo và Đồi vọng cảnh - Ngọn đồi cao 1500m so với mực nước biển để quý khách có thể ngắm toàn cảnh thành phố Đà Lạt. Có hệ thống cáp treo dài 2300m đưa quý khách thăm ngôi chùa lớn nhất Đà Lạt ( Chi phí cáp treo tự túc, nếu không muốn đi cáp treo quý khách có thể đi bằng xe tour)." + "\n"+

        "9h10’: Thiền viện Trúc Lâm – hồ Tuyền Lâm - Ngôi chùa lớn xây dựng năm 1993 theo Thiền Tông, rộng 24 hecta, cảnh quan đẹp nhìn xuống khu du lịch hồ Tuyền Lâm.11h00’: Thác Datanla - Cảnh thác nước hung vĩ giữa núi rừng Cao nguyên, hiện nay đây là con thác đẹp và sạch nhất Đà Lạt. Quý khách còn có thể thưởng thức cảm giác mạnh bằng hệ thống xe trượt ống hiện đại tại đây.(Chi phí máng trượt tự túc)." + "\n"+

        "12h00’: Về lại thành phố, ghé nhà hàng - Ăn trưa ( chi phí tự túc, có thể nhờ công ty chúng tôi đặt giúp với chi phí 100.000vnđ/phần trở lên)." + "\n" +

        "13h20’: Thung lũng tình yêu - Cảnh quan thơ mộng, lãng mạn làm đắm say biết bao người khi tới đây. Quý khách có thể thả bộ dọc theo những con đường mòn quanh co hay thuê xe Jeep dạo quanh thung lũng." + "\n" +

        "14h30’: Showroom hoa nghệ thuật - Tham quan hàng trăm loài hoa của Đà Lạt tươi và sấy khô. Đặc biệt tại đây quý khách sẽ được thấy những bức tranh kết bằng hoa khô nhưng vẫn giữ nguyên màu sắc và hình dáng. Có thể mua về sử dụng được tối thiểu 5 năm trong nhà.15h00’: Nhà thờ Domain De Marie - Ngôi nhà thờ được xây dựng từ những năm 1938 với kiến trúc đẹp, được phu nhân quan toàn quyền Đông Dương Jean Decoux ủng hộ nhiều tâm huyết, đây cũng là nơi lưu giữ phần mộ của bà." + "\n"+

        "16h00’: Về lại khách sạn - Chia tay quý khách, kết thúc chương trình tour.";
        TextView tvTourName = (TextView) findViewById(R.id.tour_name);
        tvTourName.setText(name);
        TextView tvTourIntro = (TextView) findViewById(R.id.tour_intro);
        tvTourIntro.setText(intro);
        TextView tvPrice = (TextView) findViewById(R.id.price);
        tvPrice.setText(price);
        TextView tvContent = (TextView) findViewById(R.id.content);
        tvContent.setText(schedule);
        setupToolbar(name);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
            finish();
            return true;

    }
    private void setupToolbar(String title) {
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
//            ab.setDisplayShowHomeEnabled(true);
            ab.setTitle(title);
        }
    }


    @Override
    public void onClick(View v) {

    }

}

