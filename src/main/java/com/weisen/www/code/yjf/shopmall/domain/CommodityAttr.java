package com.weisen.www.code.yjf.shopmall.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * The Employee entity.
 */
@ApiModel(description = "The Employee entity.")
@Entity
@Table(name = "commodity_attr")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CommodityAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The firstname attribute.
     */
    @ApiModelProperty(value = "The firstname attribute.")
    @Column(name = "goods_id")
    private Integer goodsId;

    @Column(name = "spu")
    private String spu;

    @Column(name = "sku")
    private String sku;

    @Column(name = "ctime")
    private String ctime;

    @Column(name = "spu_lastuptime")
    private String spuLastuptime;

    @Column(name = "sku_lastuptime")
    private String skuLastuptime;

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

    public CommodityAttr goodsId(Integer goodsId) {
        this.goodsId = goodsId;
        return this;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getSpu() {
        return spu;
    }

    public CommodityAttr spu(String spu) {
        this.spu = spu;
        return this;
    }

    public void setSpu(String spu) {
        this.spu = spu;
    }

    public String getSku() {
        return sku;
    }

    public CommodityAttr sku(String sku) {
        this.sku = sku;
        return this;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCtime() {
        return ctime;
    }

    public CommodityAttr ctime(String ctime) {
        this.ctime = ctime;
        return this;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getSpuLastuptime() {
        return spuLastuptime;
    }

    public CommodityAttr spuLastuptime(String spuLastuptime) {
        this.spuLastuptime = spuLastuptime;
        return this;
    }

    public void setSpuLastuptime(String spuLastuptime) {
        this.spuLastuptime = spuLastuptime;
    }

    public String getSkuLastuptime() {
        return skuLastuptime;
    }

    public CommodityAttr skuLastuptime(String skuLastuptime) {
        this.skuLastuptime = skuLastuptime;
        return this;
    }

    public void setSkuLastuptime(String skuLastuptime) {
        this.skuLastuptime = skuLastuptime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommodityAttr)) {
            return false;
        }
        return id != null && id.equals(((CommodityAttr) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CommodityAttr{" +
            "id=" + getId() +
            ", goodsId=" + getGoodsId() +
            ", spu='" + getSpu() + "'" +
            ", sku='" + getSku() + "'" +
            ", ctime='" + getCtime() + "'" +
            ", spuLastuptime='" + getSpuLastuptime() + "'" +
            ", skuLastuptime='" + getSkuLastuptime() + "'" +
            "}";
    }
}
