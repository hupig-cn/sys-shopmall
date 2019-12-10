package com.weisen.www.code.yjf.shopmall.web.rest.Rewrite_002_商品分类;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.weisen.www.code.yjf.shopmall.service.Rewrite_CommodityOperationService;
import com.weisen.www.code.yjf.shopmall.service.util.Result;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 商品上下架
 * 
 * @author LuoJinShui
 *
 */

@RestController
@RequestMapping("/api")
@Api(tags = "002-商品分类")
public class Rewrite_CommodityOperationResource {

	private final Logger logger = LoggerFactory.getLogger(Rewrite_CommodityOperationResource.class);

	private final Rewrite_CommodityOperationService rewrite_CommodityOperationService;

	public Rewrite_CommodityOperationResource(Rewrite_CommodityOperationService rewrite_CommodityOperationService) {
		this.rewrite_CommodityOperationService = rewrite_CommodityOperationService;
	}
	
	
	/**
	 * 查询商品分类
	 * 
	 * @return
	 */
	@PostMapping(value = "/public/user/get/CommodityClassification")
	@ApiOperation(value = "查询商品分类")
	public ResponseEntity<Result> getCommodityClassification() {
		Result result = rewrite_CommodityOperationService.getCommodityClassification();
		logger.debug("访问成功:{},传入值:{},返回值:{}", "/user/get/CommodityClassification", " ", result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}
	

	/**
	 * 查询商品二级分类
	 * 
	 * @param pid
	 * @return
	 */
	@PostMapping(value = "/public/user/get/CommoditySecondaryClassification")
	@ApiOperation(value = "查询商品二级分类")
	public ResponseEntity<Result> getCommoditySecondaryClassification(@RequestParam(value = "pid") Long pid) {
		Result result = rewrite_CommodityOperationService.getCommoditySecondaryClassification(pid);
		logger.debug("访问成功:{},传入值:{},返回值:{}", "/user/get/CommoditySecondaryClassification", pid, result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}


//	/**
//	 * 商家新增商品
//	 * 
//	 * @param userId
//	 * @return
//	 */
//	@PostMapping(value = "/user/New/Commodity")
//	@ApiOperation(value = "商家新增商品")
//	public ResponseEntity<Result> newCommodity(
//			@RequestBody Rewrite_CommodityOperationDTO rewrite_CommodityOperationDTO) {
//		Result result = rewrite_CommodityOperationService.newCommodity(rewrite_CommodityOperationDTO);
//		logger.debug("访问成功:{},传入值:{},返回值:{}", "/user/New/Commodity", rewrite_CommodityOperationDTO, result);
//		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
//	}
//
//	/**
//	 * 商家修改商品
//	 * 
//	 * @param userId
//	 * @return
//	 */
//	@PostMapping(value = "/user/Modify/Commodity")
//	@ApiOperation(value = "商家修改商品")
//	public ResponseEntity<Result> modifyCommodity(@RequestBody Rewrite_ModifyCommodityDTO rewrite_ModifyCommodityDTO) {
//		Result result = rewrite_CommodityOperationService.modifyCommodity(rewrite_ModifyCommodityDTO);
//		logger.debug("访问成功:{},传入值:{},返回值:{}", "/user/Modify/Commodity", rewrite_ModifyCommodityDTO, result);
//		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
//	}
//
//	/**
//	 * 商家删除下架商品
//	 * 
//	 * @param userId
//	 * @return
//	 */
//	@PostMapping(value = "/user/Delete/Commodity")
//	@ApiOperation(value = "商家删除下架商品")
//	public ResponseEntity<Result> deleteCommodity(@RequestParam(value = "userId") String userId,
//			@RequestParam(value = "commodityid") String commodityid) {
//		Result result = rewrite_CommodityOperationService.deleteCommodity(userId, commodityid);
//		logger.debug("访问成功:{},传入值:{},返回值:{}", "/user/Delete/Commodity", userId + "," + commodityid, result);
//		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
//	}
//
//	/**
//	 * 查询商家所有商品
//	 * 
//	 * @param userId
//	 * @return
//	 */
//	@PostMapping(value = "/user/SelectAll/Commodity")
//	@ApiOperation(value = "查询商家所有商品")
//	public ResponseEntity<Result> selectAllCommodity(@RequestParam(value = "userId") String userId,
//			@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
//		Result result = rewrite_CommodityOperationService.selectAllCommodity(userId, pageNum, pageSize);
//		logger.debug("访问成功:{},传入值:{},返回值:{}", "/user/Delete/Commodity", userId + "," + pageNum + "," + pageSize,
//				result);
//		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
//	}

}
