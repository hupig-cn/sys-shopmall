package com.weisen.www.code.yjf.shopmall.service;

import com.weisen.www.code.yjf.shopmall.service.dto.Rewrite_CommitOrderDTO;
import com.weisen.www.code.yjf.shopmall.service.dto.Shop;
import com.weisen.www.code.yjf.shopmall.service.util.Result;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/29 11:30
 */
public interface Rewrite_ShopService {
    Result ShoppingCartList(String userid,Integer pageNum,Integer pageSize);

    Result AddShoppingCart(Long userid,String commodityid,String num);

    Long createShopping(Long userid,String commodityid,String num);

    Result ChangeInValue(String userid, String shoppingid, Integer num);

    Result CancelThePurchase(String userid, String[] shoppingid);

    Result Empty(String userid);

    Result sum(String[] shoppingid);

    Result createOrder(Rewrite_CommitOrderDTO shoppingid);
}

