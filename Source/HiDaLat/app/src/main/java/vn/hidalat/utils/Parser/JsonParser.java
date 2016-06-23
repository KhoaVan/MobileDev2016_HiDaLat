package vn.hidalat.utils.Parser;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import vn.hidalat.models.OpenTime;
import vn.hidalat.models.Place;
import vn.hidalat.models.post.LatLng;

/**
 * Created by khoavankas on 23/06/2016.
 */
public class JsonParser {
    private static final String TAG = "JsonParser";

    public ArrayList<Place> parsePlaces(String json){
        ArrayList<Place> data = new ArrayList<>();
        JSONArray arr = null;
        try {
            arr = new JSONArray(json);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                String id = obj.getString("_id");
                String name = obj.getString("name");
                String address = obj.getString("address");
                String type = obj.getString("type");
                String phone = obj.getString("telephone");
                String thumbnail = obj.getString("coverImage");
                String description = obj.getString("description");
                String summary = obj.getString("summary");
                String relatedTours = obj.getString("relatedTour");

                JSONArray imageList = obj.getJSONArray("imageList");
                JSONArray extensiveInfo = obj.getJSONArray("extensiveInfo");
                JSONObject openHour = obj.getJSONObject("openHour");
                JSONObject geo = obj.getJSONObject("geo");
                // Parse array
                ArrayList<String> imgs = new ArrayList<String>();
                for (int j = 0; j < imageList.length(); j++) {
                    String url = ((JSONObject) imageList.get(j)).getString("url");
                    imgs.add(url);
                }

                HashMap<String, String> extendInfo = new HashMap<String, String>();
                for (int i2 = 0; i2 < extensiveInfo.length(); i2++) {
                    JSONObject iInfo = (JSONObject) extensiveInfo.get(i2);
                    String iName = iInfo.getString("name");
                    String iContent = iInfo.getString("content");
                    extendInfo.put(iName, iContent);
                }

                OpenTime openTime = new OpenTime(openHour.getString("begin"), openHour.getString("end"));
                LatLng latLng = new LatLng(geo.getString("lat"), geo.getString("long"));
                Place place = new Place(id, name, address, description, thumbnail,
                        type, phone, summary, relatedTours, imgs,
                        extendInfo, openTime, latLng);
                data.add(place);
            }
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }

        return data;
    }
}
