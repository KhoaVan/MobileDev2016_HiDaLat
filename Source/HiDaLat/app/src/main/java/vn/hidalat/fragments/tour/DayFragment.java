package vn.hidalat.fragments.tour;

/**
 * Created by j3ao on 6/20/2016.
 */

import java.util.ArrayList;

import vn.hidalat.adapters.TourAdapter;
import vn.hidalat.models.Tour;

public class DayFragment extends GeneralTourFragment{
    public DayFragment() {
        // Required empty public constructor
    }

    @Override
    protected TourAdapter getAdapter() {
        ArrayList<Tour> data = initSampleData();

        return new TourAdapter(getContext(), data, mRecycler);
    }

    private ArrayList<Tour> initSampleData() {
        ArrayList<Tour> data = new ArrayList<>();
        String name = "Đà Lạt Ngàn Hoa";
        String address = "Đà lạt";
        String time = "KH : Trong Ngày | Nơi khởi hành : Đà Lạt ";
        String price = "195000 VND";
        String link = "https://www.dropbox.com/home?preview=dlnh.jpg";
        Tour t;
        for (int i = 0; i < 10; i++) {
            t = new Tour(name, address, time, price, link);
            data.add(t);
        }
        return data;
    }

}
