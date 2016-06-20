package vn.hidalat.fragments.news;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.hidalat.R;
import vn.hidalat.activities.DetailNewsActivity;
import vn.hidalat.adapters.NewsAdapter;
import vn.hidalat.interfaces.OnItemClickListener;
import vn.hidalat.models.News;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    protected RecyclerView mRecycler;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        mRecycler = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        NewsAdapter adapter = getAdapter();
        if (adapter != null){
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClicked(View v, int position) {
                    startActivity(new Intent(getContext(), DetailNewsActivity.class));
                }
            });
        }

        mRecycler.setAdapter(adapter);
        return v;
    }

    private NewsAdapter getAdapter() {
        ArrayList<News> data = initSampleData();
        return new NewsAdapter(getContext(), data, mRecycler);
    }

    private ArrayList<News> initSampleData() {
        ArrayList<News> data = new ArrayList<>();
        String name = "Cao đẳng sư phạm";
        String description = "Trường Cao đẳng Sư phạm Đà Lạt là một trường cao đẳng được thành lập ngày 3 tháng 9 năm 1976 theo quyết định số 1784/QĐ của Bộ Giáo dục, trụ sở đặt tại thành phố Đà Lạt, tỉnh Lâm Đồng.";
        String link = "http://seablogs.zenfs.com/u/jIGqcwqAHxoPyp4FU4djj22y/photo/ap_20110504123928120.jpg";
        News t;
        for (int i = 0; i < 10; i++) {
            t = new News(name, "20/06/2016", description, link);
            data.add(t);
        }
        return data;
    }
}
