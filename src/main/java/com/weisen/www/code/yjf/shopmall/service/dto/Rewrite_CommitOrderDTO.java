package com.weisen.www.code.yjf.shopmall.service.dto;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/30 11:39
 */
public class Rewrite_CommitOrderDTO implements Serializable {

    private String[] bigorder;

    private String state;

    private String userid;

    private String[] commodityids;

    private String consignee;

    private String mobile;

    private String address;

    private String[] nums;

    public String[] getBigorder() {
        return bigorder;
    }

    public void setBigorder(String[] bigorder) {
        this.bigorder = bigorder;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String[] getCommodityids() {
        return commodityids;
    }

    public void setCommodityids(String[] commodityids) {
        this.commodityids = commodityids;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String[] getNums() {
        return nums;
    }

    public void setNums(String[] nums) {
        this.nums = nums;
    }

    @Override
    public String toString() {
        return "Rewrite_CommitOrderDTO{" +
            "bigorder='" + bigorder + '\'' +
            ", state='" + state + '\'' +
            ", userid='" + userid + '\'' +
            ", commodityids=" + Arrays.toString(commodityids) +
            ", consignee='" + consignee + '\'' +
            ", mobile='" + mobile + '\'' +
            ", address='" + address + '\'' +
            ", nums=" + Arrays.toString(nums) +
            '}';
    }
}
