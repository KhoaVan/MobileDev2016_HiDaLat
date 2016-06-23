package vn.hidalat.fragments.place;


import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.ArrayList;

import vn.hidalat.interfaces.ServiceListener;
import vn.hidalat.interfaces.TravelService;
import vn.hidalat.models.Place;
import vn.hidalat.nets.RestService;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopRatedFragment extends GeneralPlaceFragment {
    private static final String TAG = "TopRatedFragment";

    public TopRatedFragment() {
        // Required empty public constructor
    }

    @Override
    protected void reqData(int page, final ServiceListener onResponse) {
        Log.e(TAG, "reqData");
        Log.e(TAG, "getPlacesByType");
        TravelService service = RestService.create(TravelService.class);

    }

    private ArrayList<Place> initSampleData() {
        ArrayList<Place> data = new ArrayList<>();
        String name = "Cao đẳng sư phạm";
        String address = "29 Yersin, tp. Đà Lạt, Lâm Đồng, Việt Nam";
        String link = "http://seablogs.zenfs.com/u/jIGqcwqAHxoPyp4FU4djj22y/photo/ap_20110504123928120.jpg";
        String description = "Trường Cao đẳng Sư phạm Đà Lạt là một trường cao đẳng được thành lập ngày 3 tháng 9 năm 1976 theo quyết định số 1784/QĐ của Bộ Giáo dục, trụ sở đặt tại thành phố Đà Lạt, tỉnh Lâm Đồng.";

        Place t;
        for (int i = 0; i < 10; i++) {
            t = new Place(name, address, description, link);
            data.add(t);
        }
        return data;
    }
}
