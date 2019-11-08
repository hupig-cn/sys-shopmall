package com.weisen.www.code.yjf.shopmall.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * The Employee entity.
 */
@ApiModel(description = "The Employee entity.")
@Entity
@Table(name = "commodity_sku")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CommoditySku implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The firstname ClassificationAttr.
     */
    @ApiModelProperty(value = "The firstname ClassificationAttr.")
    @Column(name = "goods_id")
    private Integer goodsId;

    @Column(name = "skuid")
    private String skuid;

    @Column(name = "sku_content")
    private String skuContent;

    @Column(name = "unit_price", precision = 21, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "stocks")
    private Integer stocks;

    @Column(name = "code")
    private String code;

    @Column(name = "icon")
    private String icon;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public CommoditySku goodsId(Integer goodsId) {
        this.goodsId = goodsId;
        return this;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getSkuid() {
        return skuid;
    }

    public CommoditySku skuid(String skuid) {
        this.skuid = skuid;
        return this;
    }

    public void setSkuid(String skuid) {
        this.skuid = skuid;
    }

    public String getSkuContent() {
        return skuContent;
    }

    public CommoditySku skuContent(String skuContent) {
        this.skuContent = skuContent;
        return this;
    }

    public void setSkuContent(String skuContent) {
        this.skuContent = skuContent;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public CommoditySku unitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public CommoditySku stock(Integer stock) {
        this.stock = stock;
        return this;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStocks() {
        return stocks;
    }

    public CommoditySku stocks(Integer stocks) {
        this.stocks = stocks;
        return this;
    }

    public void setStocks(Integer stocks) {
        this.stocks = stocks;
    }

    public String getCode() {
        return code;
    }

    public CommoditySku code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIcon() {
        return icon;
    }

    public CommoditySku icon(String icon) {
        this.icon = icon;
        return this;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommoditySku)) {
            return false;
        }
        return id != null && id.equals(((CommoditySku) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CommoditySku{" +
            "id=" + getId() +
            ", goodsId=" + getGoodsId() +
            ", skuid='" + getSkuid() + "'" +
            ", skuContent='" + getSkuContent() + "'" +
            ", unitPrice=" + getUnitPrice() +
            ", stock=" + getStock() +
            ", stocks=" + getStocks() +
            ", code='" + getCode() + "'" +
            ", icon='" + getIcon() + "'" +
            "}";
    }
}
