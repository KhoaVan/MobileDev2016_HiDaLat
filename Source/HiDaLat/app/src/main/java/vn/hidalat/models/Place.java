package vn.hidalat.models;

/**
 * Created by khoavankas on 17/06/2016.
 */
public class Place {
    private String name;
    private String address;
    private String description;
    private String thumbnail;

    public Place(String name, String address, String description, String thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.description = description;
        this.address = address;
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
}
