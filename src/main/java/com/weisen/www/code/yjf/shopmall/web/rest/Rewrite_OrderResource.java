package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.service.OrderService;
import com.weisen.www.code.yjf.shopmall.service.Rewrite_OrderService;
import com.weisen.www.code.yjf.shopmall.service.dto.Rewrite_AnOrder;
import com.weisen.www.code.yjf.shopmall.service.util.Result;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weisen/order")
@Api(tags = "商城订单")
public class Rewrite_OrderResource {

    private final Logger log = LoggerFactory.getLogger(Rewrite_OrderResource.class);

    private static final String ENTITY_NAME = "shopmallOrder";

    private final Rewrite_OrderService rewrite_orderService;

    public Rewrite_OrderResource(Rewrite_OrderService rewrite_orderService) {
        this.rewrite_orderService = rewrite_orderService;
    }

    @GetMapping("/getTodayNum")
    @ApiOperation(value = "//查询商城的当日订单量")
    @Timed
    public ResponseEntity<Result> getTodayNum() {
        log.debug("REST request to save Commodity : {}");
        int count = rewrite_orderService.getTodayNum();
        return  ResponseEntity.ok(Result.suc("成功",count));
    }

    @GetMapping("/getThisMonthNum")
    @ApiOperation(value = "//查询商城当月订单量")
    @Timed
    public ResponseEntity<Result> getThisMonthNum() {
        log.debug("REST request to save Commodity : {}");
        int count = rewrite_orderService.getThisMonthNum();
        return  ResponseEntity.ok(Result.suc("成功",count));
    }

    @GetMapping("/createMallOrder")
    @ApiOperation(value = "//生成订单")
    @Timed
    public ResponseEntity<Result> createMallOrder(@RequestBody Rewrite_AnOrder rewrite_AnOrder) {
        log.debug("REST request to save Commodity : {}");
        Result result = rewrite_orderService.createMallOrder(rewrite_AnOrder);
        return  ResponseEntity.ok(Result.suc("成功",result));
    }


}
