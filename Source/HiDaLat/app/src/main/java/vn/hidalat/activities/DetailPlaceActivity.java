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

import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import vn.hidalat.R;

public class DetailPlaceActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "DetailPlaceAct";
    private ImageView mMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_place);
        String linkimage ="";
        String title = "";
        String address = "";
        String description = "";
        String location = "";
        ImageView  tvImage = (ImageView) findViewById(R.id.image);
        Picasso.with(this)
                .load(linkimage)
                .into(tvImage);
        TextView tvTitle = (TextView) findViewById(R.id.title);
        tvTitle.setText(title);
        TextView tvAddress = (TextView) findViewById(R.id.address);
        tvAddress.setText(address);
        TextView tvDescription = (TextView) findViewById(R.id.description);
        tvDescription.setText(description);
        setupToolbar(title);
        setupMaps(location);
        setupAction();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void setupToolbar(String title) {
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
//            ab.setDisplayShowHomeEnabled(true);
            ab.setTitle(title);
        }
    }

    private void setupAction() {
        LinearLayout photo = (LinearLayout) findViewById(R.id.action_photo);
        LinearLayout direct = (LinearLayout) findViewById(R.id.action_direct);
        LinearLayout save = (LinearLayout) findViewById(R.id.action_save);
        LinearLayout share = (LinearLayout) findViewById(R.id.action_share);
        photo.setOnClickListener(this);
        direct.setOnClickListener(this);
        save.setOnClickListener(this);
        share.setOnClickListener(this);
    }

    private void setupMaps(String location) {
        mMaps = (ImageView) findViewById(R.id.maps);
        try {
            location = URLEncoder.encode(location, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, e.toString());
        }
        String path = "https://maps.googleapis.com/maps/api/staticmap?zoom=16&size=500x250&maptype=roadmap";
        path += "&center=" + location;
        path += "&markers=color:orange%7Clabel:S%7C" + location;
        Log.e(TAG, "setupMaps\n" + path);

        Picasso.with(this)
                .load(path)
                .into(mMaps);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_photo:
                doOnPhoToClicked();
                break;
            case R.id.action_direct:
                doOnDirectClicked();
                break;
            case R.id.action_save:
                doOnSaveClicked();
                break;
            case R.id.action_share:
                doOnSharelicked();
                break;
        }
    }

    private void doOnPhoToClicked() {

    }
    private void doOnDirectClicked() {

    }
    private void doOnSaveClicked() {

    }
    private void doOnSharelicked() {

    }
}
