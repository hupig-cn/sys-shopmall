package com.weisen.www.code.yjf.shopmall.service.dto.submitdto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "商品修改DTO")
public class Rewrite_ModifyCommodityDTO implements Serializable {

	@ApiModelProperty(value = "商品文件id")
	private Long fileid;

	@ApiModelProperty(value = "商品规格", example = "飞利浦(Philips)电动牙刷HX6511/51")
	private String specifications;

	@ApiModelProperty(value = "商品积分")
	private BigDecimal integral;

	@ApiModelProperty(value = "商品库存")
	private Integer num;

	@ApiModelProperty(value = "商品价格", example = "199.99")
	private String price;

	@ApiModelProperty(value = "商品折扣")
	private String discount;

	@ApiModelProperty(value = "商品备注")
	private String other;

	@ApiModelProperty(value = "商家id", example = "51")
	private String userId;

	@ApiModelProperty(value = "商品ID", example = "101184")
	private String commodityId;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}
}
