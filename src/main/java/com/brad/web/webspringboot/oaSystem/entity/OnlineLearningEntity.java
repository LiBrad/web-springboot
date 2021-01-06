package com.brad.web.webspringboot.oaSystem.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("oa_hospital_online_learning")
public class OnlineLearningEntity {
    @TableId(value = "ID")
    private String id;

    @TableField("ol_title")
    private String olTitle;

    @TableField("technical_information_type_id")
    private String technicalInformationTypeId;

    @TableField("ol_release_time")
    private String olReleaseTime;

    @TableField("create_time")
    private String createTime;

    @TableField("ol_content")
    private String olContent;

    @TableField("ol_title")
    private String technicalInformationTypeName;//技术资料类别名称

    @TableField("ol_user_id")
    private String olUserId;

    private String olUserName;//发布人名字

    @TableField("ol_number_visits")
    private Integer olNumberVisits;

    public Integer getOlNumberVisits() {
        return olNumberVisits;
    }

    public void setOlNumberVisits(Integer olNumberVisits) {
        this.olNumberVisits = olNumberVisits;
    }


    public String getOlUserId() {
        return olUserId;
    }

    public void setOlUserId(String olUserId) {
        this.olUserId = olUserId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOlTitle() {
        return olTitle;
    }

    public void setOlTitle(String olTitle) {
        this.olTitle = olTitle == null ? null : olTitle.trim();
    }

    public String getTechnicalInformationTypeId() {
        return technicalInformationTypeId;
    }

    public void setTechnicalInformationTypeId(String technicalInformationTypeId) {
        this.technicalInformationTypeId = technicalInformationTypeId == null ? null : technicalInformationTypeId.trim();
    }

    public String getOlReleaseTime() {
        return olReleaseTime;
    }

    public void setOlReleaseTime(String olReleaseTime) {
        this.olReleaseTime = olReleaseTime == null ? null : olReleaseTime.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getOlContent() {
        return olContent;
    }

    public void setOlContent(String olContent) {
        this.olContent = olContent == null ? null : olContent.trim();
    }
}