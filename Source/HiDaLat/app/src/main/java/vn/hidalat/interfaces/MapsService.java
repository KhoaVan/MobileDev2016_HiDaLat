package vn.hidalat.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by khoavankas on 20/06/2016.
 */
public interface MapsService {
    @GET
    Call<ResponseBody> fetchingRoute(@Url String emptyUrl);
}
