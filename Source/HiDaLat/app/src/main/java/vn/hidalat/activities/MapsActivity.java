package vn.hidalat.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.hidalat.R;
import vn.hidalat.interfaces.MapsService;
import vn.hidalat.nets.RestService;
import vn.hidalat.utils.maps.MapsHelper;

public class MapsActivity extends AppCompatActivity {
    private static final String TAG = "MapsAct";
    private GoogleMap mMaps;
    private Marker mSrcMarker;
    private Marker mDestMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        double lat = 11.9464458;
        double lng = 108.4531985;
        String text = "Cao đẳng sư phạm Đà Lạt";
        initMaps();
    }

    private void initMaps() {
        if (mMaps == null) {
            // Try to obtain the map from the SupportMapFragment.
            ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.my_maps_act_main))
                    .getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            mMaps = googleMap;
                            Log.e(TAG, "maps inflated");
                            Double srcLat = 11.9464458;
                            Double srcLng = 108.4531985;
                            Double destLat = 11.9468282;
                            Double destLng = 108.45646781;
                            fetchingRoute(srcLat, srcLng, destLat, destLng);

                        }
                    });
        }
    }

    private void fetchingRoute(final double srcLat, final double srcLng, final double destLat, final double destLng) {
        final MapsHelper helper = new MapsHelper();
        String url = helper.buildDirectURL(srcLat, srcLng, destLat, destLng);
        MapsService service = RestService.create("http://maps.googleapis.com/maps/api/", MapsService.class);
        service.fetchingRoute(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String json = response.body().string();
                    Log.e(TAG, json);

                    drawPath(json);
                    setMarker(srcLat, srcLng, "Cao đẳng ĐL", true);
                    setMarker(destLat, destLng, "Bò kho", false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    public boolean setMarker(double lat, double lng, String text, boolean isSrc) {
        Log.e(TAG, "setMarker");
        if (mMaps == null) return false;
        LatLng position = new LatLng(lat, lng);
        mMaps.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 13));
        if (isSrc) {
            if (mSrcMarker != null) {
                mSrcMarker.remove();
            }
            mSrcMarker = mMaps.addMarker(new MarkerOptions().position(position).title(text));// .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_))
        } else {
            if (mDestMarker != null) {
                mDestMarker.remove();
            }
            mDestMarker = mMaps.addMarker(new MarkerOptions().position(position).title(text));// .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_))
        }

        return true;
    }

    public void drawPath(String  result) {
        try {
            //Transform the string into a json object
            final JSONObject json = new JSONObject(result);
            JSONArray routeArray = json.getJSONArray("routes");
            JSONObject routes = routeArray.getJSONObject(0);
            JSONObject overviewPolylines = routes.getJSONObject("overview_polyline");
            String encodedString = overviewPolylines.getString("points");
            Log.e(TAG, encodedString);
            List<LatLng> list = new MapsHelper().decodePoly(encodedString);
            for (int i = 0; i < list.size(); i++) {
                LatLng ll = list.get(i);
                Log.e(TAG, String.valueOf(i) + " " + Double.toString(ll.latitude) + "," + Double.toString(ll.longitude));
            }
            Polyline line = mMaps.addPolyline(new PolylineOptions()
                    .addAll(list)
                    .width(12)
                    .color(Color.parseColor("#05b1fb"))//Google maps blue color
                    .geodesic(true)
            );
            for(int z = 0; z<list.size()-1;z++){
                LatLng src= list.get(z);
                LatLng dest= list.get(z+1);
                Polyline line2 = mMaps.addPolyline(new PolylineOptions()
                        .add(new LatLng(src.latitude, src.longitude), new LatLng(dest.latitude,   dest.longitude))
                        .width(2)
                        .color(Color.BLUE).geodesic(true));
            }
        }
        catch (JSONException e) {
            Log.e(TAG, e.toString());
        }
    }
}
