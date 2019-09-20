package com.weisen.www.code.yjf.shopmall.service.dto.submitdto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value="获取订单详情查询DTO")
public class Rewrite_OrderDetailsPage implements Serializable {

	@ApiModelProperty(value="订单ID")
    private String orderid;
	
	@ApiModelProperty(value="用户ID")
    private String userid;
	
	@ApiModelProperty(value="第几页")
    private Integer pageNum;
    
	@ApiModelProperty(value="每页多少条")
    private Integer pageSize;

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

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
