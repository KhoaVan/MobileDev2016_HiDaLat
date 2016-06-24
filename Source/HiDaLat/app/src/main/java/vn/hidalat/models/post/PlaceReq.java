package vn.hidalat.models.post;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khoavankas on 21/06/2016.
 */
public class PlaceReq {
    @SerializedName("id")
    String id;

    @SerializedName("page")
    Integer page;

    public PlaceReq(String id, Integer page) {
        this.page = page;
        this.id = id;
    }
}
