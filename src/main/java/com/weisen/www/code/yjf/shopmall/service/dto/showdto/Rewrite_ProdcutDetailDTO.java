package com.weisen.www.code.yjf.shopmall.service.dto.showdto;

import java.io.Serializable;
import java.math.BigDecimal;

public class Rewrite_ProdcutDetailDTO implements Serializable {
    private Long id;
    private BigDecimal price;
    private BigDecimal postage;
    private BigDecimal integral;
    private Integer num;
    private String name;
    private String model;
    private String json;
    private Long fiedId;
    private Integer salevalue;

    public Integer getSalevalue() {
        return salevalue;
    }

    public void setSalevalue(Integer salevalue) {
        this.salevalue = salevalue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPostage() {
        return postage;
    }

    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Long getFiedId() {
        return fiedId;
    }

    public void setFiedId(Long fiedId) {
        this.fiedId = fiedId;
    }
}
