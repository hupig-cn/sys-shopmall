package com.weisen.www.code.yjf.shopmall.service;

import com.weisen.www.code.yjf.shopmall.service.dto.Rewrite_SubShopCartDTO;
import com.weisen.www.code.yjf.shopmall.service.util.Result;

import java.util.List;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.shopmall.domain.Shopping}.
 */
public interface Rewrite_ShoppingService {

    //商品加入购物车
    Result createUserShopping(Rewrite_SubShopCartDTO subShopCartDTO);

    //获取购物车列表
    Result getAllShoppingByUser(Long userid);

    //删除购物车中的商品
    Result deleteShopping(Long shoppingid);

    // 批量删除商品
    Result deleteShoppingList(List<Long> shoppingid);
}
