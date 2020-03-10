package com.linLing.project.po;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sc_policy", schema = "linling", catalog = "")
public class ScPolicy {
    private int id;
    private String policyName;
    private String releaseBm;
    private String policyContent;
    private String policyRemark;
    private String policyState;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "policy_name", nullable = true, length = 255)
    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    @Basic
    @Column(name = "release_bm", nullable = true, length = 255)
    public String getReleaseBm() {
        return releaseBm;
    }

    public void setReleaseBm(String releaseBm) {
        this.releaseBm = releaseBm;
    }

    @Basic
    @Column(name = "policy_content", nullable = true, length = 255)
    public String getPolicyContent() {
        return policyContent;
    }

    public void setPolicyContent(String policyContent) {
        this.policyContent = policyContent;
    }

    @Basic
    @Column(name = "policy_remark", nullable = true, length = 255)
    public String getPolicyRemark() {
        return policyRemark;
    }

    public void setPolicyRemark(String policyRemark) {
        this.policyRemark = policyRemark;
    }

    @Basic
    @Column(name = "policy_state", nullable = true, length = 1)
    public String getPolicyState() {
        return policyState;
    }

    public void setPolicyState(String policyState) {
        this.policyState = policyState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScPolicy scPolicy = (ScPolicy) o;
        return id == scPolicy.id &&
                Objects.equals(policyName, scPolicy.policyName) &&
                Objects.equals(releaseBm, scPolicy.releaseBm) &&
                Objects.equals(policyContent, scPolicy.policyContent) &&
                Objects.equals(policyRemark, scPolicy.policyRemark) &&
                Objects.equals(policyState, scPolicy.policyState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, policyName, releaseBm, policyContent, policyRemark, policyState);
    }
}

