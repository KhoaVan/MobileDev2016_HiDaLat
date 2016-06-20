package vn.hidalat.fragments.place;


import android.support.v4.app.Fragment;

import java.util.ArrayList;

import vn.hidalat.adapters.PlaceAdapter;
import vn.hidalat.models.Place;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends GeneralPlaceFragment {
    public ShoppingFragment() {
        // Required empty public constructor
    }

    @Override
    protected PlaceAdapter getAdapter() {
        ArrayList<Place> data = new ArrayList<>();
        Place place;
        String link = "http://www.wn.com.vn/product_images/k/314/du-lich-da-lat-tho-cam.jpg_(1)__05739_zoom.jpg";
        String name = "Thổ cẩm làng B'nerC";
        String address = "Huyện Lạc Dương, thành phố Đà Lạt, tỉnh Lâm Đồng";
        String description = "Với phương pháp dệt thô sơ nhưng làng nghề B'nerC luôn cho ra đời những sản phẩm dệt thổ cẩm rất đẹp, tỉ mỉ và tinh tế.";
        for (int i = 0; i < 10; i++) {
            place = new Place(name, address, description, link);
            data.add(place);
        }
        return new PlaceAdapter(getContext(), data, mRecycler);
    }

}
