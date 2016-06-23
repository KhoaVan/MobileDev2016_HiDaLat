package vn.hidalat.nets;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by khoavankas on 16/06/2016.
 */
public class RestService {
    private static final String BASE_URL = "https://hidalatapp.herokuapp.com/public_api/";;//"http://192.168.137.1:4000/public_api/";
    private RestService() {}

    public static <T> T create(Class<T> serviceClass) {
        return getRetrofit(BASE_URL).create(serviceClass);
    }

    public static <T> T create(String url, Class<T> serviceClass) {
        return getRetrofit(url).create(serviceClass);
    }

    private static Retrofit getRetrofit(String url) {
        Log.e("RestService", url);
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging);
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }
}
