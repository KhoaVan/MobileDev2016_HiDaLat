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
import vn.hidalat.interfaces.OnItemClickListener;
import vn.hidalat.models.NewsPage;

/**
 * Created by Nguyen_Dat on 6/21/2016.
 */
public class NewsPageFragment extends Fragment {
    protected RecyclerView mRecycler;

    public NewsPageFragment() {
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
        NewsPageAdapter adapter = getAdapter();
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

    private NewsPageAdapter getAdapter() {
        ArrayList<NewsPage> data = initSampleData();
        return new NewsPageAdapter(getContext(),data,mRecycler);
    }

    private ArrayList<NewsPage> initSampleData() {
        ArrayList<NewsPage> data = new ArrayList<>() ;
        String title = "Cao đẳng sư phạm";
        String date = "20/06/2016";
        String description = "Trường Cao đẳng Sư phạm Đà Lạt là một trường cao đẳng được thành lập ngày 3 tháng 9 năm 1976 theo quyết định số 1784/QĐ của Bộ Giáo dục, trụ sở đặt tại thành phố Đà Lạt, tỉnh Lâm Đồng.";
        String link = "http://seablogs.zenfs.com/u/jIGqcwqAHxoPyp4FU4djj22y/photo/ap_20110504123928120.jpg";
        String name = "Mr. Don";
        NewsPage t;
        for (int i = 0; i < 1; i++) {
            t = new NewsPage(title,date, description, link, name);
            data.add(t);
        }
        return data;
    }

}
