package com.linLing.project.po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "xm_project_process", schema = "linling", catalog = "")
public class XmProjectProcess {
    private int id;
    private Integer projectId;
    private Integer userId;
    private String userName;
    private String processRecord;
    private Timestamp recordDate;
    private String recordType;
    private String recordState;

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
    @Column(name = "process_record", nullable = true, length = 255)
    public String getProcessRecord() {
        return processRecord;
    }

    public void setProcessRecord(String processRecord) {
        this.processRecord = processRecord;
    }

    @Basic
    @Column(name = "record_date", nullable = true)
    public Timestamp getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Timestamp recordDate) {
        this.recordDate = recordDate;
    }

    @Basic
    @Column(name = "record_type", nullable = true, length = 255)
    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    @Basic
    @Column(name = "record_state", nullable = true, length = 1)
    public String getRecordState() {
        return recordState;
    }

    public void setRecordState(String recordState) {
        this.recordState = recordState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XmProjectProcess that = (XmProjectProcess) o;
        return id == that.id &&
                Objects.equals(projectId, that.projectId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(processRecord, that.processRecord) &&
                Objects.equals(recordDate, that.recordDate) &&
                Objects.equals(recordType, that.recordType) &&
                Objects.equals(recordState, that.recordState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, userId, userName, processRecord, recordDate, recordType, recordState);
    }
}

