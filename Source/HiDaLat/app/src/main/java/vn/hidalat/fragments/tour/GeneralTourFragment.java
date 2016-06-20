package vn.hidalat.fragments.tour;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.hidalat.R;
import vn.hidalat.activities.DetailTourActivity;
import vn.hidalat.adapters.TourAdapter;
import vn.hidalat.interfaces.OnItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class GeneralTourFragment extends Fragment {
    private static final String TAG = "GeneralTourFrag";
    protected RecyclerView mRecycler;

    public GeneralTourFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_general_tour, container, false);
        mRecycler = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        TourAdapter adapter = getAdapter();
        if (adapter != null){
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClicked(View v, int position) {
                    Log.e(TAG, "onItemClicked");
                    doOnItemClicked(v, position);
                }
            });
        }

        mRecycler.setAdapter(adapter);
        return v;
    }

    /**
     * Handle recycler view item clicked. Also modified in subclass
     */
    protected void doOnItemClicked(View v, int position) {
        startActivity(new Intent(getContext(), DetailTourActivity.class));
    }

    /**
     * Create an adapter for recycler view
     */
    protected abstract TourAdapter getAdapter();
}
