package com.weisen.www.code.yjf.shopmall.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Commodity.
 */
@Entity
@Table(name = "commodity")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Commodity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "sub_title")
    private String sub_title;

    @Column(name = "store_alert")
    private String storeAlert;

    @Column(name = "jhi_quota")
    private Integer quota;

    @Column(name = "brandid")
    private String brandid;

    @Column(name = "classificationid")
    private String classificationid;

    @Column(name = "commoditystate")
    private String commoditystate;

    @Column(name = "postage")
    private String postage;

    @Column(name = "salevalue")
    private String salevalue;

    @Column(name = "weight")
    private String weight;

    @Column(name = "weight_2")
    private String weight2;

    @Column(name = "weight_3")
    private String weight3;

    @Column(name = "feature")
    private String feature;

    @Column(name = "services")
    private String services;

    @Column(name = "remark")
    private String remark;

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

    public String getName() {
        return name;
    }

    public Commodity name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSub_title() {
        return sub_title;
    }

    public Commodity sub_title(String sub_title) {
        this.sub_title = sub_title;
        return this;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getStoreAlert() {
        return storeAlert;
    }

    public Commodity storeAlert(String storeAlert) {
        this.storeAlert = storeAlert;
        return this;
    }

    public void setStoreAlert(String storeAlert) {
        this.storeAlert = storeAlert;
    }

    public Integer getQuota() {
        return quota;
    }

    public Commodity quota(Integer quota) {
        this.quota = quota;
        return this;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public String getBrandid() {
        return brandid;
    }

    public Commodity brandid(String brandid) {
        this.brandid = brandid;
        return this;
    }

    public void setBrandid(String brandid) {
        this.brandid = brandid;
    }

    public String getClassificationid() {
        return classificationid;
    }

    public Commodity classificationid(String classificationid) {
        this.classificationid = classificationid;
        return this;
    }

    public void setClassificationid(String classificationid) {
        this.classificationid = classificationid;
    }

    public String getCommoditystate() {
        return commoditystate;
    }

    public Commodity commoditystate(String commoditystate) {
        this.commoditystate = commoditystate;
        return this;
    }

    public void setCommoditystate(String commoditystate) {
        this.commoditystate = commoditystate;
    }

    public String getPostage() {
        return postage;
    }

    public Commodity postage(String postage) {
        this.postage = postage;
        return this;
    }

    public void setPostage(String postage) {
        this.postage = postage;
    }

    public String getSalevalue() {
        return salevalue;
    }

    public Commodity salevalue(String salevalue) {
        this.salevalue = salevalue;
        return this;
    }

    public void setSalevalue(String salevalue) {
        this.salevalue = salevalue;
    }

    public String getWeight() {
        return weight;
    }

    public Commodity weight(String weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeight2() {
        return weight2;
    }

    public Commodity weight2(String weight2) {
        this.weight2 = weight2;
        return this;
    }

    public void setWeight2(String weight2) {
        this.weight2 = weight2;
    }

    public String getWeight3() {
        return weight3;
    }

    public Commodity weight3(String weight3) {
        this.weight3 = weight3;
        return this;
    }

    public void setWeight3(String weight3) {
        this.weight3 = weight3;
    }

    public String getFeature() {
        return feature;
    }

    public Commodity feature(String feature) {
        this.feature = feature;
        return this;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getServices() {
        return services;
    }

    public Commodity services(String services) {
        this.services = services;
        return this;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getRemark() {
        return remark;
    }

    public Commodity remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreator() {
        return creator;
    }

    public Commodity creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatedate() {
        return createdate;
    }

    public Commodity createdate(String createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getModifier() {
        return modifier;
    }

    public Commodity modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierdate() {
        return modifierdate;
    }

    public Commodity modifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
        return this;
    }

    public void setModifierdate(String modifierdate) {
        this.modifierdate = modifierdate;
    }

    public Long getModifiernum() {
        return modifiernum;
    }

    public Commodity modifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
        return this;
    }

    public void setModifiernum(Long modifiernum) {
        this.modifiernum = modifiernum;
    }

    public Boolean isLogicdelete() {
        return logicdelete;
    }

    public Commodity logicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
        return this;
    }

    public void setLogicdelete(Boolean logicdelete) {
        this.logicdelete = logicdelete;
    }

    public String getOther() {
        return other;
    }

    public Commodity other(String other) {
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
        if (!(o instanceof Commodity)) {
            return false;
        }
        return id != null && id.equals(((Commodity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Commodity{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sub_title='" + getSub_title() + "'" +
            ", storeAlert='" + getStoreAlert() + "'" +
            ", quota=" + getQuota() +
            ", brandid='" + getBrandid() + "'" +
            ", classificationid='" + getClassificationid() + "'" +
            ", commoditystate='" + getCommoditystate() + "'" +
            ", postage='" + getPostage() + "'" +
            ", salevalue='" + getSalevalue() + "'" +
            ", weight='" + getWeight() + "'" +
            ", weight2='" + getWeight2() + "'" +
            ", weight3='" + getWeight3() + "'" +
            ", feature='" + getFeature() + "'" +
            ", services='" + getServices() + "'" +
            ", remark='" + getRemark() + "'" +
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
