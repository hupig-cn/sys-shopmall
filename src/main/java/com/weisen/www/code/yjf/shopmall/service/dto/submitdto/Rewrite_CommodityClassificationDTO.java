package com.weisen.www.code.yjf.shopmall.service.dto.submitdto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "商品分类表DTO")
public class Rewrite_CommodityClassificationDTO implements Serializable {

	@ApiModelProperty(value = "商品分类id", example = "13")
	private Long id;

	@ApiModelProperty(value = "商品分类名称")
	private String commodityName;

	@ApiModelProperty(value = "商品分类标记")
	private Integer type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
