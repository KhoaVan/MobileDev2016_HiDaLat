package vn.hidalat.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.hidalat.models.post.LatLng;
import vn.hidalat.models.post.PlaceId;
import vn.hidalat.models.post.PlacesReq;

/**
 * Created by khoavankas on 16/06/2016.
 */
public interface TravelService {
    @GET("getAllLocation")
    Call<ResponseBody> reqAllPlace();

    @POST("getLocationByTypeId")
    Call<ResponseBody> reqPlacesByType(@Body PlacesReq type);
    @POST("getHotLocation")
    Call<ResponseBody> reqTopRatedPlace();
    @POST("getRelatedLocation")
    Call<ResponseBody> reqRelatedPlace(@Body PlaceId placeId);
    @POST("getNearByLocation")
    Call<ResponseBody> reqRelatedPlace(@Body LatLng latLng);


}
