package vn.hidalat.models;

/**
 * Created by Nguyen_Dat on 6/21/2016.
 */
public class NewsPage {
    String title;
    String date;
    String description;
    String thumbnail;
    String postperson;

    public NewsPage(String title, String date, String description, String thumbnail, String postperson ) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.thumbnail = thumbnail;
        this.postperson = postperson;

    }

    public String getTitle() {return title;}

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getPostperson() {
        return postperson;
    }

}


