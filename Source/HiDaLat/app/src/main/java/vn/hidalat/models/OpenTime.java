package vn.hidalat.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by khoavankas on 23/06/2016.
 */
public class OpenTime implements Parcelable {
    private String open;
    private String close;

    public OpenTime(String open, String close) {
        this.open = open;
        this.close = close;
    }

    public String getOpen() {
        return open;
    }

    public String getClose() {
        return close;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.open);
        dest.writeString(this.close);
    }

    protected OpenTime(Parcel in) {
        this.open = in.readString();
        this.close = in.readString();
    }

    public static final Parcelable.Creator<OpenTime> CREATOR = new Parcelable.Creator<OpenTime>() {
        @Override
        public OpenTime createFromParcel(Parcel source) {
            return new OpenTime(source);
        }

        @Override
        public OpenTime[] newArray(int size) {
            return new OpenTime[size];
        }
    };
}
