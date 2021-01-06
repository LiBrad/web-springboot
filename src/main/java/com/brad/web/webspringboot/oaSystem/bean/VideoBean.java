package com.brad.web.webspringboot.oaSystem.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("oa_hospital_video")
public class VideoBean {
    @TableId(value = "ID")
    private String id;

    @TableField("video_title")
    private String videoTitle;

    @TableField("video_release_time")
    private String videoReleaseTime;

    @TableField("video_url")
    private String videoUrl;

    @TableField("create_time")
    private String createTime;

    @TableField("video_content")
    private String videoContent;

    @TableField("video_number_visits")
    private Integer videoNumberVisits;

    public Integer getVideoNumberVisits() {
        return videoNumberVisits;
    }

    public void setVideoNumberVisits(Integer videoNumberVisits) {
        this.videoNumberVisits = videoNumberVisits;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle == null ? null : videoTitle.trim();
    }

    public String getVideoReleaseTime() {
        return videoReleaseTime;
    }

    public void setVideoReleaseTime(String videoReleaseTime) {
        this.videoReleaseTime = videoReleaseTime == null ? null : videoReleaseTime.trim();
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getVideoContent() {
        return videoContent;
    }

    public void setVideoContent(String videoContent) {
        this.videoContent = videoContent == null ? null : videoContent.trim();
    }
}