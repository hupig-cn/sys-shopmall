package com.weisen.www.code.yjf.shopmall.service;

import com.weisen.www.code.yjf.shopmall.service.dto.submitdto.Rewrite_CommodityOperationDTO;
import com.weisen.www.code.yjf.shopmall.service.dto.submitdto.Rewrite_ModifyCommodityDTO;
import com.weisen.www.code.yjf.shopmall.service.util.Result;

/**
 * @date 2019-11-19 10:28:17
 * @author LuoJinShui
 *
 */
public interface Rewrite_CommodityOperationService {

	// 查询商品分类
	Result getCommodityClassification();

	// 查询商品二级分类
	Result getCommoditySecondaryClassification(Long pid);

	// 商家新增商品
	Result newCommodity(Rewrite_CommodityOperationDTO rewrite_CommodityOperationDTO);

	// 商家修改商品
	Result modifyCommodity(Rewrite_ModifyCommodityDTO rewrite_ModifyCommodityDTO);

	// 商家删除下架商品
	Result deleteCommodity(String userId, String commodityid);

	// 查询商家所有商品
	Result selectAllCommodity(String userId, Integer pageNum, Integer pageSize);

}
