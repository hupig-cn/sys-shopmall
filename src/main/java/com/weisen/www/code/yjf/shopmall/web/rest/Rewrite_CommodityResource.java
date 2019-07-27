package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.service.Rewrite_CommodityService;
import com.weisen.www.code.yjf.shopmall.service.Rewrite_SpecificationsService;
import com.weisen.www.code.yjf.shopmall.service.dto.CommodityDTO;
import com.weisen.www.code.yjf.shopmall.service.dto.Rewrite_ForNearShop;
import com.weisen.www.code.yjf.shopmall.service.dto.Rewrite_submitPaySumDTO;
import com.weisen.www.code.yjf.shopmall.service.util.Result;
import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/weisen/commodity")
@Api(tags = "商品")
public class Rewrite_CommodityResource {

    private final Logger log = LoggerFactory.getLogger(Rewrite_CommodityResource.class);

    private static final String ENTITY_NAME = "shopmallCommodity";

    private final Rewrite_CommodityService rewrite_CommodityService;

    private final Rewrite_SpecificationsService rewrite_specificationsService;

    public Rewrite_CommodityResource(Rewrite_CommodityService rewrite_CommodityService,Rewrite_SpecificationsService rewrite_specificationsService) {
        this.rewrite_CommodityService = rewrite_CommodityService;
        this.rewrite_specificationsService = rewrite_specificationsService;
    }

    @PostMapping ("/getAllCommodity")
    @ApiOperation(value = "根据商品名称获取全部商品列表")
    @Timed
    public ResponseEntity<Result> getAllCommodity(@RequestBody Rewrite_ForNearShop rewrite_ForNearShop) {
        log.debug("getAllCommodity : {}");
        Result result = rewrite_CommodityService.getAllCommodity(rewrite_ForNearShop);
        return  ResponseEntity.ok(result);
    }

    @PostMapping ("/findAllByTime")
    @ApiOperation(value = "根据最新时间查询商品")
    @Timed
    public ResponseEntity<Result> findAllByTime(@RequestBody Rewrite_ForNearShop rewrite_ForNearShop) {
        log.debug("findAllByTime : {}");
        Result result = rewrite_CommodityService.findAllByTime(rewrite_ForNearShop);
        return ResponseEntity.ok(result);

    }

    @PostMapping("/createCommodity")
    @ApiOperation(value = "添加商品")
    @Timed
    public ResponseEntity<Result> createCommodity(@RequestBody CommodityDTO commodityDTO) {
        log.debug("REST request to save Commodity : {}", commodityDTO);
//        if (commodityDTO.getId() != null) {
//            throw new BadRequestAlertException("A new commodity cannot already have an ID", ENTITY_NAME, "idexists");
//        }
        rewrite_CommodityService.createCommodity(commodityDTO);
        return  ResponseEntity.ok(Result.suc("成功"));
    }

    @PostMapping("/updateCommodity")
    @ApiOperation(value = "修改商品信息")
    @Timed
    public ResponseEntity<Result> updateCommodity(@RequestBody CommodityDTO commodityDTO) {
        log.debug("REST request to save Commodity : {}", commodityDTO);
        rewrite_CommodityService.updateCommodity(commodityDTO);
        return  ResponseEntity.ok(Result.suc("成功"));
    }

    @PostMapping("/CommodityGetUp")
    @ApiOperation(value = "商品上架")
    @Timed
    public ResponseEntity<Result> CommodityGetUp(@RequestBody Long commodityId) {
        log.debug("REST request to save Commodity : {}", commodityId);
        rewrite_CommodityService.CommodityGetUp(commodityId);
        return  ResponseEntity.ok(Result.suc("成功"));
    }

    @PostMapping("/CommodityGetDown")
    @ApiOperation(value = "商品下架")
    @Timed
    public ResponseEntity<Result> CommodityGetDown(@RequestBody Long commodityId) {
        log.debug("REST request to save Commodity : {}", commodityId);
        rewrite_CommodityService.CommodityGetUp(commodityId);
        return  ResponseEntity.ok(Result.suc("成功"));
    }

    @DeleteMapping("/deleteCommodity/{commodityId}")
    @ApiOperation(value = "删除商品")
    @Timed
    public ResponseEntity<Result> deleteCommodity(@PathVariable Long commodityId) {
        log.debug("REST request to save Commodity : {}", commodityId);
        rewrite_CommodityService.deleteCommodity(commodityId);
        return  ResponseEntity.ok(Result.suc("成功"));
    }

    @DeleteMapping ("/deleteListCommodity/{commodityId}")
    @ApiOperation(value = "批量删除")
    @Timed
    public ResponseEntity<Result> deleteListCommodity(@PathVariable List<Long> commodityId) {
        log.debug("REST request to save Commodity : {}", commodityId);
        rewrite_CommodityService.deleteListCommodity(commodityId);
        return  ResponseEntity.ok(Result.suc("成功"));
    }

    @GetMapping("/findCommodityInfo/{commodityId}")
    @ApiOperation(value = "查看商品详情")
    @Timed
    public ResponseEntity<Result> findCommodityInfo(@PathVariable Long commodityId) {
        log.debug("REST request to save Commodity : {}", commodityId);
        Result result = rewrite_CommodityService.findCommodityInfo(commodityId);
        return  ResponseEntity.ok(result);
    }
    //商品详情接口
    @GetMapping("/get-productDetail/{id}")
    @ApiOperation(value = "获取商品详情")
    @Time
    public ResponseEntity<?> getProductDetail(@PathVariable Long id){
        Result result = rewrite_specificationsService.getProductDetail(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    //商品图片接口
    //算价接口
    @PostMapping("/get-amount")
    @ApiOperation(value = "获取支付价格")
    @Time
    public ResponseEntity<?> getAmount(@RequestBody Rewrite_submitPaySumDTO rewrite_submitPaySumDTO){
        Result result = rewrite_specificationsService.getAmout(rewrite_submitPaySumDTO.getId(), rewrite_submitPaySumDTO.getNumber());
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    @PostMapping("/get-order-info")
    @ApiOperation(value = "确认商品信息")
    @Time
    public ResponseEntity<?> getProductInfo(@RequestBody Rewrite_submitPaySumDTO rewrite_submitPaySumDTO){
            //同时接收一个数组和id,intger 类型的数量,存在数组则查询购物车不查询id
            //根据数组查询购物车,获取每件商品的信息,判断相应的库存,根据商品数量和价格进行乘算,计算出总价
            //根据id查询出商品并且根据商品的价格进行乘算计算出总价.
            //统一返回一个map封装数据
        Result result = rewrite_specificationsService.getOrderInfo(rewrite_submitPaySumDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

}
