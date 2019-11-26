package com.weisen.www.code.yjf.shopmall.service.dto.submitdto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "查询商家所有商品DTO")
public class Rewrite_GetAllCommodityOperationDTO implements Serializable {

	@ApiModelProperty(value = "商品分类id")
	private Long brandid;

	@ApiModelProperty(value = "商品文件id")
	private Long fileid;

	@ApiModelProperty(value = "商品规格")
	private String specifications;

	@ApiModelProperty(value = "商品积分")
	private BigDecimal integral;

	@ApiModelProperty(value = "商品库存")
	private Integer num;

	@ApiModelProperty(value = "商品价格")
	private String price;

	@ApiModelProperty(value = "商品折扣")
	private String discount;

	@ApiModelProperty(value = "商品备注")
	private String other;

	@ApiModelProperty(value = "商家id")
	private String userId;

	@ApiModelProperty(value = "商品id")
	private String commodityId;

	@ApiModelProperty(value = "商品分类名称")
	private String model;

	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getBrandid() {
		return brandid;
	}

	public void setBrandid(Long brandid) {
		this.brandid = brandid;
	}

	public Long getFileid() {
		return fileid;
	}

	public void setFileid(Long fileid) {
		this.fileid = fileid;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}
