package com.socialuni.social.admin.model;

public class BaseImg {
    private String img;
    private Double aspectRatio;

    public BaseImg() {
    }

    public BaseImg(String img, Integer width, Integer height) {
        this.img = img;
        this.aspectRatio = (double) height / width;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Double getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(Double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }
}
