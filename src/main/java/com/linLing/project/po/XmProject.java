package com.linLing.project.po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "xm_project", schema = "linling", catalog = "")
public class XmProject {
    private Integer id;
    private String projectCode;
    private String projectName;
    private String projectYear;
    private Integer projectField;
    private Integer projectStudentId;
    private String projectStudentName;
    private Integer projectTeacherId;
    private String projectTeacherName;
    private String projectFunds;
    private String projectIntroduce;
    private String projectRemark;
    private String acceptanceOpinion;
    private Timestamp createDate;
    private String projectState;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "project_code")
    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    @Basic
    @Column(name = "project_name")
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "project_year")
    public String getProjectYear() {
        return projectYear;
    }

    public void setProjectYear(String projectYear) {
        this.projectYear = projectYear;
    }

    @Basic
    @Column(name = "project_field")
    public Integer getProjectField() {
        return projectField;
    }

    public void setProjectField(Integer projectField) {
        this.projectField = projectField;
    }

    @Basic
    @Column(name = "project_student_id")
    public Integer getProjectStudentId() {
        return projectStudentId;
    }

    public void setProjectStudentId(Integer projectStudentId) {
        this.projectStudentId = projectStudentId;
    }

    @Basic
    @Column(name = "project_student_name")
    public String getProjectStudentName() {
        return projectStudentName;
    }

    public void setProjectStudentName(String projectStudentName) {
        this.projectStudentName = projectStudentName;
    }

    @Basic
    @Column(name = "project_teacher_id")
    public Integer getProjectTeacherId() {
        return projectTeacherId;
    }

    public void setProjectTeacherId(Integer projectTeacherId) {
        this.projectTeacherId = projectTeacherId;
    }

    @Basic
    @Column(name = "project_teacher_name")
    public String getProjectTeacherName() {
        return projectTeacherName;
    }

    public void setProjectTeacherName(String projectTeacherName) {
        this.projectTeacherName = projectTeacherName;
    }

    @Basic
    @Column(name = "project_funds")
    public String getProjectFunds() {
        return projectFunds;
    }

    public void setProjectFunds(String projectFunds) {
        this.projectFunds = projectFunds;
    }

    @Basic
    @Column(name = "project_introduce")
    public String getProjectIntroduce() {
        return projectIntroduce;
    }

    public void setProjectIntroduce(String projectIntroduce) {
        this.projectIntroduce = projectIntroduce;
    }

    @Basic
    @Column(name = "project_remark")
    public String getProjectRemark() {
        return projectRemark;
    }

    public void setProjectRemark(String projectRemark) {
        this.projectRemark = projectRemark;
    }

    @Basic
    @Column(name = "acceptance_opinion")
    public String getAcceptanceOpinion() {
        return acceptanceOpinion;
    }

    public void setAcceptanceOpinion(String acceptanceOpinion) {
        this.acceptanceOpinion = acceptanceOpinion;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "project_state")
    public String getProjectState() {
        return projectState;
    }

    public void setProjectState(String projectState) {
        this.projectState = projectState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XmProject that = (XmProject) o;
        return id == that.id &&
                Objects.equals(projectCode, that.projectCode) &&
                Objects.equals(projectName, that.projectName) &&
                Objects.equals(projectYear, that.projectYear) &&
                Objects.equals(projectField, that.projectField) &&
                Objects.equals(projectStudentId, that.projectStudentId) &&
                Objects.equals(projectStudentName, that.projectStudentName) &&
                Objects.equals(projectTeacherId, that.projectTeacherId) &&
                Objects.equals(projectTeacherName, that.projectTeacherName) &&
                Objects.equals(projectFunds, that.projectFunds) &&
                Objects.equals(projectIntroduce, that.projectIntroduce) &&
                Objects.equals(projectRemark, that.projectRemark) &&
                Objects.equals(acceptanceOpinion, that.acceptanceOpinion) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(projectState, that.projectState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectCode, projectName, projectYear, projectField, projectStudentId, projectStudentName, projectTeacherId, projectTeacherName, projectFunds, projectIntroduce, projectRemark, acceptanceOpinion, createDate , projectState);
    }
}
