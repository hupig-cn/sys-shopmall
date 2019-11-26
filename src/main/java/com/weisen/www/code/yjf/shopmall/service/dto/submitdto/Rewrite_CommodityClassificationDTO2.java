package com.weisen.www.code.yjf.shopmall.service.dto.submitdto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "商品分类表DTO")
public class Rewrite_CommodityClassificationDTO2 implements Serializable {

	@ApiModelProperty(value = "商品二级分类id")
	private Long id;

	@ApiModelProperty(value = "商品分类名称")
	private String commodityName;

	@ApiModelProperty(value = "商品分类标记")
	private Integer type;

	@ApiModelProperty(value = "商品图片URL")
	private String icon;

	@ApiModelProperty(value = "图片高度")
	private Integer height;

	@ApiModelProperty(value = "图片宽度")
	private Integer width;

	@ApiModelProperty(value = "商品父类id")
	private Long pid;

	@ApiModelProperty(value = "商品分类名称隐藏字段")
	private String other;

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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}
