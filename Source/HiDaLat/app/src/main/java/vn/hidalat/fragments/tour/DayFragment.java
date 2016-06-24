package vn.hidalat.fragments.tour;

/**
 * Created by j3ao on 6/20/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
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
import vn.hidalat.activities.DetailTourActivity;
import vn.hidalat.adapters.TourAdapter;
import vn.hidalat.interfaces.OnItemClickListener;
import vn.hidalat.interfaces.ServiceListener;
import vn.hidalat.interfaces.TravelService;
import vn.hidalat.models.Tour;
import vn.hidalat.models.post.PageReq;
import vn.hidalat.nets.RestService;
import vn.hidalat.utils.Parser.JsonParser;
import vn.hidalat.utils.constant.Const;

public class DayFragment extends GeneralTourFragment{
    private static final String TAG = "DayToursFrag";
    public DayFragment() {
        // Required empty public constructor
    }
    /**
     * Create an adapter for recycler view
     */
    protected void reqData(int page, final  ServiceListener onResponse){
        final int p = page;
        TravelService service = RestService.create(TravelService.class);
        service.reqTours(new PageReq(page))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.body() != null) {
                            try {
                                ArrayList<Object> data = new ArrayList<Object>();
                                ArrayList<Tour> tour = new ArrayList<Tour>();
                                String json = response.body().string();
                                Log.e(TAG, json);
                                tour = new JsonParser().parseTours(json);
                                data.add(p);
                                data.add(tour);
                                onResponse.onSuccess(data, ServiceListener.SUCCESS, null);
                            } catch (IOException e) {
                                Log.e(TAG, e.toString());
                            }
                        } else {
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
