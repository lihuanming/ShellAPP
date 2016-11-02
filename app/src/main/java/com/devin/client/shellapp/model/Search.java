package com.devin.client.shellapp.model;

import java.io.Serializable;

/**
 * @author devin
 * @Class Search
 * @Date 16/10/28
 */

public class Search implements Serializable{

    private int id;

    private String title;

    private String content;

    private int image;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
