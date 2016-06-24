package vn.hidalat.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.hidalat.R;
import vn.hidalat.fragments.news.NewsFragment;
import vn.hidalat.fragments.tour.GeneralTourFragment;
import vn.hidalat.models.News;
import vn.hidalat.models.Place;
import vn.hidalat.models.Tour;
import vn.hidalat.utils.constant.Const;

public class DetailTourActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "DetailTourAct";
    private TextView mTvName;
    private TextView mTvCompany;
    private ImageView mImgCover;
    private TextView mTvPrice;
    private TextView mTvPhone;
    private TextView mTvEmail;
    private TextView mTvDuration;
    private TextView mTvStartdate;
    private TextView mTvContent;


    // Thumbnail list
    private ImageView mImg1;
    private ImageView mImg2;
    private ImageView mImg3;
    private ImageView mImg4;
    private ImageView mImg5;
    private ImageView mImg6;
    private TextView mSeeMore;
    // private TextView mTvPlaceAddress;
   // private TextView mTvNewsDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tour);

        Tour p = getIntent().getParcelableExtra(GeneralTourFragment.P_TOUR);
        setupView(p);
        setupThumbnailList(p);
        setupToolbar(p);

    }

    private void setupThumbnailList(Tour p) {
        if (p != null) {
            ArrayList<String> thumbnails = p.getImageList();
            if (thumbnails != null) {
                ArrayList<ImageView> imgs = new ArrayList<>();
                imgs.add(mImg1);
                imgs.add(mImg2);
                imgs.add(mImg3);
                imgs.add(mImg4);
                imgs.add(mImg5);
                imgs.add(mImg6);
                int i;
                for (i = 0; i < thumbnails.size() && i < Const.MAX_PLACE_THUMBNAILS; i++) {
                    Picasso.with(this)
                            .load(thumbnails.get(i))
                            .placeholder(R.drawable.placeholder)
                            .into(imgs.get(i));
                }

                // See more visibility
                if (i == Const.MAX_PLACE_THUMBNAILS)
                    mSeeMore.setVisibility(View.GONE);
            }
        }
    }
    private void setupView(Tour p) {
        mTvName = (TextView) findViewById(R.id.tour_name);
        mImgCover = (ImageView) findViewById(R.id.image);
        mTvDuration = (TextView) findViewById(R.id.duration);
        mTvStartdate = (TextView) findViewById(R.id.start_date);
        mTvPrice = (TextView) findViewById(R.id.price);
        mTvContent = (TextView) findViewById(R.id.content);
        mTvCompany = (TextView) findViewById(R.id.company);
        mTvPhone = (TextView) findViewById(R.id.phone);
        mTvEmail = (TextView) findViewById(R.id.email);

        mImg1 = (ImageView) findViewById(R.id.img1);
        mImg2 = (ImageView) findViewById(R.id.img2);
        mImg3 = (ImageView) findViewById(R.id.img3);
        mImg4 = (ImageView) findViewById(R.id.img4);
        mImg5 = (ImageView) findViewById(R.id.img5);
        mImg6 = (ImageView) findViewById(R.id.img6);
        mSeeMore = (TextView) findViewById(R.id.see_more);
        // Update data
        if (p != null) {
            Picasso.with(this)
                    .load(p.getThumbnail())
                    .placeholder(R.drawable.placeholder)
                    .into(mImgCover);
            mTvName.setText(p.getName());
            mTvDuration.setText(p.getDuration());
            mTvStartdate.setText(p.getStartdate());
            mTvPrice.setText(p.getPrice());
            mTvCompany.setText(p.getCompany());
            mTvPhone.setText(p.getTelephone());
            mTvEmail.setText(p.getEmail());
            mTvContent.setText(p.getContent());
            mSeeMore = (TextView) findViewById(R.id.see_more);
        }
        // See more clicked
        mSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailTourActivity.this, "See more image", Toast.LENGTH_SHORT).show();
            }
        });
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
