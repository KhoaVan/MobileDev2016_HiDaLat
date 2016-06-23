package vn.hidalat.utils.maps;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khoavankas on 20/06/2016.
 */
public class MapsHelper {
    private static final String TAG = "MapsDirectHelper";

    public String buildDirectURL(double sourcelat, double sourcelog, double destlat, double destlog ){
        StringBuilder url = new StringBuilder();
        url.append("http://maps.googleapis.com/maps/api/directions/json")
        .append("?origin=")// from
        .append(Double.toString(sourcelat))
        .append(",")
        .append(Double.toString(sourcelog))
        .append("&destination=")// to
        .append(Double.toString(destlat))
        .append(",")
        .append(Double.toString(destlog))
        .append("&sensor=false&mode=driving&alternatives=true");
        return url.toString();
    }

    public List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng( (((double) lat / 1E5)),
                    (((double) lng / 1E5) ));
            poly.add(p);
        }

        return poly;
    }

    // --------------
    public String buildStaticMapUrl(String lat, String lng){
        String url = "https://maps.googleapis.com/maps/api/staticmap?zoom=16&size=500x250&maptype=roadmap";
        String location = lat.trim() + "," + lng.trim();
        url += "&center=" + location;
        url += "&markers=color:orange%7Clabel:S%7C" + location;
        Log.e(TAG, "buildStaticMapUrl\n" + url);
        return url;
    }
}
