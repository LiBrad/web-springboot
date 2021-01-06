package com.brad.web.webspringboot.oaSystem.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("oa_hospital_notice")
public class NoticeBean {
    @TableId(value = "ID")
    private String id;

    @TableField("notice_title")
    private String noticeTitle;

    @TableField("notice_release_time")
    private String noticeReleaseTime;

    @TableField("create_time")
    private String createTime;

    @TableField("notice_content")
    private String noticeContent;

    @TableField("notice_number_visits")
    private Integer noticeNumberVisits;

    public Integer getNoticeNumberVisits() {
        return noticeNumberVisits;
    }

    public void setNoticeNumberVisits(Integer noticeNumberVisits) {
        this.noticeNumberVisits = noticeNumberVisits;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle == null ? null : noticeTitle.trim();
    }

    public String getNoticeReleaseTime() {
        return noticeReleaseTime;
    }

    public void setNoticeReleaseTime(String noticeReleaseTime) {
        this.noticeReleaseTime = noticeReleaseTime == null ? null : noticeReleaseTime.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent == null ? null : noticeContent.trim();
    }
}