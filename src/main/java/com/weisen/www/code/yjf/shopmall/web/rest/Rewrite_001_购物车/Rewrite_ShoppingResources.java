package com.weisen.www.code.yjf.shopmall.web.rest.Rewrite_001_购物车;

import com.weisen.www.code.yjf.shopmall.service.Rewrite_ShopService;
import com.weisen.www.code.yjf.shopmall.service.dto.Rewrite_CommitOrderDTO;
import com.weisen.www.code.yjf.shopmall.service.dto.Shop;
import com.weisen.www.code.yjf.shopmall.service.util.Result;
import com.weisen.www.code.yjf.shopmall.web.rest.Rewrite_ShoppingResource;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/25 9:55
 */
@RestController
@RequestMapping("/api/weisen/shoppings")
@Api(tags = "购物车aa")
public class Rewrite_ShoppingResources {

    private final Logger log = LoggerFactory.getLogger(Rewrite_ShoppingResource.class);

    private final Rewrite_ShopService rewrite_shopService;

    public Rewrite_ShoppingResources(Rewrite_ShopService rewrite_shopService) {
        this.rewrite_shopService = rewrite_shopService;
    }

    @PostMapping("/ShoppingCartList")
    @ApiOperation("购物车列表")
    public ResponseEntity<?> ShoppingCartList(@RequestParam(required = false) String userid){
        Result result = rewrite_shopService.ShoppingCartList(userid);
        log.debug("访问地址: {},传入值: {},返回值: {}","/api/weisen/shoppings/ShoppingCartList", "传入值:"+userid, result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));

    }

    @PostMapping("/AddShoppingCart")
    @ApiOperation("加入购物车")
    public ResponseEntity<?> AddShoppingCart(@RequestParam(required = false) Long userid,
                                             @RequestParam(required = false)String commodityid,
                                             @RequestParam(required = false)String num){
        Result result = rewrite_shopService.AddShoppingCart(userid,commodityid,num);
        log.debug("访问地址: {},传入值: {},返回值: {}","/api/weisen/shoppings/AddShoppingCart", "传入值:"+userid+","+commodityid+","+num, result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));

    }

    @PostMapping("/ChangeInValue")
    @ApiOperation("购物车的数量改变")
    public ResponseEntity<?> ChangeInValue(@RequestParam(required = false) String userid,
                                           @RequestParam(required = false) String shoppingid,
                                           @RequestParam(required = false) Integer num){
        Result result = rewrite_shopService.ChangeInValue(userid,shoppingid,num);
        log.debug("访问地址: {},传入值: {},返回值: {}","/api/weisen/shoppings/ChangeInValue", "传入值:"+userid+":"+shoppingid+":"+num, result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));

    }

    @PostMapping("/CancelThePurchase")
    @ApiOperation("删除购物车商品(单和多)")
    public ResponseEntity<?> CancelThePurchase(@RequestParam(required = false) String userid,
                                           @RequestParam(required = false) String[] shoppingid){
        Result result = rewrite_shopService.CancelThePurchase(userid,shoppingid);
        log.debug("访问地址: {},传入值: {},返回值: {}","/api/weisen/shoppings/CancelThePurchase", "传入值:"+userid+":"+shoppingid, result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));

    }

    @PostMapping("/Empty")
    @ApiOperation("清空购物车")
    public ResponseEntity<?> Empty(@RequestParam(required = false) String userid){
        Result result = rewrite_shopService.Empty(userid);
        log.debug("访问地址: {},传入值: {},返回值: {}","/api/weisen/shoppings/Empty", "传入值:"+userid, result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));

    }

    @PostMapping("/sum")
    @ApiOperation("选中价格总价")
    public ResponseEntity<?> sum(@RequestParam(required = false) String[] shoppingid){
        Result result = rewrite_shopService.sum(shoppingid);
        log.debug("访问地址: {},传入值: {},返回值: {}","/api/weisen/shoppings/sum", "传入值:"+shoppingid, result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    @PostMapping("/createOrder")
    @ApiOperation("创建订单")
    public ResponseEntity<?> createOrder(@RequestBody Rewrite_CommitOrderDTO shoppingid){
        Result result = rewrite_shopService.createOrder(shoppingid);
        log.debug("访问地址: {},传入值: {},返回值: {}","/api/weisen/shoppings/createOrder", "传入值:"+shoppingid.toString(), result);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

}
