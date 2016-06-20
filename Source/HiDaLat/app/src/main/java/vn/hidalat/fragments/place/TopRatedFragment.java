package vn.hidalat.fragments.place;


import android.support.v4.app.Fragment;

import java.util.ArrayList;

import vn.hidalat.adapters.PlaceAdapter;
import vn.hidalat.models.Place;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopRatedFragment extends GeneralPlaceFragment {
    public TopRatedFragment() {
        // Required empty public constructor
    }

    @Override
    protected PlaceAdapter getAdapter() {
        ArrayList<Place> data = new ArrayList<>();
        Place place;
        String name = "Cao đẳng sư phạm";
        String address = "29 Yersin, tp. Đà Lạt, Lâm Đồng, Việt Nam";
        String link = "http://seablogs.zenfs.com/u/jIGqcwqAHxoPyp4FU4djj22y/photo/ap_20110504123928120.jpg";
        String description = "Trường Cao đẳng Sư phạm Đà Lạt là một trường cao đẳng được thành lập ngày 3 tháng 9 năm 1976 theo quyết định số 1784/QĐ của Bộ Giáo dục, trụ sở đặt tại thành phố Đà Lạt, tỉnh Lâm Đồng.";
        for (int i = 0; i < 10; i++) {
            place = new Place(name, address, description, link);
            data.add(place);
        }
        return new PlaceAdapter(getContext(), data, mRecycler);
    }

}
