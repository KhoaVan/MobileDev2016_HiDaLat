package vn.hidalat.fragments.news;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.hidalat.R;
import vn.hidalat.activities.DetailNewsActivity;
import vn.hidalat.activities.DetailPlaceActivity;
import vn.hidalat.adapters.NewsAdapter;
import vn.hidalat.interfaces.OnItemClickListener;
import vn.hidalat.interfaces.ServiceListener;
import vn.hidalat.interfaces.TravelService;
import vn.hidalat.models.News;
import vn.hidalat.models.post.PageReq;
import vn.hidalat.models.post.PlaceReq;
import vn.hidalat.nets.RestService;
import vn.hidalat.utils.Parser.JsonParser;
import vn.hidalat.utils.constant.Const;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    private static final String TAG = "NewsFrag";
    public static final String P_NEWS = "NewsFragment.P_NEWS";
    protected RecyclerView mRecycler;
    private NewsAdapter mAdapter;
    private NewsAdapter.LoadMoreListener mOnLoadMore;
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
        //NewsAdapter adapter = getAdapter();
        final ServiceListener onResponse = new ServiceListener() {
            @Override
            public void onSuccess(Object data, int error, String msg) {
                Log.e(TAG, "onSuccess");
                // Update adapter
                if (data != null) {
                    ArrayList<Object> obj = (ArrayList<Object>) data;
                    int page = (int) obj.get(0);
                    ArrayList<News> news = (ArrayList<News>) obj.get(1);
                    if (page == Const.DEFAULT_PAGE) {
                        mAdapter = new NewsAdapter(getContext(), news, mRecycler);
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
                        mOnLoadMore.onLoadMoreSuccess(page, news);
                    }

                } else {
                    // TODO: notify

                }
            }

            @Override
            public void onFailure(Object data, int error, String msg) {
                Log.e(TAG, "onFailure");
            }
        };

        //  Load more
        mOnLoadMore = new NewsAdapter.LoadMoreListener() {
            @Override
            public void onLoadMore(int pagePosition) {
                reqData(pagePosition, onResponse);
            }

            @Override
            public void onLoadMoreSuccess(int pagePosition, ArrayList<News> appendData) {
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
        Intent intent = new Intent(getContext(), DetailNewsActivity.class);
        if (mAdapter != null) {
            News p = mAdapter.getItem(position);
            intent.putExtra(P_NEWS, (Parcelable) p);
        }
        startActivity(intent);
    }

    /**
     * Create an adapter for recycler view
     */
    protected void reqData(int page, final  ServiceListener onResponse){
        final int p = page;
        TravelService service = RestService.create(TravelService.class);
        service.reqNews(new PageReq(page))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.body() != null) {
                            try {
                                ArrayList<Object> data = new ArrayList<Object>();
                                ArrayList<News> news = new ArrayList<News>();
                                String json = response.body().string();
                                Log.e(TAG, json);
                                news = new JsonParser().parseNews(json);
                                data.add(p);
                                data.add(news);
                                onResponse.onSuccess(data, ServiceListener.SUCCESS, null);
                            } catch (IOException e) {
                                Log.e(TAG, e.toString());
                            }
                        } else{
                            onResponse.onFailure(null, ServiceListener.FAILURE, "Null response");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e(TAG, t.toString());
                        onResponse.onFailure(null, ServiceListener.FAILURE, t.toString());
                    }
                });
    }
}

