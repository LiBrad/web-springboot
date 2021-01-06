package com.brad.web.webspringboot.oaSystem.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("oa_hospital_news")
public class NewsEntity {
    @TableId(value = "ID")
    private String id;

    @TableField("NEWS_CATEGORY")
    private String newsCategory;

    @TableField("news_content_title")
    private String newsContentTitle;

    @TableField("news_display_status")
    private String newsDisplayStatus;

    @TableField("news_recommended_type")
    private String newsRecommendedType;

    @TableField("news_cover_photo_url")
    private String newsCoverPhotoUrl;

    @TableField("news_number_visits")
    private Integer newsNumberVisits;

    @TableField("news_release_time")
    private String newsReleaseTime;

    @TableField("create_time")
    private String createTime;

    @TableField("news_content_description")
    private String newsContentDescription;

    @TableField("news_recommended_image_type")
    private String newsRecommendedImageType;


//    public String getNewsRecommendedImageType() {
//        return newsRecommendedImageType;
//    }
//
//    public void setNewsRecommendedImageType(String newsRecommendedImageType) {
//        this.newsRecommendedImageType = newsRecommendedImageType;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id == null ? null : id.trim();
//    }
//
//    public String getNewsCategory() {
//        return newsCategory;
//    }
//
//    public void setNewsCategory(String newsCategory) {
//        this.newsCategory = newsCategory == null ? null : newsCategory.trim();
//    }
//
//    public String getNewsContentTitle() {
//        return newsContentTitle;
//    }
//
//    public void setNewsContentTitle(String newsContentTitle) {
//        this.newsContentTitle = newsContentTitle == null ? null : newsContentTitle.trim();
//    }
//
//    public String getNewsDisplayStatus() {
//        return newsDisplayStatus;
//    }
//
//    public void setNewsDisplayStatus(String newsDisplayStatus) {
//        this.newsDisplayStatus = newsDisplayStatus == null ? null : newsDisplayStatus.trim();
//    }
//
//    public String getNewsRecommendedType() {
//        return newsRecommendedType;
//    }
//
//    public void setNewsRecommendedType(String newsRecommendedType) {
//        this.newsRecommendedType = newsRecommendedType == null ? null : newsRecommendedType.trim();
//    }
//
//    public String getNewsCoverPhotoUrl() {
//        return newsCoverPhotoUrl;
//    }
//
//    public void setNewsCoverPhotoUrl(String newsCoverPhotoUrl) {
//        this.newsCoverPhotoUrl = newsCoverPhotoUrl == null ? null : newsCoverPhotoUrl.trim();
//    }
//
//    public Integer getNewsNumberVisits() {
//        return newsNumberVisits;
//    }
//
//    public void setNewsNumberVisits(Integer newsNumberVisits) {
//        this.newsNumberVisits = newsNumberVisits;
//    }
//
//    public String getNewsReleaseTime() {
//        return newsReleaseTime;
//    }
//
//    public void setNewsReleaseTime(String newsReleaseTime) {
//        this.newsReleaseTime = newsReleaseTime == null ? null : newsReleaseTime.trim();
//    }
//
//    public String getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(String createTime) {
//        this.createTime = createTime == null ? null : createTime.trim();
//    }
//
//    public String getNewsContentDescription() {
//        return newsContentDescription;
//    }
//
//    public void setNewsContentDescription(String newsContentDescription) {
//        this.newsContentDescription = newsContentDescription == null ? null : newsContentDescription.trim();
//    }
}