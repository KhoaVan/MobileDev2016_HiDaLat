package vn.hidalat.interfaces;

/**
 * Created by khoavankas on 16/06/2016.
 */
public interface ServiceListener {
    int FAILURE = -1;
    int SUCCESS = 1;

    void onSuccess(Object data, int error, String msg);
    void onFailure(Object data, int error, String msg);
}
