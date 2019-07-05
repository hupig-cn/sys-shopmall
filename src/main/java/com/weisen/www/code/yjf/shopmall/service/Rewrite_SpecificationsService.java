package com.weisen.www.code.yjf.shopmall.service;

import com.weisen.www.code.yjf.shopmall.service.dto.SpecificationsDTO;
import com.weisen.www.code.yjf.shopmall.service.util.Result;

public interface Rewrite_SpecificationsService {

    // 创建订单规格
    Result createSpecifications(SpecificationsDTO specificationsDTO);

    // 查询商品的订单规格
    Result findAllByCommodity(String commodityid);



}
