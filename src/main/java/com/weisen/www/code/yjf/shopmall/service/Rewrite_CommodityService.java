package com.weisen.www.code.yjf.shopmall.service;

import com.weisen.www.code.yjf.shopmall.service.dto.CommodityDTO;
import com.weisen.www.code.yjf.shopmall.service.dto.Rewrite_ForNearShop;
import com.weisen.www.code.yjf.shopmall.service.util.Result;

import java.util.List;

public interface Rewrite_CommodityService {

    //根据商品名称获取全部商品列表
    Result getAllCommodity(Rewrite_ForNearShop rewrite_ForNearShop);

    //添加商品
    void createCommodity(CommodityDTO commodityDTO);

    // 修改商品信息
    void updateCommodity(CommodityDTO commodityDTO);

    // 商品上架
    Result CommodityGetUp(Long commodityId);

    // 商品下架
    Result CommodityGetDown(Long commodityId);

    // 删除商品
    Result deleteCommodity(Long commodityId);

    // 批量删除
    Result deleteListCommodity(List<Long> commodityId);

    // 查看商品详情
    Result findCommodityInfo(Long commodityId);

    // 根据销量查询商品
    Result findAllBySales();

    // 根据最新时间查询商品
    Result findAllByTime(Rewrite_ForNearShop rewrite_ForNearShop);
    
    // 根据查询内容查询商品
    Result findAllByContent(Rewrite_ForNearShop rewrite_ForNearShop);
}
