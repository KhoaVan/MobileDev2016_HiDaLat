package vn.hidalat.fragments.tour;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.hidalat.R;
import vn.hidalat.activities.DetailPlaceActivity;
import vn.hidalat.activities.DetailTourActivity;
import vn.hidalat.adapters.PlaceAdapter;
import vn.hidalat.adapters.TourAdapter;
import vn.hidalat.interfaces.OnItemClickListener;
import vn.hidalat.interfaces.ServiceListener;
import vn.hidalat.models.Place;
import vn.hidalat.models.Tour;
import vn.hidalat.utils.constant.Const;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class GeneralTourFragment extends Fragment {
    private static final String TAG = "GeneralTourFrag";
    public static final String P_TOUR = "GeneralTourFragment.P_TOUR";
    protected RecyclerView mRecycler;
    private TourAdapter mAdapter;
    private TourAdapter.LoadMoreListener mOnLoadMore;

    public GeneralTourFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_general_tour, container, false);
        mRecycler = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        final ServiceListener onResponse = new ServiceListener() {
            @Override
            public void onSuccess(Object data, int error, String msg) {
                Log.e(TAG, "onSuccess");
                // Update adapter
                if (data != null){
                    ArrayList<Object> obj = (ArrayList<Object>) data;
                    int page = (int) obj.get(0);
                    ArrayList<Tour> tours = (ArrayList<Tour>) obj.get(1);
                    if (page == Const.DEFAULT_PAGE) {
                        mAdapter = new TourAdapter(getContext(), tours, mRecycler);
                        mAdapter.setOnLoadMoreListener(mOnLoadMore);
                        mAdapter.setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClicked(View v, int position) {
                                Log.e(TAG, "onItemClicked");
                                doOnItemClicked(v, position);
                            }
                        });
                        mRecycler.setAdapter(mAdapter);
                    } else {
                        mOnLoadMore.onLoadMoreSuccess(page, tours);
                    }

                } else{
                    // TODO: notify

                }
            }

            @Override
            public void onFailure(Object data, int error, String msg) {
                Log.e(TAG, "onFailure");
            }
        };
        //  Load more
        mOnLoadMore = new TourAdapter.LoadMoreListener() {
            @Override
            public void onLoadMore(int pagePosition) {
                reqData(pagePosition, onResponse);
            }

            @Override
            public void onLoadMoreSuccess(int pagePosition, ArrayList<Tour> appendData) {
                // Remove loading
                mAdapter.removeItem(null);
                mAdapter.addItems(appendData);
                mAdapter.setPageLoaded(pagePosition);
                mAdapter.setLoading(false);
            }

            @Override
            public void onLoadMoreFail(String message) {
                // Remove loading
                mAdapter.removeItem(null);
                mAdapter.setLoading(false);
            }
        };

        reqData(Const.DEFAULT_PAGE, onResponse);

        return v;
    }

    /**
     * Handle recycler view item clicked. Also modified in subclass
     */
    protected void doOnItemClicked(View v, int position) {
        Intent intent = new Intent(getContext(), DetailTourActivity.class);
        if (mAdapter != null) {
            Tour p = mAdapter.getItem(position);
            intent.putExtra(P_TOUR, p);
        }
        startActivity(intent);
    }

    /**
     * Create an adapter for recycler view
     */
    protected abstract void reqData(int page, ServiceListener onResponse);
}
