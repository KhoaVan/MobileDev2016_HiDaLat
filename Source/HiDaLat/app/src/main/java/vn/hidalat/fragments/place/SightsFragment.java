package vn.hidalat.fragments.place;


import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.hidalat.interfaces.ServiceListener;
import vn.hidalat.interfaces.TravelService;
import vn.hidalat.models.Place;
import vn.hidalat.models.post.PlaceReq;
import vn.hidalat.nets.RestService;
import vn.hidalat.utils.Parser.JsonParser;
import vn.hidalat.utils.constant.Const;

public class SightsFragment extends GeneralPlaceFragment {
    private static final String TAG = "SightsFragment";

    public SightsFragment() {
        // Required empty public constructor
    }

    @Override
    protected void reqData(int page, final ServiceListener onResponse) {
        final int p = page;
        TravelService service = RestService.create(TravelService.class);

        service.reqPlacesByType(new PlaceReq(Const.SIGHTS_PLACE, page))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.body() != null) {
                            try {
                                ArrayList<Object> data = new ArrayList<Object>();
                                ArrayList<Place> places = new ArrayList<Place>();
                                String json = response.body().string();
                                Log.e(TAG, json);
                                places = new JsonParser().parsePlaces(json);
                                data.add(p);
                                data.add(places);
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
    /*@Override
    protected PlaceAdapter getAdapter() {
        ArrayList<Place> data = new ArrayList<>();
        Place place;
        String link = "http://fantasea.vn/sites/default/files/edensee%20lake.jpg";
        String name = "Đà Lạt Edensee Lake Resort & Spa";
        String address = "Hồ Tuyền Lâm Đà Lạt";
        String description = "Đà Lạt Edensee Lake Resort & Spa tọa lạc ở Hồ Tuyền Lâm Đà Lạt sẽ là nơi nghỉ mát lý tưởng cho gia đình bạn với tiêu chuẩn 5 Đà Lạt Edensee Lake Resort & Spa tự sẽ làm hài lòng tất cả các du khách.";
        for (int i = 0; i < 10; i++) {
            place = new Place(name, address, description, link);
            data.add(place);
        }
        return new PlaceAdapter(getContext(), data, mRecycler);
    }*/

}
