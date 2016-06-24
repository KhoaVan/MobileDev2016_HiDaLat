package vn.hidalat.fragments.place;


import android.support.v4.app.Fragment;

import vn.hidalat.interfaces.ServiceListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class StayingFragment extends GeneralPlaceFragment {


    public StayingFragment() {
        // Required empty public constructor
    }

    @Override
    protected void reqData(int page, ServiceListener onResponse) {
        onResponse.onFailure(null, ServiceListener.FAILURE, null);
    }

}
