package com.weisen.www.code.yjf.shopmall.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Order.
 */
@Entity
@Table(name = "jhi_order")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ordernum")
    private String ordernum;

    @Column(name = "state")
    private String state;

    @Column(name = "userid")
    private String userid;

    @Column(name = "commodityid")
    private String commodityid;

    @Column(name = "specificationsid")
    private String specificationsid;

    @Column(name = "num")
    private String num;

    @Column(name = "paymethod")
    private String paymethod;

    @Column(name = "payresult")
    private String payresult;

    @Column(name = "creator")
    private String creator;

    @Column(name = "createdate")
    private String createdate;

    @Column(name = "modifier")
    private String modifier;

    @Column(name = "modifierdate")
    private String modifierdate;

    @Column(name = "modifiernum")
    private Long modifiernum;

    @Column(name = "logicdelete")
    private Boolean logicdelete;

    @Column(name = "other")
    private String other;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public Order ordernum(String ordernum) {
        this.ordernum = ordernum;
        return this;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getState() {
        return state;
    }

    public Order state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserid() {
        return userid;
    }

    public Order userid(String userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCommodityid() {
        return commodityid;
    }

    public Order commodityid(String commodityid) {
        this.commodityid = commodityid;
        return this;
    }

    public void setCommodityid(String commodityid) {
        this.commodityid = commodityid;
    }

    public String getSpecificationsid() {
        return specificationsid;
    }

    public Order specificationsid(String specificationsid) {
        this.specificationsid = specificationsid;
        return this;
    }

    public void setSpecificationsid(String specificationsid) {
        this.specificationsid = specificationsid;
    }

    public String getNum() {
        return num;
    }

    public Order num(String num) {
        this.num = num;
        return this;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public Order paymethod(String paymethod) {
        this.paymethod = paymethod;
        return this;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    public String getPayresult() {
        return payresult;
    }

    public Order payresult(String payresult) {
        this.payresult = payresult;
        return this;
    }

    public void setPayresult(String payresult) {
        this.payresult = payresult;
    }

    public String getCreator() {
        return creator;
    }

    public Order creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatedate() {
        return createdate;
    }

    public Order createdate(String createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public Order modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierdate() {
        return modifierdate;
    }

    public Order modifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
        return this;
    }

    public void setModifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
    }

    public Long getModifiernum() {
        return modifiernum;
    }

    public Order modifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
        return this;
    }

    public void setModifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
    }

    public Boolean isLogicdelete() {
        return logicdelete;
    }

    public Order logicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
        return this;
    }

    public void setLogicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
    }

    public String getOther() {
        return other;
    }

    public Order other(String other) {
        this.other = other;
        return this;
    }

    public void setOther(String other) {
        this.other = other;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        return id != null && id.equals(((Order) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Order{" +
            "id=" + getId() +
            ", ordernum='" + getOrdernum() + "'" +
            ", state='" + getState() + "'" +
            ", userid='" + getUserid() + "'" +
            ", commodityid='" + getCommodityid() + "'" +
            ", specificationsid='" + getSpecificationsid() + "'" +
            ", num='" + getNum() + "'" +
            ", paymethod='" + getPaymethod() + "'" +
            ", payresult='" + getPayresult() + "'" +
            ", creator='" + getCreator() + "'" +
            ", createdate='" + getCreatedate() + "'" +
            ", modifier='" + getModifier() + "'" +
            ", modifierdate='" + getModifierdate() + "'" +
            ", modifiernum=" + getModifiernum() +
            ", logicdelete='" + isLogicdelete() + "'" +
            ", other='" + getOther() + "'" +
            "}";
    }
}
