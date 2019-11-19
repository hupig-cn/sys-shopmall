package com.weisen.www.code.yjf.shopmall.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A V2ShoppingCart.
 */
@Entity
@Table(name = "v_2_shopping_cart")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class V2ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private Long userid;

    @Column(name = "goods_id")
    private Long goodsId;

    @Column(name = "sku_price")
    private Float skuPrice;

    @Column(name = "sku_content")
    private String skuContent;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "ctime")
    private String ctime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public V2ShoppingCart userid(Long userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public V2ShoppingCart goodsId(Long goodsId) {
        this.goodsId = goodsId;
        return this;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Float getSkuPrice() {
        return skuPrice;
    }

    public V2ShoppingCart skuPrice(Float skuPrice) {
        this.skuPrice = skuPrice;
        return this;
    }

    public void setSkuPrice(Float skuPrice) {
        this.skuPrice = skuPrice;
    }

    public String getSkuContent() {
        return skuContent;
    }

    public V2ShoppingCart skuContent(String skuContent) {
        this.skuContent = skuContent;
        return this;
    }

    public void setSkuContent(String skuContent) {
        this.skuContent = skuContent;
    }

    public Long getQuantity() {
        return quantity;
    }

    public V2ShoppingCart quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getCtime() {
        return ctime;
    }

    public V2ShoppingCart ctime(String ctime) {
        this.ctime = ctime;
        return this;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof V2ShoppingCart)) {
            return false;
        }
        return id != null && id.equals(((V2ShoppingCart) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "V2ShoppingCart{" +
            "id=" + getId() +
            ", userid=" + getUserid() +
            ", goodsId=" + getGoodsId() +
            ", skuPrice=" + getSkuPrice() +
            ", skuContent='" + getSkuContent() + "'" +
            ", quantity=" + getQuantity() +
            ", ctime='" + getCtime() + "'" +
            "}";
    }
}
