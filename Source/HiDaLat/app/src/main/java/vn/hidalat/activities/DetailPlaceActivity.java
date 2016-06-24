package vn.hidalat.activities;


import android.content.Intent;
import android.net.Uri;
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

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import vn.hidalat.R;
import vn.hidalat.fragments.place.GeneralPlaceFragment;
import vn.hidalat.interfaces.ServiceListener;
import vn.hidalat.interfaces.TravelService;
import vn.hidalat.models.Place;
import vn.hidalat.models.post.LatLng;
import vn.hidalat.nets.RestService;
import vn.hidalat.utils.Converter;
import vn.hidalat.utils.constant.Const;
import vn.hidalat.utils.maps.MapsHelper;

public class DetailPlaceActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "DetailPlaceAct";
    private ImageView mImgCover;
    private ImageView mMaps;
    private TextView mTvPlaceName;
    private TextView mTvPlaceAddress;
    private TextView mTvPlaceDescription;
    // Thumbnail list
    private ImageView mImg1;
    private ImageView mImg2;
    private ImageView mImg3;
    private ImageView mImg4;
    private ImageView mImg5;
    private ImageView mImg6;
    private TextView mSeeMore;
    // Place type
    private TextView mTvPlaceType;

    //place object
    Place currentPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_place);

        Place p = getIntent().getParcelableExtra(GeneralPlaceFragment.P_PLACE);
        currentPlace = p;
        setupView(p);

        setupToolbar(p);
        setupMaps(p);
        setupAction();
        // Thumbnail list
        setupThumbnailList(p);
        // Request data
        setupRelatedTour(p);

    }

    private void setupRelatedTour(Place p) {
        // Start request to server
        ServiceListener relatedTourListener = new ServiceListener() {
            @Override
            public void onSuccess(Object data, int error, String msg) {
                Log.e(TAG, "onSuccess");
            }

            @Override
            public void onFailure(Object data, int error, String msg) {
                Log.e(TAG, "onFailure\n" + msg.toString());
            }
        };

        if (p != null) {
            String tourId = p.getRelatedTour();
            TravelService service = RestService.create(TravelService.class);
            // ---

        }
    }

    private void setupThumbnailList(Place p) {
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

    private void setupView(Place p) {
        mTvPlaceType = (TextView) findViewById(R.id.place_type);
        mImgCover = (ImageView) findViewById(R.id.place_image);
        mTvPlaceName = (TextView) findViewById(R.id.place_name);
        mTvPlaceAddress = (TextView) findViewById(R.id.place_address);
        mTvPlaceDescription = (TextView) findViewById(R.id.place_content);
        // Image list
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
            mTvPlaceType.setText(new Converter().placeTypeToString(this, p.getType()));
            mTvPlaceName.setText(p.getName());
            mTvPlaceAddress.setText(p.getAddress());
            mTvPlaceDescription.setText(p.getDescription());
        }
        // See more clicked
        mSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailPlaceActivity.this, "See more image", Toast.LENGTH_SHORT).show();
            }
        });
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
    private void setupToolbar(Place p) {
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(p.getName());
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

    private void setupMaps(Place p) {
        if (p != null) {
            mMaps = (ImageView) findViewById(R.id.place_maps);
            LatLng latLng = p.getLatLng();
            String url = new MapsHelper().buildStaticMapUrl(latLng.getLat(), latLng.getLng());
            Picasso.with(this)
                    .load(url)
                    .placeholder(R.drawable.placeholder)
                    .into(mMaps);
        }
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
        startActivity(new Intent(this, MapsActivity.class));
    }
    private void doOnSaveClicked() {

    }
    private void doOnSharelicked() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        Uri screenshotUri = Uri.parse(currentPlace.getThumbnail());

        try {
            InputStream stream = getContentResolver().openInputStream(screenshotUri);
        }

        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        sharingIntent.setType("image/jpeg");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Chia sẻ từ ứng dụng HiDaLat");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "Thật tuyệt vời khi đến nơi đây ! " + "\n\n" + mTvPlaceName.getText());
        sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
        startActivity(Intent.createChooser(sharingIntent, "Lựa chọn nơi chia sẻ địa điểm "));

    }
}