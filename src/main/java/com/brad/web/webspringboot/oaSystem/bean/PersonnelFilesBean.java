package com.brad.web.webspringboot.oaSystem.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

@Data
@TableName("oa_hospital_personnel_files")
@Excel("人事档案")
public class PersonnelFilesBean {

    @TableId(value = "ID")
    private String id;

    @ExcelField(value = "姓名")
    @TableField("personnel_files_name")
    private String personnelFilesName;


    @ExcelField(value = "性别")
    private String personnelFilesGenderName; //性别


    @TableField("personnel_files_gender")
    private String personnelFilesGender;

    @ExcelField(value = "出生日期")
    @TableField("personnel_files_date_birth")
    private String personnelFilesDateBirth;

    @ExcelField(value = "民族")
    @TableField("personnel_files_nation")
    private String personnelFilesNation;

    @ExcelField(value = "籍贯")
    @TableField("personnel_files_native_place")
    private String personnelFilesNativePlace;

    @TableField("personnel_files_photo_url")
    private String personnelFilesPhotoUrl;

    @ExcelField(value = "身份证号码")
    @TableField("personnel_files_id_card")
    private String personnelFilesIdCard;

    @ExcelField(value = "政治面貌")
    @TableField("personnel_files_political_situation")
    private String personnelFilesPoliticalSituation;

    @ExcelField(value = "参加工作时间")
    @TableField("personnel_files_frist_working_hours")
    private String personnelFilesFristWorkingHours;

    @ExcelField(value = "所属部门")
    private String departmentName; //部门

    @TableField("personnel_files_department_id")
    private String personnelFilesDepartmentId;

    @ExcelField(value = "所属科室类别")
    @TableField("personnel_files_department_category")
    private String personnelFilesDepartmentCategory;

    @ExcelField(value = "联系电话")
    @TableField("personnel_files_hospital_now_working_hours")
    private String personnelFilesHospitalNowWorkingHours;

    @ExcelField(value = "职业证书类别")
    @TableField("personnel_files_professional_certificate_type")
    private String personnelFilesProfessionalCertificateType;

    @ExcelField(value = "职业证书编号")
    @TableField("personnel_files_professional_certificate_number")
    private String personnelFilesProfessionalCertificateNumber;

    @ExcelField(value = "职业证书取得时间")
    @TableField("personnel_files_professional_certificate_time")
    private String personnelFilesProfessionalCertificateTime;


    @ExcelField(value = "第一学历")
    private String personnelFilesFirstDegreeName; //第一学历

    @TableField("personnel_files_first_degree")
    private String personnelFilesFirstDegree;

    @ExcelField(value = "第一学历毕业院校")
    @TableField("personnel_files_first_degree_university")
    private String personnelFilesFirstDegreeUniversity;

    @ExcelField(value = "第一学历毕业时间")
    @TableField("personnel_files_first_degree_time")
    private String personnelFilesFirstDegreeTime;

    @ExcelField(value = "第二学历")
    private String personnelFilesSecondDegreeName;//第二学历

    @TableField("personnel_files_second_degree")
    private String personnelFilesSecondDegree;

    @ExcelField(value = "第二学历毕业院校")
    @TableField("personnel_files_second_degree_university")
    private String personnelFilesSecondDegreeUniversity;

    @ExcelField(value = "第二学历毕业时间")
    @TableField("personnel_files_second_degree_time")
    private String personnelFilesSecondDegreeTime;

    @ExcelField(value = "职称")
    @TableField("personnel_files_title")
    private String personnelFilesTitle;

    @ExcelField(value = "职称取得时间")
    @TableField("personnel_files_title_time")
    private String personnelFilesTitleTime;

    @ExcelField(value = "聘任时间")
    @TableField("personnel_files_title_appointment_time")
    private String personnelFilesTitleAppointmentTime;

    @ExcelField(value = "职工类别")
    @TableField("personnel_files_name")
    private String personnelFilesStaffCategoryName;//职工类别

    @TableField("personnel_files_staff_category_id")
    private String personnelFilesStaffCategoryId;

    @ExcelField(value = "创建时间")
    @TableField("create_time")
    private String createTime;

    @ExcelField(value = "备注信息")
    @TableField("personnel_files_remarks")
    private String personnelFilesRemarks;







    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPersonnelFilesGenderName() {
        return personnelFilesGenderName;
    }

    public void setPersonnelFilesGenderName(String personnelFilesGenderName) {
        this.personnelFilesGenderName = personnelFilesGenderName;
    }

    public String getPersonnelFilesFirstDegreeName() {
        return personnelFilesFirstDegreeName;
    }

    public void setPersonnelFilesFirstDegreeName(String personnelFilesFirstDegreeName) {
        this.personnelFilesFirstDegreeName = personnelFilesFirstDegreeName;
    }

    public String getPersonnelFilesSecondDegreeName() {
        return personnelFilesSecondDegreeName;
    }

    public void setPersonnelFilesSecondDegreeName(String personnelFilesSecondDegreeName) {
        this.personnelFilesSecondDegreeName = personnelFilesSecondDegreeName;
    }

    public String getPersonnelFilesStaffCategoryName() {
        return personnelFilesStaffCategoryName;
    }

    public void setPersonnelFilesStaffCategoryName(String personnelFilesStaffCategoryName) {
        this.personnelFilesStaffCategoryName = personnelFilesStaffCategoryName;
    }

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