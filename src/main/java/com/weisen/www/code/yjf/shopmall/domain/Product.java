package com.weisen.www.code.yjf.shopmall.domain;

import javax.persistence.Column;
import java.io.Serializable;

public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "id")
    private Long id;
    @Column(name = "commodityid")
    private Long commodityid;
    @Column(name = "model")
    private String model;
    @Column(name = "specifications")
    private String specifications;
    @Column(name = "price")
    private String price;
    @Column(name = "discount")
    private String discount;
    @Column(name = "content")
    private String content;

    public Long getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(Long commodityid) {
        this.commodityid = commodityid;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
