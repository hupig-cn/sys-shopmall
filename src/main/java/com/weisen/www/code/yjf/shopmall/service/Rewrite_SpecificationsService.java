package com.weisen.www.code.yjf.shopmall.service;

import com.weisen.www.code.yjf.shopmall.service.dto.Rewrite_submitPaySumDTO;
import com.weisen.www.code.yjf.shopmall.service.dto.SpecificationsDTO;
import com.weisen.www.code.yjf.shopmall.service.dto.submitdto.Rewrite_OrderDetailsPage;
import com.weisen.www.code.yjf.shopmall.service.dto.submitdto.Rewrite_SpecificationsPage;
import com.weisen.www.code.yjf.shopmall.service.util.Result;

public interface Rewrite_SpecificationsService {

	// 商品列表
	Result getSpecificationsList(Rewrite_SpecificationsPage rewrite_SpecificationsPage);
	
	// 获取订单详情
	Result getOrderDetailsList(Rewrite_OrderDetailsPage rewrite_OrderDetailsPage);
	
    // 创建商品规格
    Result createSpecifications(SpecificationsDTO specificationsDTO);

    // 查询商品的商品规格
    Result findAllByCommodity(String commodityid);

    //查询规格的详细信息
    Result findById(Long specificationsId);

    Result getProductDetail(Long id);
    //获取支付价格
    Result getAmout(Long id, Integer number);

    Result getOrderInfo(Rewrite_submitPaySumDTO rewrite_submitPaySumDTO);

    Result getOrderInfoByOrderId(String ordreId);
}
