package vn.hidalat.nets;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by khoavankas on 16/06/2016.
 */
public class RestService {
    public static final String BASE_URL = "https://api.flickr.com";
    private static Retrofit mRest;
    private RestService() {}
    public static <T> T create(Class<T> serviceClass) {
        return getRetrofit().create(serviceClass);
    }

    private static Retrofit getRetrofit() {
        if (mRest == null) {
            final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            final OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .addInterceptor(logging);
            mRest = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        return mRest;
    }
}
