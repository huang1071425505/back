package com.linLing.project.po;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "xm_project", schema = "linling", catalog = "")
public class XmProject {
    private Integer id;
    private String projectCode;
    private String projectName;
    private String projectYear;
    private String projectField;
    private String projectStudentId;
    private String projectStudentName;
    private String projectTeacherId;
    private String projectTeacherName;
    private String projectFunds;
    private String projectIntroduce;
    private String projectRemark;
    private String acceptanceOpinion;
    private String projectState;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "project_code", nullable = true, length = 255)
    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    @Basic
    @Column(name = "project_name", nullable = true, length = 255)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "project_year", nullable = true, length = 255)
    public String getProjectYear() {
        return projectYear;
    }

    public void setProjectYear(String projectYear) {
        this.projectYear = projectYear;
    }

    @Basic
    @Column(name = "project_field", nullable = true, length = 255)
    public String getProjectField() {
        return projectField;
    }

    public void setProjectField(String projectField) {
        this.projectField = projectField;
    }

    @Basic
    @Column(name = "project_student_id", nullable = true, length = 255)
    public String getProjectStudentId() {
        return projectStudentId;
    }

    public void setProjectStudentId(String projectStudentId) {
        this.projectStudentId = projectStudentId;
    }

    @Basic
    @Column(name = "project_student_name", nullable = true, length = 255)
    public String getProjectStudentName() {
        return projectStudentName;
    }

    public void setProjectStudentName(String projectStudentName) {
        this.projectStudentName = projectStudentName;
    }

    @Basic
    @Column(name = "project_teacher_id", nullable = true, length = 255)
    public String getProjectTeacherId() {
        return projectTeacherId;
    }

    public void setProjectTeacherId(String projectTeacherId) {
        this.projectTeacherId = projectTeacherId;
    }

    @Basic
    @Column(name = "project_teacher_name", nullable = true, length = 255)
    public String getProjectTeacherName() {
        return projectTeacherName;
    }

    public void setProjectTeacherName(String projectTeacherName) {
        this.projectTeacherName = projectTeacherName;
    }

    @Basic
    @Column(name = "project_funds", nullable = true, length = 255)
    public String getProjectFunds() {
        return projectFunds;
    }

    public void setProjectFunds(String projectFunds) {
        this.projectFunds = projectFunds;
    }

    @Basic
    @Column(name = "project_introduce", nullable = true, length = 255)
    public String getProjectIntroduce() {
        return projectIntroduce;
    }

    public void setProjectIntroduce(String projectIntroduce) {
        this.projectIntroduce = projectIntroduce;
    }

    @Basic
    @Column(name = "project_remark", nullable = true, length = 255)
    public String getProjectRemark() {
        return projectRemark;
    }

    public void setProjectRemark(String projectRemark) {
        this.projectRemark = projectRemark;
    }

    @Basic
    @Column(name = "acceptance_opinion", nullable = true, length = 255)
    public String getAcceptanceOpinion() {
        return acceptanceOpinion;
    }

    public void setAcceptanceOpinion(String acceptanceOpinion) {
        this.acceptanceOpinion = acceptanceOpinion;
    }

    @Basic
    @Column(name = "project_state", nullable = true, length = 1)
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
        XmProject xmProject = (XmProject) o;
        return id == xmProject.id &&
                Objects.equals(projectCode, xmProject.projectCode) &&
                Objects.equals(projectName, xmProject.projectName) &&
                Objects.equals(projectYear, xmProject.projectYear) &&
                Objects.equals(projectField, xmProject.projectField) &&
                Objects.equals(projectStudentId, xmProject.projectStudentId) &&
                Objects.equals(projectStudentName, xmProject.projectStudentName) &&
                Objects.equals(projectTeacherId, xmProject.projectTeacherId) &&
                Objects.equals(projectTeacherName, xmProject.projectTeacherName) &&
                Objects.equals(projectFunds, xmProject.projectFunds) &&
                Objects.equals(projectIntroduce, xmProject.projectIntroduce) &&
                Objects.equals(projectRemark, xmProject.projectRemark) &&
                Objects.equals(acceptanceOpinion, xmProject.acceptanceOpinion) &&
                Objects.equals(projectState, xmProject.projectState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectCode, projectName, projectYear, projectField, projectStudentId, projectStudentName, projectTeacherId, projectTeacherName, projectFunds, projectIntroduce, projectRemark, acceptanceOpinion, projectState);
    }
}

