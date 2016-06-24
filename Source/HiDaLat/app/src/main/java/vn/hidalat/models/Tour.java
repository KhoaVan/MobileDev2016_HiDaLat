package vn.hidalat.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;

import vn.hidalat.models.post.LatLng;

/**
 * Created by j3ao on 6/20/2016.
 */
public class Tour implements Parcelable{
    private String id;
    private String name;
    private String company;
    private String thumbnail;
    private String price;
    private String telephone;
    private String email;
    private String duration;
   // private String address;
    private String startdate;
    private String description;
    private String content;
    private ArrayList<String> imageList;
    private Boolean ishot = false;

    public Tour(String name, String description, String startdate, String price, String thumbnail) {
        this.name = name;
        this.description = description;
        this.startdate = startdate;
        this.price = price;
        this.thumbnail = thumbnail;
    }

    public Tour(String id, String name, String company,String thumbnail, String price,
                String telephone, String email, String duration, String startdate, String description, String content,
                ArrayList<String> imageList, Boolean ishot ){
        this.id = id;
        this.name = name;
        this.company = company;
        this.thumbnail  = thumbnail;
        this.price     = price;
        this.telephone = telephone;
        this.email  = email;
        this.duration = duration;
        this.startdate = startdate;
        this.description = description;
        this.content = content;
        this.imageList = imageList;
        this.ishot = ishot;
    }
    public String getId(){return id;}

    public String getCompany(){return company;}

    public String getTelephone(){return telephone;}

    public String getEmail(){return email;}

    public String getDuration(){return duration;}

    public String getDecription(){return description;}

    public  String getContent (){return content;}

    public String getName() {
        return name;
    }

//    public String getAddress() {
//        return address;
//    }

    public String getStartdate() { return startdate; }

    public String getPrice() {return price; }

    public String getThumbnail() {
        return thumbnail;
    }

    public ArrayList<String> getImageList(){return imageList;}

    public Boolean getIshot(){return  ishot;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.company);
        dest.writeString(this.thumbnail);
        dest.writeString(this.price);
        dest.writeString(this.telephone);
        dest.writeString(this.email);
        dest.writeString(this.duration);
        dest.writeString(this.startdate);
        dest.writeString(this.description);
        dest.writeString(this.content);
        dest.writeStringList(this.imageList);
        dest.writeValue(this.ishot);
    }

    protected Tour(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.company = in.readString();
        this.thumbnail = in.readString();
        this.price = in.readString();
        this.telephone = in.readString();
        this.email = in.readString();
        this.duration = in.readString();
        this.startdate= in.readString();
        this.description = in.readString();
        this.content = in.readString();
        this.imageList = in.createStringArrayList();
        this.ishot = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<Tour> CREATOR = new Parcelable.Creator<Tour>() {
        @Override
        public Tour createFromParcel(Parcel source) {
            return new Tour(source);
        }

        @Override
        public Tour[] newArray(int size) {
            return new Tour[size];
        }
    };
}

