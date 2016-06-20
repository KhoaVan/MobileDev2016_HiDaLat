package vn.hidalat.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.hidalat.R;
import vn.hidalat.adapters.TourPagerAdapter;
import vn.hidalat.models.Tour;


/**
 * Created by j3ao on 6/20/2016.
 */

public class TourFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private ViewPager mViewPager;
    private TourPagerAdapter mPagerAdapter;
    public TourFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tour, container, false);
        setUpTabLayout(v);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void setUpTabLayout(View container) {
        mViewPager = (ViewPager)container.findViewById(R.id.viewpager_tour);
        setUpViewPager(mViewPager);
        // TabLayout with ViewPager
        TabLayout tabLayout = (TabLayout)container.findViewById(R.id.tab_tour);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setUpViewPager(ViewPager viewPager) {
        mPagerAdapter = new TourPagerAdapter(getActivity().getSupportFragmentManager());
        mPagerAdapter.addFragment(new TopRatedFragment(), getResources().getString(R.string.day));
        mPagerAdapter.addFragment(new TopRatedFragment(), getResources().getString(R.string.all));

        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(mPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
