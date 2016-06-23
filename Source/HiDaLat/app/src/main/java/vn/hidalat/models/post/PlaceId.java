package vn.hidalat.models.post;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khoavankas on 21/06/2016.
 */
public class PlaceId {
    @SerializedName("id")
    String id;

    public PlaceId(String id) {
        this.id = id;
    }
}
