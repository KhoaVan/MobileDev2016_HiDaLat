package vn.hidalat.models;

/**
 * Created by j3ao on 6/20/2016.
 */
public class Tour {
    private String name;
    private String address;
    private String time;
    private String price;
    private String thumbnail;

    public Tour(String name, String address, String time, String price, String thumbnail) {
        this.name = name;
        this.address = address;
        this.time = time;
        this.price = price;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTime() { return time; }

    public String getPrice() {return price; }

    public String getThumbnail() {
        return thumbnail;
    }


}

