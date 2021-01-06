package com.brad.web.webspringboot.oaSystem.bean;

/**
 * @version 1.0
 * @author： 李逸野
 * @date： 2020-11-05
 */

public class ImageVideoBean {
    private String videoUrl;

    private String imgUrl;

    private String enclosureUrl; //附件url

    public String getEnclosureUrl() {
        return enclosureUrl;
    }

    public void setEnclosureUrl(String enclosureUrl) {
        this.enclosureUrl = enclosureUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
