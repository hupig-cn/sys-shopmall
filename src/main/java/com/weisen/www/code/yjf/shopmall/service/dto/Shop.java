package com.weisen.www.code.yjf.shopmall.service.dto;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/29 14:56
 */

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value="加入购物车DTO")
public class Shop implements Serializable {

    @ApiModelProperty(value="用户id")
    private Long userid;

    @ApiModelProperty(value="商品id")
    private String commodityid;

    @ApiModelProperty(value="规格id")
    private String specificationsid;

    @ApiModelProperty(value="购买数量")
    private String num;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
