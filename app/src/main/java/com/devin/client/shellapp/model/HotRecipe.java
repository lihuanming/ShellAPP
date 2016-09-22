package com.devin.client.shellapp.model;

/**
 * Created by 书凡 on 2015-11-27.
 */
public class HotRecipe {
    private int imgUrl;
    private String name;
    private String longstring;
    private String date;

    public int getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongstring() {
        return longstring;
    }

    public void setLongstring(String longstring) {
        this.longstring = longstring;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
