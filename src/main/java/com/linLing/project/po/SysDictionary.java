package com.linLing.project.po;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_dictionary", schema = "linling", catalog = "")
public class SysDictionary {
    private Integer id;
    private String dicCode;
    private String dicName;
    private String dicValue;
    private String dicGroup;
    private Integer dicPid;
    private Integer dicOrder;
    private String dicState;

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
    @Column(name = "dic_code")
    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    @Basic
    @Column(name = "dic_name")
    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    @Basic
    @Column(name = "dic_value")
    public String getDicValue() {
        return dicValue;
    }

    public void setDicValue(String dicValue) {
        this.dicValue = dicValue;
    }

    @Basic
    @Column(name = "dic_group")
    public String getDicGroup() {
        return dicGroup;
    }

    public void setDicGroup(String dicGroup) {
        this.dicGroup = dicGroup;
    }

    @Basic
    @Column(name = "dic_pid")
    public Integer getDicPid() {
        return dicPid;
    }

    public void setDicPid(Integer dicPid) {
        this.dicPid = dicPid;
    }

    @Basic
    @Column(name = "dic_order")
    public Integer getDicOrder() {
        return dicOrder;
    }

    public void setDicOrder(Integer dicOrder) {
        this.dicOrder = dicOrder;
    }

    @Basic
    @Column(name = "dic_state")
    public String getDicState() {
        return dicState;
    }

    public void setDicState(String dicState) {
        this.dicState = dicState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysDictionary that = (SysDictionary) o;
        return id == that.id &&
                Objects.equals(dicCode, that.dicCode) &&
                Objects.equals(dicName, that.dicName) &&
                Objects.equals(dicValue, that.dicValue) &&
                Objects.equals(dicGroup, that.dicGroup) &&
                Objects.equals(dicPid, that.dicPid) &&
                Objects.equals(dicOrder, that.dicOrder) &&
                Objects.equals(dicState, that.dicState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dicCode , dicName, dicValue, dicGroup, dicPid, dicOrder, dicState);
    }
}
