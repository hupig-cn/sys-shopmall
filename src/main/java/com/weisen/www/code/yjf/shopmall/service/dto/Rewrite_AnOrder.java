package com.weisen.www.code.yjf.shopmall.service.dto;

import java.math.BigDecimal;
import java.util.List;

public class Rewrite_AnOrder {

    private BigDecimal price;

    private String userId;

    private String payWay;

    private String bigOrder;

    private String commodityid; // 商品id

    private List<SpecificationsDTO> specifications; // 规格id

    private String num;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(String commodityid) {
        this.commodityid = commodityid;
    }

    public List<SpecificationsDTO> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<SpecificationsDTO> specifications) {
        this.specifications = specifications;
    }

    public String getBigOrder() {
        return bigOrder;
    }

    public void setBigOrder(String bigOrder) {
        this.bigOrder = bigOrder;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
