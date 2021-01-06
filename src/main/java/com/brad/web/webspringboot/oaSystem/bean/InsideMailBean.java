package com.brad.web.webspringboot.oaSystem.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("oa_hospital_inside_mail")
public class InsideMailBean {
    @TableId(value = "ID")
    private String id;

    @TableField("inside_mai_addresseel_user_id")
    private String insideMaiAddresseelUserId;

    @TableField("inside_mai_from_user_id")
    private String insideMaiFromUserId;

    @TableField("inside_mail_theme")
    private String insideMailTheme;

    @TableField("inside_mail_enclosure_url")
    private String insideMailEnclosureUrl;

    @TableField("inside_mail_content")
    private String insideMailContent;

    @TableField("inside_mail_to_read")
    private String insideMailToRead;

    @TableField("inside_mail_to_reply")
    private String insideMailToReply;

    @TableField("create_time")
    private String createTime;

    private String insideMaiAddresseelUserName;// 收件人
    private String insideMaiFromUserName;// 发件人

    private String insideMaiAddresseelUserIdS;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getInsideMaiAddresseelUserId() {
        return insideMaiAddresseelUserId;
    }

    public void setInsideMaiAddresseelUserId(String insideMaiAddresseelUserId) {
        this.insideMaiAddresseelUserId = insideMaiAddresseelUserId == null ? null : insideMaiAddresseelUserId.trim();
    }

    public String getInsideMaiFromUserId() {
        return insideMaiFromUserId;
    }

    public void setInsideMaiFromUserId(String insideMaiFromUserId) {
        this.insideMaiFromUserId = insideMaiFromUserId == null ? null : insideMaiFromUserId.trim();
    }

    public String getInsideMailTheme() {
        return insideMailTheme;
    }

    public void setInsideMailTheme(String insideMailTheme) {
        this.insideMailTheme = insideMailTheme == null ? null : insideMailTheme.trim();
    }

    public String getInsideMailEnclosureUrl() {
        return insideMailEnclosureUrl;
    }

    public void setInsideMailEnclosureUrl(String insideMailEnclosureUrl) {
        this.insideMailEnclosureUrl = insideMailEnclosureUrl == null ? null : insideMailEnclosureUrl.trim();
    }

    public String getInsideMailContent() {
        return insideMailContent;
    }

    public void setInsideMailContent(String insideMailContent) {
        this.insideMailContent = insideMailContent == null ? null : insideMailContent.trim();
    }

    public String getInsideMailToRead() {
        return insideMailToRead;
    }

    public void setInsideMailToRead(String insideMailToRead) {
        this.insideMailToRead = insideMailToRead == null ? null : insideMailToRead.trim();
    }

    public String getInsideMailToReply() {
        return insideMailToReply;
    }

    public void setInsideMailToReply(String insideMailToReply) {
        this.insideMailToReply = insideMailToReply == null ? null : insideMailToReply.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
}