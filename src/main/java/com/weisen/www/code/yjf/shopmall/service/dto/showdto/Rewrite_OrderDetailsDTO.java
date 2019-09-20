package com.weisen.www.code.yjf.shopmall.service.dto.showdto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value="获取订单详情列表DTO")
public class Rewrite_OrderDetailsDTO implements Serializable {

	@ApiModelProperty(value="订单ID")
    private String orderid;
	
	@ApiModelProperty(value="用户ID")
    private String userid;
	
	@ApiModelProperty(value="商品ID")
    private String specificationsid;

    @ApiModelProperty(value="商品类型")
    private String model;
    
    @ApiModelProperty(value="规格详情")
    private String specifications;
    
    @ApiModelProperty(value="商品价格")
    private String price;

    @ApiModelProperty(value="收货人")
    private String consignee;
    
    @ApiModelProperty(value="收货人手机号")
    private String mobile;
    
    @ApiModelProperty(value="收货人地址")
    private String address;

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSpecificationsid() {
		return specificationsid;
	}

	public void setSpecificationsid(String specificationsid) {
		this.specificationsid = specificationsid;
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

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

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
    
}
