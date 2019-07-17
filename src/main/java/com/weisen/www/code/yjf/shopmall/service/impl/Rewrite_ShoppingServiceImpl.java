package com.weisen.www.code.yjf.shopmall.service.impl;

import com.weisen.www.code.yjf.shopmall.domain.Shopping;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_ShoppingRepository;
import com.weisen.www.code.yjf.shopmall.repository.ShoppingRepository;
import com.weisen.www.code.yjf.shopmall.service.Rewrite_ShoppingService;
import com.weisen.www.code.yjf.shopmall.service.dto.ShoppingDTO;
import com.weisen.www.code.yjf.shopmall.service.mapper.ShoppingMapper;
import com.weisen.www.code.yjf.shopmall.service.util.Result;
import com.weisen.www.code.yjf.shopmall.service.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing {@link Shopping}.
 */
@Service
@Transactional
public class Rewrite_ShoppingServiceImpl implements Rewrite_ShoppingService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_ShoppingServiceImpl.class);

    private final Rewrite_ShoppingRepository rewrite_ShoppingRepository;

    private final ShoppingMapper shoppingMapper;

    public Rewrite_ShoppingServiceImpl(Rewrite_ShoppingRepository rewrite_ShoppingRepository, ShoppingMapper shoppingMapper) {
        this.rewrite_ShoppingRepository = rewrite_ShoppingRepository;
        this.shoppingMapper = shoppingMapper;
    }

    //商品加入购物车
    @Override
    public Result createUserShopping(ShoppingDTO shoppingDTO) {
        
        Long count = rewrite_ShoppingRepository.countByUseridAndSpecificationsid(shoppingDTO.getUserid(),shoppingDTO.getSpecificationsid());
        if(count > 0){
            Shopping shop = rewrite_ShoppingRepository.findByUseridAndSpecificationsid(shoppingDTO.getUserid(),shoppingDTO.getSpecificationsid());
            int sum = Integer.valueOf(shop.getNum());
            sum = sum++;
            shop.setNum(String.valueOf(sum));
            rewrite_ShoppingRepository.saveAndFlush(shop);
        }else{
            Shopping shopping = new Shopping();
            shopping.setUserid(shoppingDTO.getUserid());
            shopping.setCommodityid(shoppingDTO.getCommodityid());
            shopping.setSpecificationsid(shoppingDTO.getSpecificationsid());
            shopping.setCreator("");
            shopping.setCreatedate(TimeUtil.getDate());
            shopping.setNum(shoppingDTO.getNum());
            rewrite_ShoppingRepository.save(shopping);
        }

        return Result.suc("成功");
    }

    //获取购物车列表
    @Override
    public Result getAllShoppingByUser(String userid) {
        List<Shopping> list = rewrite_ShoppingRepository.findAllByUserid(userid);

        return Result.suc("成功",shoppingMapper.toDto(list));
    }

    //删除购物车中的商品
    @Override
    public Result deleteShopping(Long shoppingid) {
        rewrite_ShoppingRepository.deleteById(shoppingid);
        return Result.suc("成功");
    }

    // 批量删除商品
    @Override
    public Result deleteShoppingList(List<Long> shoppingid) {
        shoppingid.forEach(x -> rewrite_ShoppingRepository.deleteById(x));
        return Result.suc("成功");
    }
}
