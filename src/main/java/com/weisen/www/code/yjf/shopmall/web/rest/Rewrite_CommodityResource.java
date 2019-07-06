package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.service.Rewrite_CommodityService;
import com.weisen.www.code.yjf.shopmall.service.dto.CommodityDTO;
import com.weisen.www.code.yjf.shopmall.service.util.Result;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/weisen/commodity")
@Api(tags = "商品")
public class Rewrite_CommodityResource {

    private final Logger log = LoggerFactory.getLogger(Rewrite_CommodityResource.class);

    private static final String ENTITY_NAME = "shopmallCommodity";

    private final Rewrite_CommodityService rewrite_CommodityService;

    public Rewrite_CommodityResource(Rewrite_CommodityService rewrite_CommodityService) {
        this.rewrite_CommodityService = rewrite_CommodityService;
    }

    @GetMapping ("/getAllCommodity")
    @ApiOperation(value = "//获取全部商品列表")
    @Timed
    public ResponseEntity<Result> getAllCommodity() {
        log.debug("REST request to save Commodity : {}");
        Result result = rewrite_CommodityService.getAllCommodity();
        return  ResponseEntity.ok(Result.suc("成功",result));
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

    @GetMapping ("/deleteListCommodity/{commodityId}")
    @ApiOperation(value = "查看商品详情")
    @Timed
    public ResponseEntity<Result> findCommodityInfo(@PathVariable Long commodityId) {
        log.debug("REST request to save Commodity : {}", commodityId);
        Result result = rewrite_CommodityService.findCommodityInfo(commodityId);
        return  ResponseEntity.ok(Result.suc("成功",result));
    }

}
