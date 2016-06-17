package vn.hidalat.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.hidalat.R;
import vn.hidalat.adapters.PlaceAdapter;
import vn.hidalat.models.Place;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopRatedFragment extends Fragment {

    private RecyclerView mRecycler;
    public TopRatedFragment() {
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
        PlaceAdapter adapter = getAdapter();
        mRecycler.setAdapter(adapter);
        return v;
    }

    protected PlaceAdapter getAdapter(){
        ArrayList<Place> data = new ArrayList<>();
        Place place;
        String link = "http://seablogs.zenfs.com/u/jIGqcwqAHxoPyp4FU4djj22y/photo/ap_20110504123928120.jpg";
        String description = "Trường Cao đẳng Sư phạm Đà Lạt là một trường cao đẳng được thành lập ngày 3 tháng 9 năm 1976 theo quyết định số 1784/QĐ của Bộ Giáo dục, trụ sở đặt tại thành phố Đà Lạt, tỉnh Lâm Đồng.";
        for (int i = 0; i < 10; i++) {
            place = new Place("Cao đẳng sư phạm", "29 Yersin, tp. Đà Lạt, Lâm Đồng, Việt Nam", description, link);
            data.add(place);
        }
        return new PlaceAdapter(getContext(), data, mRecycler);
    }

}
