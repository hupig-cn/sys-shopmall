package com.weisen.www.code.yjf.shopmall.service;


import com.weisen.www.code.yjf.shopmall.service.util.Result;

public interface Rewrite_CommityGoodService {

    Result myfilesList(Integer pageSize, Integer pageNum, Integer type, Integer condition,String name);

    Result findCommodityInfo2(String commityid);
}
