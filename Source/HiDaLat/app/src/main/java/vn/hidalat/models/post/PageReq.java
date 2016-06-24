package vn.hidalat.models.post;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nguyen_Dat on 6/23/2016.
 */
public class PageReq {
    @SerializedName("page")
    Integer page;

    public PageReq(int page) {
        this.page = page;
    }
}
