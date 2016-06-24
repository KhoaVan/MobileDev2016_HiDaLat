package vn.hidalat.models;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by khoavankas on 20/06/2016.
 */
public class News implements Parcelable {
    String title;
    String date;
    String description;
    String thumbnail;
    String id;
    Boolean ishot = false;
    String author;
    String content;
    public News(String title, String date, String description, String thumbnail) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.thumbnail = thumbnail;
    }
    public News (String id, String title,Boolean ishot, String author, String thumbnail, String description, String content, String date){
        this.id = id;
        this.title = title;
        this.ishot = ishot;
        this.author = author;
        this.thumbnail = thumbnail;
        this.description = description;
        this.content = content;
        this.date = date;
    }
    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getId() {
        return id;
    }

    public Boolean getIshot() {
        return ishot;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.date);
        dest.writeString(this.description);
        dest.writeString(this.thumbnail);
        dest.writeString(this.id);
        dest.writeValue(this.ishot);
        dest.writeString(this.author);
        dest.writeString(this.content);
    }

    protected News(Parcel in) {
        this.title = in.readString();
        this.date = in.readString();
        this.description = in.readString();
        this.thumbnail = in.readString();
        this.id = in.readString();
        this.ishot = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.author = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
        @Override
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
}