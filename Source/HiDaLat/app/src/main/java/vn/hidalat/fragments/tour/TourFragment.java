package vn.hidalat.fragments.tour;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.hidalat.R;
import vn.hidalat.adapters.TourPagerAdapter;


/**
 * Created by j3ao on 6/20/2016.
 */

public class TourFragment extends Fragment {
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

    private void setUpTabLayout(View container) {
        mViewPager = (ViewPager)container.findViewById(R.id.viewpager_tour);
        setUpViewPager(mViewPager);
        // TabLayout with ViewPager
        TabLayout tabLayout = (TabLayout)container.findViewById(R.id.tab_tour);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setUpViewPager(ViewPager viewPager) {
        mPagerAdapter = new TourPagerAdapter(getActivity().getSupportFragmentManager());
        mPagerAdapter.addFragment(new DayFragment(), getResources().getString(R.string.day));
        mPagerAdapter.addFragment(new AllTourFragment(), getResources().getString(R.string.all));

        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(mPagerAdapter);
    }
}
