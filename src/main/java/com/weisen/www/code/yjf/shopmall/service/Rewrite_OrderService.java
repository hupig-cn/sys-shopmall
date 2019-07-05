package com.weisen.www.code.yjf.shopmall.service;

import com.weisen.www.code.yjf.shopmall.domain.Order;
import com.weisen.www.code.yjf.shopmall.service.dto.Rewrite_AnOrder;
import com.weisen.www.code.yjf.shopmall.service.dto.Rewrite_PriceDTO;
import com.weisen.www.code.yjf.shopmall.service.util.Result;

import java.util.List;

public interface Rewrite_OrderService {

    //查询商城的当日订单量
    int getTodayNum(Long userId);

    //查询商城当月订单量
    int getThisMonthNum(Long userId);

    //查询商城的所订单
    List<Order> selectAllUserOrder(Long userId);

    //查询商城今日收入
    Rewrite_PriceDTO getTodayPrice(Long userId);

    //获取商城本月的销售额
    Rewrite_PriceDTO getThisMonthPrice(Long userId);

    //获取商城上月的销售
    Rewrite_PriceDTO getLastMonthPrice(Long userId);

    //生成订单
    Result createMallOrder(Rewrite_AnOrder rewrite_AnOrder);

}
