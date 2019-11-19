package com.weisen.www.code.yjf.shopmall.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A V2OrderExtends.
 */
@Entity
@Table(name = "v_2_order_extends")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class V2OrderExtends implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jhi_oid")
    private Long oid;

    @Column(name = "paid_amount")
    private Float paidAmount;

    @Column(name = "paid_method")
    private String paidMethod;

    @Column(name = "paid_time")
    private String paidTime;

    @Column(name = "express_state")
    private String expressState;

    @Column(name = "express_time")
    private String expressTime;

    @Column(name = "express_company")
    private String expressCompany;

    @Column(name = "express_no")
    private String expressNo;

    @Column(name = "express_remark")
    private String expressRemark;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOid() {
        return oid;
    }

    public V2OrderExtends oid(Long oid) {
        this.oid = oid;
        return this;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public Float getPaidAmount() {
        return paidAmount;
    }

    public V2OrderExtends paidAmount(Float paidAmount) {
        this.paidAmount = paidAmount;
        return this;
    }

    public void setPaidAmount(Float paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getPaidMethod() {
        return paidMethod;
    }

    public V2OrderExtends paidMethod(String paidMethod) {
        this.paidMethod = paidMethod;
        return this;
    }

    public void setPaidMethod(String paidMethod) {
        this.paidMethod = paidMethod;
    }

    public String getPaidTime() {
        return paidTime;
    }

    public V2OrderExtends paidTime(String paidTime) {
        this.paidTime = paidTime;
        return this;
    }

    public void setPaidTime(String paidTime) {
        this.paidTime = paidTime;
    }

    public String getExpressState() {
        return expressState;
    }

    public V2OrderExtends expressState(String expressState) {
        this.expressState = expressState;
        return this;
    }

    public void setExpressState(String expressState) {
        this.expressState = expressState;
    }

    public String getExpressTime() {
        return expressTime;
    }

    public V2OrderExtends expressTime(String expressTime) {
        this.expressTime = expressTime;
        return this;
    }

    public void setExpressTime(String expressTime) {
        this.expressTime = expressTime;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public V2OrderExtends expressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
        return this;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public V2OrderExtends expressNo(String expressNo) {
        this.expressNo = expressNo;
        return this;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getExpressRemark() {
        return expressRemark;
    }

    public V2OrderExtends expressRemark(String expressRemark) {
        this.expressRemark = expressRemark;
        return this;
    }

    public void setExpressRemark(String expressRemark) {
        this.expressRemark = expressRemark;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof V2OrderExtends)) {
            return false;
        }
        return id != null && id.equals(((V2OrderExtends) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "V2OrderExtends{" +
            "id=" + getId() +
            ", oid=" + getOid() +
            ", paidAmount=" + getPaidAmount() +
            ", paidMethod='" + getPaidMethod() + "'" +
            ", paidTime='" + getPaidTime() + "'" +
            ", expressState='" + getExpressState() + "'" +
            ", expressTime='" + getExpressTime() + "'" +
            ", expressCompany='" + getExpressCompany() + "'" +
            ", expressNo='" + getExpressNo() + "'" +
            ", expressRemark='" + getExpressRemark() + "'" +
            "}";
    }
}
