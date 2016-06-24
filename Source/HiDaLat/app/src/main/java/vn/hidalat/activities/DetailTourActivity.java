package vn.hidalat.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import vn.hidalat.R;
import vn.hidalat.fragments.news.NewsFragment;
import vn.hidalat.fragments.tour.GeneralTourFragment;
import vn.hidalat.models.News;
import vn.hidalat.models.Tour;

public class DetailTourActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "DetailTourAct";
    private TextView mName;
    private TextView mCompany;
    private ImageView mImgCover;
    private TextView mPrice;
    private TextView mPhone;
    private TextView mEmail;
    private TextView mDuration;
    private TextView mStartdate;
    private TextView mContent;

    // Thumbnail list
    private ImageView mImg1;
    private ImageView mImg2;
    private ImageView mImg3;
    private ImageView mImg4;
    private ImageView mImg5;
    private ImageView mImg6;
    // private TextView mTvPlaceAddress;
    private TextView mTvNewsDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tour);

        Tour p = getIntent().getParcelableExtra(GeneralTourFragment.P_TOUR);
        setupView(p);

        setupToolbar(p);

    }
    private void setupView(Tour p) {
        mName = (TextView) findViewById(R.id.tour_name);
        mImgCover = (ImageView) findViewById(R.id.image);
        mCompany = (TextView) findViewById(R.id.tour_name);
        mPrice = (TextView) findViewById(R.id.price);
        mPhone = (TextView) findViewById(R.id.tour_name);
        mEmail = (TextView) findViewById(R.id.tour_name);
        mStartdate = (TextView) findViewById(R.id.date);
        mDuration = (TextView) findViewById(R.id.tour_intro);
        mContent = (TextView) findViewById(R.id.content);


        // Update data
        if (p != null) {
            Picasso.with(this)
                    .load(p.getThumbnail())
                    .placeholder(R.drawable.placeholder)
                    .into(mImgCover);
            mName.setText(p.getName());
            mCompany.setText(p.getCompany());
            mPrice.setText(p.getPrice());
            mPhone.setText(p.getTelephone());
            mEmail.setText(p.getEmail());
            mStartdate.setText(p.getStartdate());
            mContent.setText(p.getContent());
        }
    }

    private void setupToolbar(Tour p) {
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle((p == null) && (p.getName() != null) ? "Chi tiết tin tức" : p.getName());
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // if (id == android.R.id.home) {
        finish();
        return true;
        //  }

        //return super.onOptionsItemSelected(item);
    }
    private void setupToolbar(String name) {
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
//            ab.setDisplayShowHomeEnabled(true);
            ab.setTitle(name);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
