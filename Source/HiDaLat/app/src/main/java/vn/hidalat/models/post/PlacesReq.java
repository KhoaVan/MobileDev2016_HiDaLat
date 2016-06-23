package vn.hidalat.models.post;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khoavankas on 21/06/2016.
 */
public class PlacesReq {
    @SerializedName("id")
    String id;

    @SerializedName("page")
    Integer page;

    public PlacesReq(String id, Integer page) {
        this.page = page;
        this.id = id;
    }
}
