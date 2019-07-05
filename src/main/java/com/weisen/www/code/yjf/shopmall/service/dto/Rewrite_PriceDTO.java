package com.weisen.www.code.yjf.shopmall.service.dto;

public class Rewrite_PriceDTO {

    private int todayCount;  // 今日订单量

    private String todayPrice;  //今日收入

    private String monthPrice;  // 当月销售

    private String lastmonthPrice;  // 上个月销售

    public int getTodayCount() {
        return todayCount;
    }

    public void setTodayCount(int todayCount) {
        this.todayCount = todayCount;
    }

    public String getTodayPrice() {
        return todayPrice;
    }

    public void setTodayPrice(String todayPrice) {
        this.todayPrice = todayPrice;
    }

    public String getMonthPrice() {
        return monthPrice;
    }

    public void setMonthPrice(String monthPrice) {
        this.monthPrice = monthPrice;
    }

    public String getLastmonthPrice() {
        return lastmonthPrice;
    }

    public void setLastmonthPrice(String lastmonthPrice) {
        this.lastmonthPrice = lastmonthPrice;
    }
}
