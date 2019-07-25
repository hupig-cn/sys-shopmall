package com.weisen.www.code.yjf.shopmall.service.dto;

public class Rewrite_AnOrder {

    private Long userId;

    private Long bigOrder;

    private Long id;

    private Integer number;

    private Long [] ids;
    private String mobile;
    private String address;
    private String consignee;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBigOrder() {
        return bigOrder;
    }

    public void setBigOrder(Long bigOrder) {
        this.bigOrder = bigOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }
}
