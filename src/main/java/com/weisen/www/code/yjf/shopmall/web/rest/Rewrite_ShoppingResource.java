package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.service.Rewrite_ShoppingService;
import com.weisen.www.code.yjf.shopmall.service.dto.Rewrite_SubShopCartDTO;
import com.weisen.www.code.yjf.shopmall.service.util.Result;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weisen/shopping")
@Api(tags = "购物车")
public class Rewrite_ShoppingResource {

    private final Logger log = LoggerFactory.getLogger(Rewrite_ShoppingResource.class);

    private final Rewrite_ShoppingService rewrite_ShoppingService;

    public Rewrite_ShoppingResource(Rewrite_ShoppingService rewrite_ShoppingService) {
        this.rewrite_ShoppingService = rewrite_ShoppingService;
    }

    @PostMapping("/createUserShopping")
    @ApiOperation(value = "商品加入购物车")
    @Timed
    public ResponseEntity<Result> createUserShopping(@RequestBody Rewrite_SubShopCartDTO subShopCartDTO) {
        log.debug("createUserShopping : {}");
        Result result = rewrite_ShoppingService.createUserShopping(subShopCartDTO);
        return  ResponseEntity.ok(result);
    }

    @GetMapping("/getAllShoppingByUser/{userid}")
    @ApiOperation(value = "获取购物车列表")
    @Timed
    public ResponseEntity<Result> getAllShoppingByUser(@PathVariable Long userid) {
        log.debug("getAllShoppingByUser : {}");
        Result result = rewrite_ShoppingService.getAllShoppingByUser(userid);
        return  ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteShopping/{shoppingid}")
    @ApiOperation(value = "删除购物车中的商品")
    @Timed
    public ResponseEntity<Result> deleteShopping(@PathVariable Long shoppingid) {
        log.debug("deleteShopping : {}");
        Result result = rewrite_ShoppingService.deleteShopping(shoppingid);
        return  ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteShoppingList/{shoppingid}")
    @ApiOperation(value = "批量删除商品")
    @Timed
    public ResponseEntity<Result> deleteShoppingList(@PathVariable List<Long> shoppingid) {
        log.debug("deleteShoppingList : {}");
        Result result = rewrite_ShoppingService.deleteShoppingList(shoppingid);
        return  ResponseEntity.ok(result);
    }


}
