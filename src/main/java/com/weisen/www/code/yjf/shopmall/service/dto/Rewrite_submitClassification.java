package com.weisen.www.code.yjf.shopmall.service.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Rewrite_submitClassification implements Serializable {
    private String name;
    private String superior;
    private String creator;
    private String order;
    private String modifier;
    private String other;
    private Long ClassificationId;

    public Long getClassificationId() {
        return ClassificationId;
    }

    public void setClassificationId(Long classificationId) {
        ClassificationId = classificationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuperior() {
        return superior;
    }

    public void setSuperior(String superior) {
        this.superior = superior;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
