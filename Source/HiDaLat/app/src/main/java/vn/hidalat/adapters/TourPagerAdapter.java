package vn.hidalat.adapters;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import vn.hidalat.models.Tour;

/**
 * Created by j3ao on 6/20/2016.
 */
public class TourPagerAdapter extends FragmentStatePagerAdapter {
    private FragmentManager mFragManager;
    private final List<Fragment> mFrags;
    private final List<String> mFragTitles;
    public TourPagerAdapter(FragmentManager fm) {
        super(fm);
        mFrags = new ArrayList<>();
        mFragTitles = new ArrayList<>();
        this.mFragManager = fm;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public Fragment getItem(int position) {
        return mFrags.get(position);
    }

    @Override
    public int getCount() {
        return mFrags.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragTitles.get(position);
    }

    public void addFragment(Fragment fragment, String title ){
        mFrags.add(fragment);
        mFragTitles.add(title);
    }
}
