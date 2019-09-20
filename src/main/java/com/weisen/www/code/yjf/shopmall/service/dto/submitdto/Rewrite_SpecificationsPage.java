package com.weisen.www.code.yjf.shopmall.service.dto.submitdto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value="商品列表查询DTO")
public class Rewrite_SpecificationsPage implements Serializable {

	@ApiModelProperty(value="商品ID")
    private String goodsid;
	
	@ApiModelProperty(value="第几页")
    private Integer pageNum;
    
	@ApiModelProperty(value="每页多少条")
    private Integer pageSize;

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
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
