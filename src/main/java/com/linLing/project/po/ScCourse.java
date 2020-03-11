package com.linLing.project.po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "sc_course", schema = "linling", catalog = "")
public class ScCourse {
    private Integer id;
    private String courseName;
    private Integer userId;
    private String userName;
    private Timestamp courseDate;
    private String coursePlace;
    private String courseContent;
    private String courseRemark;
    private String courseState;

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
    @Column(name = "course_name", nullable = true, length = 255)
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
    @Column(name = "course_date", nullable = true)
    public Timestamp getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(Timestamp courseDate) {
        this.courseDate = courseDate;
    }

    @Basic
    @Column(name = "course_place", nullable = true, length = 255)
    public String getCoursePlace() {
        return coursePlace;
    }

    public void setCoursePlace(String coursePlace) {
        this.coursePlace = coursePlace;
    }

    @Basic
    @Column(name = "course_content", nullable = true, length = 255)
    public String getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent;
    }

    @Basic
    @Column(name = "course_remark", nullable = true, length = 255)
    public String getCourseRemark() {
        return courseRemark;
    }

    public void setCourseRemark(String courseRemark) {
        this.courseRemark = courseRemark;
    }

    @Basic
    @Column(name = "course_state", nullable = true, length = 1)
    public String getCourseState() {
        return courseState;
    }

    public void setCourseState(String courseState) {
        this.courseState = courseState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScCourse scCourse = (ScCourse) o;
        return id == scCourse.id &&
                Objects.equals(courseName, scCourse.courseName) &&
                Objects.equals(userId, scCourse.userId) &&
                Objects.equals(userName, scCourse.userName) &&
                Objects.equals(courseDate, scCourse.courseDate) &&
                Objects.equals(coursePlace, scCourse.coursePlace) &&
                Objects.equals(courseContent, scCourse.courseContent) &&
                Objects.equals(courseRemark, scCourse.courseRemark) &&
                Objects.equals(courseState, scCourse.courseState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, userId, userName, courseDate, coursePlace, courseContent, courseRemark, courseState);
    }
}

