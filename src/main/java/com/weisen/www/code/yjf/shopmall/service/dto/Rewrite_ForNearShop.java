package com.weisen.www.code.yjf.shopmall.service.dto;

import java.math.BigDecimal;
import java.util.List;

public class Rewrite_ForNearShop {

    private BigDecimal longitude;  // 经度

    private BigDecimal latitude;   // 纬度

    private int pageNum;  // 起始条目

    private int pageSize;  // 一页数量

    private int distance;  // 距离

    public String toString() {
        return "Rewrite_ForNearShop{" +
            "longitude=" + longitude +
            ", latitude=" + latitude +
            ", pageNum=" + pageNum +
            ", pageSize=" + pageSize +
            ", distance=" + distance +
            ", name='" + name + '\'' +
            ", list=" + list +
            '}';
    }

    private String name;
    private List<?> list;

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
