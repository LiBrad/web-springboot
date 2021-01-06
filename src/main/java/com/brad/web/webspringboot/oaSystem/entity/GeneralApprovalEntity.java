package com.brad.web.webspringboot.oaSystem.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("oa_hospital_general_approval")
public class GeneralApprovalEntity {

    @TableId(value = "ID")
    private String id;

    @TableField("approved_by_user_id")
    private Integer approvedByUserId;

    @TableField("applicant_user_id")
    private Integer applicantUserId;

    @TableField("approval_image_url")
    private String approvalImageUrl;

    @TableField("approval_enclosure_url")
    private String approvalEnclosureUrl;

    @TableField("approval_results")
    private String approvalResults;

    @TableField("approval_time")
    private String approvalTime;

    @TableField("create_time")
    private String createTime;

    @TableField("application_content")
    private String applicationContent;

    @TableField("approval_details")
    private String approvalDetails;

    @TableField("sign")
    private String sign;

    @TableField("approval_comments")
    private String approvalComments;

    @TableField("application_title")
    private String applicationTitle;

    public String getApplicationContent() {
        return applicationContent;
    }

    public void setApplicationContent(String applicationContent) {
        this.applicationContent = applicationContent;
    }

    public String getApprovalDetails() {
        return approvalDetails;
    }

    public void setApprovalDetails(String approvalDetails) {
        this.approvalDetails = approvalDetails;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getApprovalComments() {
        return approvalComments;
    }

    public void setApprovalComments(String approvalComments) {
        this.approvalComments = approvalComments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getApprovedByUserId() {
        return approvedByUserId;
    }

    public void setApprovedByUserId(Integer approvedByUserId) {
        this.approvedByUserId = approvedByUserId;
    }

    public Integer getApplicantUserId() {
        return applicantUserId;
    }

    public void setApplicantUserId(Integer applicantUserId) {
        this.applicantUserId = applicantUserId;
    }

    public String getApprovalImageUrl() {
        return approvalImageUrl;
    }

    public void setApprovalImageUrl(String approvalImageUrl) {
        this.approvalImageUrl = approvalImageUrl == null ? null : approvalImageUrl.trim();
    }

    public String getApprovalEnclosureUrl() {
        return approvalEnclosureUrl;
    }

    public void setApprovalEnclosureUrl(String approvalEnclosureUrl) {
        this.approvalEnclosureUrl = approvalEnclosureUrl == null ? null : approvalEnclosureUrl.trim();
    }

    public String getApprovalResults() {
        return approvalResults;
    }

    public void setApprovalResults(String approvalResults) {
        this.approvalResults = approvalResults == null ? null : approvalResults.trim();
    }

    public String getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(String approvalTime) {
        this.approvalTime = approvalTime == null ? null : approvalTime.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
}