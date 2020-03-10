package com.linLing.project.po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "xm_project_examine", schema = "linling", catalog = "")
public class XmProjectExamine {
    private int id;
    private Integer projectId;
    private Integer userId;
    private String userName;
    private Double examineFraction;
    private String examineOpinion;
    private Timestamp examineDate;
    private String examineState;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "project_id", nullable = true)
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name", nullable = true, length = 255)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "examine_fraction", nullable = true, precision = 0)
    public Double getExamineFraction() {
        return examineFraction;
    }

    public void setExamineFraction(Double examineFraction) {
        this.examineFraction = examineFraction;
    }

    @Basic
    @Column(name = "examine_opinion", nullable = true, length = 255)
    public String getExamineOpinion() {
        return examineOpinion;
    }

    public void setExamineOpinion(String examineOpinion) {
        this.examineOpinion = examineOpinion;
    }

    @Basic
    @Column(name = "examine_date", nullable = true)
    public Timestamp getExamineDate() {
        return examineDate;
    }

    public void setExamineDate(Timestamp examineDate) {
        this.examineDate = examineDate;
    }

    @Basic
    @Column(name = "examine_state", nullable = true, length = 1)
    public String getExamineState() {
        return examineState;
    }

    public void setExamineState(String examineState) {
        this.examineState = examineState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XmProjectExamine that = (XmProjectExamine) o;
        return id == that.id &&
                Objects.equals(projectId, that.projectId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(examineFraction, that.examineFraction) &&
                Objects.equals(examineOpinion, that.examineOpinion) &&
                Objects.equals(examineDate, that.examineDate) &&
                Objects.equals(examineState, that.examineState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, userId, userName, examineFraction, examineOpinion, examineDate, examineState);
    }
}

