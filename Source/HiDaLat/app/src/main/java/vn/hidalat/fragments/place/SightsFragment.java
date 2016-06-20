package vn.hidalat.fragments.place;


import java.util.ArrayList;

import vn.hidalat.adapters.PlaceAdapter;
import vn.hidalat.models.Place;

public class SightsFragment extends GeneralPlaceFragment {
    public SightsFragment() {
        // Required empty public constructor
    }

    @Override
    protected PlaceAdapter getAdapter() {
        ArrayList<Place> data = new ArrayList<>();
        Place place;
        String link = "http://fantasea.vn/sites/default/files/edensee%20lake.jpg";
        String name = "Đà Lạt Edensee Lake Resort & Spa";
        String address = "Hồ Tuyền Lâm Đà Lạt";
        String description = "Đà Lạt Edensee Lake Resort & Spa tọa lạc ở Hồ Tuyền Lâm Đà Lạt sẽ là nơi nghỉ mát lý tưởng cho gia đình bạn với tiêu chuẩn 5 Đà Lạt Edensee Lake Resort & Spa tự sẽ làm hài lòng tất cả các du khách.";
        for (int i = 0; i < 10; i++) {
            place = new Place(name, address, description, link);
            data.add(place);
        }
        return new PlaceAdapter(getContext(), data, mRecycler);
    }

}
