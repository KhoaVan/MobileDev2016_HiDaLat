package vn.hidalat.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.hidalat.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StayingFragment extends Fragment {


    public StayingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_staying, container, false);
        return v;
    }
}
