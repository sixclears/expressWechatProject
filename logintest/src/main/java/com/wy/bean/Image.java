package com.wy.bean;

import java.io.Serializable;

public class Image implements Serializable {
    private String name;
    private String page;
    private String imageSrc;

    public Image() {
    }

    public Image(String name, String page, String image_src) {
        this.name = name;
        this.page = page;
        this.imageSrc = image_src;
    }

    public String getName() {
        return name;
    }

    public String getPage() {
        return page;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setImageSrc(String image_src) {
        this.imageSrc = image_src;
    }

    @Override
    public String toString() {
        return "Image{" +
                "name='" + name + '\'' +
                ", page='" + page + '\'' +
                ", image_src='" + imageSrc + '\'' +
                '}';
    }
}
