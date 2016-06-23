package vn.hidalat.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;

import vn.hidalat.models.post.LatLng;

/**
 * Created by khoavankas on 17/06/2016.
 */
public class Place implements Parcelable {
    private String id;
    private String name;
    private String address;
    private String description;
    private String thumbnail;

    private String type;
    private String phone;
    private String summary;
    private String relatedTour; // tour id
    private ArrayList<String> imageList;
    private HashMap<String, String> extendInfo;
    private OpenTime openTime;
    private LatLng latLng;

    public Place(String name, String address, String description, String thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.description = description;
        this.address = address;
    }

    public Place(String id, String name, String address, String description, String thumbnail,
                 String type, String phone, String summary, String relatedTour, ArrayList<String> imageList,
                 HashMap<String, String> extendInfo, OpenTime openTime, LatLng latLng) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.thumbnail = thumbnail;
        this.type = type;
        this.phone = phone;
        this.summary = summary;
        this.relatedTour = relatedTour;
        this.imageList = imageList;
        this.extendInfo = extendInfo;
        this.openTime = openTime;
        this.latLng = latLng;
    }

    public String getName() {
        return name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getPhone() {
        return phone;
    }

    public String getSummary() {
        return summary;
    }

    public String getRelatedTour() {
        return relatedTour;
    }

    public ArrayList<String> getImageList() {
        return imageList;
    }

    public HashMap<String, String> getExtendInfo() {
        return extendInfo;
    }

    public OpenTime getOpenTime() {
        return openTime;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.address);
        dest.writeString(this.description);
        dest.writeString(this.thumbnail);
        dest.writeString(this.type);
        dest.writeString(this.phone);
        dest.writeString(this.summary);
        dest.writeString(this.relatedTour);
        dest.writeStringList(this.imageList);
        dest.writeSerializable(this.extendInfo);
        dest.writeParcelable(this.openTime, flags);
        dest.writeParcelable(this.latLng, flags);
    }

    protected Place(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.address = in.readString();
        this.description = in.readString();
        this.thumbnail = in.readString();
        this.type = in.readString();
        this.phone = in.readString();
        this.summary = in.readString();
        this.relatedTour = in.readString();
        this.imageList = in.createStringArrayList();
        this.extendInfo = (HashMap<String, String>) in.readSerializable();
        this.openTime = in.readParcelable(OpenTime.class.getClassLoader());
        this.latLng = in.readParcelable(LatLng.class.getClassLoader());
    }

    public static final Parcelable.Creator<Place> CREATOR = new Parcelable.Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel source) {
            return new Place(source);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };
}
