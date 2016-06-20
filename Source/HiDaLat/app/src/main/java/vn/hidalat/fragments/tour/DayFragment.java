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
        String name = "Cao đẳng sư phạm";
        String address = "29 Yersin, tp. Đà Lạt, Lâm Đồng, Việt Nam";
        String link = "http://seablogs.zenfs.com/u/jIGqcwqAHxoPyp4FU4djj22y/photo/ap_20110504123928120.jpg";
        Tour t;
        for (int i = 0; i < 10; i++) {
            t = new Tour(name, address, "20/06/2016", "5.000.000", link);
            data.add(t);
        }
        return data;
    }

}
