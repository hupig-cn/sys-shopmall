package com.weisen.www.code.yjf.shopmall.web.rest.Rewrite_000_商品列表;

import com.weisen.www.code.yjf.shopmall.service.Rewrite_CommityGoodService;
import com.weisen.www.code.yjf.shopmall.service.util.Result;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/25 15:52
 */
@RestController
@RequestMapping("/api/public/commodity")
@Api(tags = "商品列表")
public class Rewrite_PublicResources {

    private final Logger log = LoggerFactory.getLogger(Rewrite_PublicResources.class);

    private final Rewrite_CommityGoodService rewrite_commityGoodService;

    public Rewrite_PublicResources(Rewrite_CommityGoodService rewrite_commityGoodService) {
        this.rewrite_commityGoodService = rewrite_commityGoodService;
    }

    /**
     * hui
     * @param pageSize
     * @param pageNum
     * @param type
     * @param condition
     * @param name
     * @return
     */
    @PostMapping("/findAllByTime2")
    @ApiOperation("商品列表")
    public ResponseEntity<?> myfilesList(@RequestParam Integer pageSize,
                                         @RequestParam Integer pageNum,
                                         @RequestParam Integer type,
                                         @RequestParam Integer condition, String name){
        Result result = rewrite_commityGoodService.myfilesList(pageSize,pageNum,type,condition,name);
        log.debug("访问地址: {},传入值: {},返回值: {}","/api/commodity/findAllByTime2", "传入值:"+pageSize+";"+pageNum, result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    /**
     * hui
     * @param commodityId
     * @return
     */
    @PostMapping("/findCommodityInfo2")
    @ApiOperation("商品详情")
    public ResponseEntity<?> findCommodityInfo2(@RequestParam String commodityId){
        Result result = rewrite_commityGoodService.findCommodityInfo2(commodityId);
        log.debug("访问地址: {},传入值: {},返回值: {}","/api/commodity/findCommodityInfo2", "传入值:"+commodityId, result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

//    @PostMapping("/findCommodityInfo3")
//    @ApiOperation("商品详情")
//    public ResponseEntity<?> findCommodityInfo3(){
//        Result result = rewrite_commityGoodService.findCommodityInfo3();
//        log.debug("访问地址: {},传入值: {},返回值: {}","/api/commodity/findCommodityInfo2", "传入值:", result);
//        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
//    }


}
