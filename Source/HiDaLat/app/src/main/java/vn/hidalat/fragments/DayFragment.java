package vn.hidalat.fragments;

/**
 * Created by j3ao on 6/20/2016.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.hidalat.R;
import vn.hidalat.adapters.TourAdapter;
import vn.hidalat.models.Tour;

public class DayFragment extends Fragment{
    private RecyclerView mRecycler;
    public DayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_top_rated, container, false);
        mRecycler = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        TourAdapter adapter = getAdapter();
        mRecycler.setAdapter(adapter);
        return v;
    }

    protected TourAdapter getAdapter(){
        ArrayList<Tour> data = new ArrayList<>();
        Tour tour;
        String link = "http://seablogs.zenfs.com/u/jIGqcwqAHxoPyp4FU4djj22y/photo/ap_20110504123928120.jpg";
        String time = "1 ngày";
        for (int i = 0; i < 10; i++) {
            tour = new Tour("Cao đẳng sư phạm", "29 Yersin, tp. Đà Lạt, Lâm Đồng, Việt Nam","2000000", time, link);
            data.add(tour);
        }
        return new TourAdapter(getContext(), data, mRecycler);
    }
}
