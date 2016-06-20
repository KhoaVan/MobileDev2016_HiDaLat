package vn.hidalat.models;

/**
 * Created by khoavankas on 20/06/2016.
 */
public class News {
    String title;
    String date;
    String description;
    String thumbnail;

    public News(String title, String date, String description, String thumbnail) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.thumbnail = thumbnail;
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
}
