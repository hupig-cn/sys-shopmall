package com.weisen.www.code.yjf.shopmall.web.rest.Rewrite_000_商品列表;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.shopmall.service.Rewrite_SpecificationsService;
import com.weisen.www.code.yjf.shopmall.service.dto.submitdto.Rewrite_OrderDetailsPage;
import com.weisen.www.code.yjf.shopmall.service.dto.submitdto.Rewrite_SpecificationsPage;
import com.weisen.www.code.yjf.shopmall.service.util.Result;

import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/weisen")
@Api(tags = "商品列表")
public class Rewrite_GoodsOrderResource {

    private final Rewrite_SpecificationsService rewrite_SpecificationsService;

    public Rewrite_GoodsOrderResource(Rewrite_SpecificationsService rewrite_SpecificationsService) {
        this.rewrite_SpecificationsService = rewrite_SpecificationsService;
    }

    /**
     * 商品列表
     * @author Carson
     * @date 2019-09-04 16:55:45
     * @param rewrite_SpecificationsPage
     * @return
     */
    @PostMapping("/admin/getSpecificationsList")
    @ApiOperation(value = "商品列表")
    @Timed
    public ResponseEntity<Result> getSpecificationsList(@RequestBody Rewrite_SpecificationsPage rewrite_SpecificationsPage) {
        Result result = rewrite_SpecificationsService.getSpecificationsList(rewrite_SpecificationsPage);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    /**
     * 获取订单详情
     * @author Carson
     * @date 2019-09-04 19:27:52
     * @param rewrite_OrderDetailsPage
     * @return
     */
    @PostMapping("/admin/getOrderDetailsList")
    @ApiOperation(value = "获取订单详情")
    @Timed
    public ResponseEntity<Result> getOrderDetailsList(@RequestBody Rewrite_OrderDetailsPage rewrite_OrderDetailsPage) {
        Result result = rewrite_SpecificationsService.getOrderDetailsList(rewrite_OrderDetailsPage);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

}
