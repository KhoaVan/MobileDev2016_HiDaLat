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
import vn.hidalat.adapters.PlacePagerAdapter;

public class PlaceFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private ViewPager mViewPager;
    private PlacePagerAdapter mPagerAdapter;
    public PlaceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_place, container, false);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void setUpTabLayout(View container) {
        mViewPager = (ViewPager)container.findViewById(R.id.viewpager);
        setUpViewPager(mViewPager);
        // TabLayout with ViewPager
        TabLayout tabLayout = (TabLayout)container.findViewById(R.id.tab);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setUpViewPager(ViewPager viewPager) {
        mPagerAdapter = new PlacePagerAdapter(getActivity().getSupportFragmentManager());
        mPagerAdapter.addFragment(new TopRatedFragment(), getResources().getString(R.string.top_rated));
        mPagerAdapter.addFragment(new SightsFragment(), getResources().getString(R.string.sights));
        mPagerAdapter.addFragment(new StayingFragment(), getResources().getString(R.string.staying));
        mPagerAdapter.addFragment(new FoodFragment(), getResources().getString(R.string.food));
        mPagerAdapter.addFragment(new ShoppingFragment(), getResources().getString(R.string.shopping));

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
