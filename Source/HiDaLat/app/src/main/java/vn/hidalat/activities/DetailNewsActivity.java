package vn.hidalat.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import vn.hidalat.R;
import vn.hidalat.fragments.news.NewsFragment;
import vn.hidalat.fragments.place.GeneralPlaceFragment;
import vn.hidalat.models.News;
import vn.hidalat.models.Place;
import vn.hidalat.utils.Converter;

public class DetailNewsActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "DetailNewsAct";
    private ImageView mImgCover;
    private TextView mTvNewsTitle;
    private TextView mTvNewsDate;
    private TextView mTvNewsAuthor;
   // private TextView mTvPlaceAddress;
    private TextView mTvNewsDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        News p = getIntent().getParcelableExtra(NewsFragment.P_NEWS);
        setupView(p);

        setupToolbar(p);

    }
    private void setupView(News p) {
        mTvNewsTitle = (TextView) findViewById(R.id.title);
        mImgCover = (ImageView) findViewById(R.id.image);
        mTvNewsDate = (TextView) findViewById(R.id.date);
        mTvNewsAuthor = (TextView) findViewById(R.id.name);
        mTvNewsDescription = (TextView) findViewById(R.id.content);


        // Update data
        if (p != null) {
            Picasso.with(this)
                    .load(p.getThumbnail())
                    .placeholder(R.drawable.placeholder)
                    .into(mImgCover);
            mTvNewsTitle.setText(p.getTitle());
            mTvNewsDate.setText(p.getDate());
            mTvNewsAuthor.setText(p.getAuthor());
            mTvNewsDescription.setText(p.getContent());
        }
    }

    private void setupToolbar(News p) {
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle((p == null) && (p.getTitle() != null) ? "Chi tiết tin tức" : p.getTitle());
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
