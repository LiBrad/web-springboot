package com.brad.web.webspringboot.oaSystem.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("oa_hospital_personnel_files")
public class PersonnelFilesEntity {

    @TableId(value = "ID")
    private String id;

    @TableField("personnel_files_name")
    private String personnelFilesName;

    @TableField("personnel_files_gender")
    private String personnelFilesGender;

    @TableField("personnel_files_date_birth")
    private String personnelFilesDateBirth;

    @TableField("personnel_files_nation")
    private String personnelFilesNation;

    @TableField("personnel_files_native_place")
    private String personnelFilesNativePlace;

    @TableField("personnel_files_photo_url")
    private String personnelFilesPhotoUrl;

    @TableField("personnel_files_id_card")
    private String personnelFilesIdCard;

    @TableField("personnel_files_political_situation")
    private String personnelFilesPoliticalSituation;

    @TableField("personnel_files_frist_working_hours")
    private String personnelFilesFristWorkingHours;

    @TableField("personnel_files_department_id")
    private String personnelFilesDepartmentId;

    @TableField("personnel_files_department_category")
    private String personnelFilesDepartmentCategory;

    @TableField("personnel_files_hospital_now_working_hours")
    private String personnelFilesHospitalNowWorkingHours;

    @TableField("personnel_files_professional_certificate_type")
    private String personnelFilesProfessionalCertificateType;

    @TableField("personnel_files_professional_certificate_number")
    private String personnelFilesProfessionalCertificateNumber;

    @TableField("personnel_files_professional_certificate_time")
    private String personnelFilesProfessionalCertificateTime;

    @TableField("personnel_files_first_degree")
    private String personnelFilesFirstDegree;

    @TableField("personnel_files_first_degree_university")
    private String personnelFilesFirstDegreeUniversity;

    @TableField("personnel_files_first_degree_time")
    private String personnelFilesFirstDegreeTime;

    @TableField("personnel_files_second_degree")
    private String personnelFilesSecondDegree;

    @TableField("personnel_files_second_degree_university")
    private String personnelFilesSecondDegreeUniversity;

    @TableField("personnel_files_second_degree_time")
    private String personnelFilesSecondDegreeTime;

    @TableField("personnel_files_title")
    private String personnelFilesTitle;

    @TableField("personnel_files_title_time")
    private String personnelFilesTitleTime;

    @TableField("personnel_files_title_appointment_time")
    private String personnelFilesTitleAppointmentTime;

    @TableField("personnel_files_staff_category_id")
    private String personnelFilesStaffCategoryId;

    @TableField("create_time")
    private String createTime;

    @TableField("personnel_files_remarks")
    private String personnelFilesRemarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPersonnelFilesName() {
        return personnelFilesName;
    }

    public void setPersonnelFilesName(String personnelFilesName) {
        this.personnelFilesName = personnelFilesName == null ? null : personnelFilesName.trim();
    }

    public String getPersonnelFilesGender() {
        return personnelFilesGender;
    }

    public void setPersonnelFilesGender(String personnelFilesGender) {
        this.personnelFilesGender = personnelFilesGender == null ? null : personnelFilesGender.trim();
    }

    public String getPersonnelFilesDateBirth() {
        return personnelFilesDateBirth;
    }

    public void setPersonnelFilesDateBirth(String personnelFilesDateBirth) {
        this.personnelFilesDateBirth = personnelFilesDateBirth == null ? null : personnelFilesDateBirth.trim();
    }

    public String getPersonnelFilesNation() {
        return personnelFilesNation;
    }

    public void setPersonnelFilesNation(String personnelFilesNation) {
        this.personnelFilesNation = personnelFilesNation == null ? null : personnelFilesNation.trim();
    }

    public String getPersonnelFilesNativePlace() {
        return personnelFilesNativePlace;
    }

    public void setPersonnelFilesNativePlace(String personnelFilesNativePlace) {
        this.personnelFilesNativePlace = personnelFilesNativePlace == null ? null : personnelFilesNativePlace.trim();
    }

    public String getPersonnelFilesPhotoUrl() {
        return personnelFilesPhotoUrl;
    }

    public void setPersonnelFilesPhotoUrl(String personnelFilesPhotoUrl) {
        this.personnelFilesPhotoUrl = personnelFilesPhotoUrl == null ? null : personnelFilesPhotoUrl.trim();
    }

    public String getPersonnelFilesIdCard() {
        return personnelFilesIdCard;
    }

    public void setPersonnelFilesIdCard(String personnelFilesIdCard) {
        this.personnelFilesIdCard = personnelFilesIdCard == null ? null : personnelFilesIdCard.trim();
    }

    public String getPersonnelFilesPoliticalSituation() {
        return personnelFilesPoliticalSituation;
    }

    public void setPersonnelFilesPoliticalSituation(String personnelFilesPoliticalSituation) {
        this.personnelFilesPoliticalSituation = personnelFilesPoliticalSituation == null ? null : personnelFilesPoliticalSituation.trim();
    }

    public String getPersonnelFilesFristWorkingHours() {
        return personnelFilesFristWorkingHours;
    }

    public void setPersonnelFilesFristWorkingHours(String personnelFilesFristWorkingHours) {
        this.personnelFilesFristWorkingHours = personnelFilesFristWorkingHours == null ? null : personnelFilesFristWorkingHours.trim();
    }

    public String getPersonnelFilesDepartmentId() {
        return personnelFilesDepartmentId;
    }

    public void setPersonnelFilesDepartmentId(String personnelFilesDepartmentId) {
        this.personnelFilesDepartmentId = personnelFilesDepartmentId == null ? null : personnelFilesDepartmentId.trim();
    }

    public String getPersonnelFilesDepartmentCategory() {
        return personnelFilesDepartmentCategory;
    }

    public void setPersonnelFilesDepartmentCategory(String personnelFilesDepartmentCategory) {
        this.personnelFilesDepartmentCategory = personnelFilesDepartmentCategory == null ? null : personnelFilesDepartmentCategory.trim();
    }

    public String getPersonnelFilesHospitalNowWorkingHours() {
        return personnelFilesHospitalNowWorkingHours;
    }

    public void setPersonnelFilesHospitalNowWorkingHours(String personnelFilesHospitalNowWorkingHours) {
        this.personnelFilesHospitalNowWorkingHours = personnelFilesHospitalNowWorkingHours == null ? null : personnelFilesHospitalNowWorkingHours.trim();
    }

    public String getPersonnelFilesProfessionalCertificateType() {
        return personnelFilesProfessionalCertificateType;
    }

    public void setPersonnelFilesProfessionalCertificateType(String personnelFilesProfessionalCertificateType) {
        this.personnelFilesProfessionalCertificateType = personnelFilesProfessionalCertificateType == null ? null : personnelFilesProfessionalCertificateType.trim();
    }

    public String getPersonnelFilesProfessionalCertificateNumber() {
        return personnelFilesProfessionalCertificateNumber;
    }

    public void setPersonnelFilesProfessionalCertificateNumber(String personnelFilesProfessionalCertificateNumber) {
        this.personnelFilesProfessionalCertificateNumber = personnelFilesProfessionalCertificateNumber == null ? null : personnelFilesProfessionalCertificateNumber.trim();
    }

    public String getPersonnelFilesProfessionalCertificateTime() {
        return personnelFilesProfessionalCertificateTime;
    }

    public void setPersonnelFilesProfessionalCertificateTime(String personnelFilesProfessionalCertificateTime) {
        this.personnelFilesProfessionalCertificateTime = personnelFilesProfessionalCertificateTime == null ? null : personnelFilesProfessionalCertificateTime.trim();
    }

    public String getPersonnelFilesFirstDegree() {
        return personnelFilesFirstDegree;
    }

    public void setPersonnelFilesFirstDegree(String personnelFilesFirstDegree) {
        this.personnelFilesFirstDegree = personnelFilesFirstDegree == null ? null : personnelFilesFirstDegree.trim();
    }

    public String getPersonnelFilesFirstDegreeUniversity() {
        return personnelFilesFirstDegreeUniversity;
    }

    public void setPersonnelFilesFirstDegreeUniversity(String personnelFilesFirstDegreeUniversity) {
        this.personnelFilesFirstDegreeUniversity = personnelFilesFirstDegreeUniversity == null ? null : personnelFilesFirstDegreeUniversity.trim();
    }

    public String getPersonnelFilesFirstDegreeTime() {
        return personnelFilesFirstDegreeTime;
    }

    public void setPersonnelFilesFirstDegreeTime(String personnelFilesFirstDegreeTime) {
        this.personnelFilesFirstDegreeTime = personnelFilesFirstDegreeTime == null ? null : personnelFilesFirstDegreeTime.trim();
    }

    public String getPersonnelFilesSecondDegree() {
        return personnelFilesSecondDegree;
    }

    public void setPersonnelFilesSecondDegree(String personnelFilesSecondDegree) {
        this.personnelFilesSecondDegree = personnelFilesSecondDegree == null ? null : personnelFilesSecondDegree.trim();
    }

    public String getPersonnelFilesSecondDegreeUniversity() {
        return personnelFilesSecondDegreeUniversity;
    }

    public void setPersonnelFilesSecondDegreeUniversity(String personnelFilesSecondDegreeUniversity) {
        this.personnelFilesSecondDegreeUniversity = personnelFilesSecondDegreeUniversity == null ? null : personnelFilesSecondDegreeUniversity.trim();
    }

    public String getPersonnelFilesSecondDegreeTime() {
        return personnelFilesSecondDegreeTime;
    }

    public void setPersonnelFilesSecondDegreeTime(String personnelFilesSecondDegreeTime) {
        this.personnelFilesSecondDegreeTime = personnelFilesSecondDegreeTime == null ? null : personnelFilesSecondDegreeTime.trim();
    }

    public String getPersonnelFilesTitle() {
        return personnelFilesTitle;
    }

    public void setPersonnelFilesTitle(String personnelFilesTitle) {
        this.personnelFilesTitle = personnelFilesTitle == null ? null : personnelFilesTitle.trim();
    }

    public String getPersonnelFilesTitleTime() {
        return personnelFilesTitleTime;
    }

    public void setPersonnelFilesTitleTime(String personnelFilesTitleTime) {
        this.personnelFilesTitleTime = personnelFilesTitleTime == null ? null : personnelFilesTitleTime.trim();
    }

    public String getPersonnelFilesTitleAppointmentTime() {
        return personnelFilesTitleAppointmentTime;
    }

    public void setPersonnelFilesTitleAppointmentTime(String personnelFilesTitleAppointmentTime) {
        this.personnelFilesTitleAppointmentTime = personnelFilesTitleAppointmentTime == null ? null : personnelFilesTitleAppointmentTime.trim();
    }

    public String getPersonnelFilesStaffCategoryId() {
        return personnelFilesStaffCategoryId;
    }

    public void setPersonnelFilesStaffCategoryId(String personnelFilesStaffCategoryId) {
        this.personnelFilesStaffCategoryId = personnelFilesStaffCategoryId == null ? null : personnelFilesStaffCategoryId.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getPersonnelFilesRemarks() {
        return personnelFilesRemarks;
    }

    public void setPersonnelFilesRemarks(String personnelFilesRemarks) {
        this.personnelFilesRemarks = personnelFilesRemarks == null ? null : personnelFilesRemarks.trim();
    }
}