package com.weisen.www.code.yjf.shopmall.service.dto;

import java.io.Serializable;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/29 11:45
 */
@SuppressWarnings("serial")
public class ShopDTO implements Serializable {

    private String id;

    private String url;

    private String commodityid;

    private String specificationsid;

    private String specificationstitle;

    private String model;

    private String price;

    private String num;

    private Boolean start;

    public Boolean getStart() {
        return start;
    }

    public void setStart(Boolean start) {
        this.start = start;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(String commodityid) {
        this.commodityid = commodityid;
    }

    public String getSpecificationsid() {
        return specificationsid;
    }

    public void setSpecificationsid(String specificationsid) {
        this.specificationsid = specificationsid;
    }

    public String getSpecificationstitle() {
        return specificationstitle;
    }

    public void setSpecificationstitle(String specificationstitle) {
        this.specificationstitle = specificationstitle;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
