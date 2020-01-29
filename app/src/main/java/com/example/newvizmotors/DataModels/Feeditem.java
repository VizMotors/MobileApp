package com.example.newvizmotors.DataModels;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.Exclude;

import java.util.Map;

public class Feeditem {
    private String id;
    private String image;
    private String name;
    private String proflePic;
    private String status;
    private String timestamp;
    private String category;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    private int drawableImage, drawableProfilePic;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProflePic() {
        return proflePic;
    }

    public void setProflePic(String proflePic) {
        this.proflePic = proflePic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    public Feeditem(String id, String name, String image, String proflePic, String status, String timestamp, String category) {
        super();
        this.id = id;
        this.name = name;
        this.image = image;
        this.proflePic = proflePic;
        this.status = status;
        this.timestamp = timestamp;
        this.category = category;
    }

    public Feeditem() {

    }


    @Exclude
    public Feeditem toFeedItem(Map item) {
        if (item != null) {
            Feeditem c = new Feeditem();

            c.setName(item.get("name").toString());
            if (item.get("timestamp") != null) {
                c.setStatus(item.get("timestamp").toString());
            }
            c.setImage(item.get("image").toString());
            if (item.get("status") != null) {
                c.setStatus(item.get("status").toString());
            }
            if (item.get("timestamp") != null) {
                c.setTimestamp(item.get("timestamp").toString());
            }

            if (item.get("profilePic") != null) {
                c.setProflePic(item.get("profilePic").toString());
            }

            if(item.get("category") != null) {

                c.setCategory(item.get("category").toString());

            }

            return c;
        }
        return null;
    }
}
