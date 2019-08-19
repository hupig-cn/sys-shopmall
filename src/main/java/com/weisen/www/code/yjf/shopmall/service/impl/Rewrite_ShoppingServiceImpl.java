package com.weisen.www.code.yjf.shopmall.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.shopmall.domain.Shopping;
import com.weisen.www.code.yjf.shopmall.domain.Specifications;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_ShoppingRepository;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_SpecificationsRepository;
import com.weisen.www.code.yjf.shopmall.service.Rewrite_ShoppingService;
import com.weisen.www.code.yjf.shopmall.service.dto.Rewrite_SubShopCartDTO;
import com.weisen.www.code.yjf.shopmall.service.mapper.ShoppingMapper;
import com.weisen.www.code.yjf.shopmall.service.util.CheckUtils;
import com.weisen.www.code.yjf.shopmall.service.util.DateUtils;
import com.weisen.www.code.yjf.shopmall.service.util.Result;

/**
 * Service Implementation for managing {@link Shopping}.
 */
@Service
@Transactional
public class Rewrite_ShoppingServiceImpl implements Rewrite_ShoppingService {

    private final Rewrite_ShoppingRepository rewrite_ShoppingRepository;

    private final ShoppingMapper shoppingMapper;

    private final Rewrite_SpecificationsRepository rewrite_specificationsRepository;

    public Rewrite_ShoppingServiceImpl(Rewrite_ShoppingRepository rewrite_ShoppingRepository, ShoppingMapper shoppingMapper, Rewrite_SpecificationsRepository rewrite_specificationsRepository) {
        this.rewrite_ShoppingRepository = rewrite_ShoppingRepository;
        this.shoppingMapper = shoppingMapper;
        this.rewrite_specificationsRepository = rewrite_specificationsRepository;
    }

    //商品加入购物车
    @Override
    public Result createUserShopping(Rewrite_SubShopCartDTO subShopCartDTO) {
        //前端传入商品id和数量,用户id传入
        //基本数据判断
        if (!CheckUtils.checkLongByZero(subShopCartDTO.getUserId()))
            return Result.fail();
        else if (!CheckUtils.checkLongByZero(subShopCartDTO.getGoodsId()))
            return Result.fail();
        else if (!CheckUtils.checkIntegerByZero(subShopCartDTO.getNumber()))
            return Result.fail();
        else {
            //校验库存
            Optional<Specifications> byId = rewrite_specificationsRepository.findById(subShopCartDTO.getGoodsId());
            Specifications specifications = byId.get();
            if (specifications.getNum() < subShopCartDTO.getNumber())
                return Result.fail("商品库存不足");
            Shopping oldCard = rewrite_ShoppingRepository.findBySpecificationsIdAndUserId(subShopCartDTO.getGoodsId(), subShopCartDTO.getUserId());
            if (!CheckUtils.checkObj(oldCard)) {
                //如果当前用户的购物车不存在该商品的话就新建购物车
                Shopping shopping = new Shopping();
                shopping.setCreatedate(DateUtils.getDateForNow());
                shopping.setNum(subShopCartDTO.getNumber());
                shopping.setUserid(subShopCartDTO.getUserId());
                shopping.setSpecificationsid(subShopCartDTO.getGoodsId().toString());
                shopping.setModifiernum(0L);
                Shopping save = rewrite_ShoppingRepository.save(shopping);
                if(!CheckUtils.checkObj(save))
                    return Result.fail("网络繁忙请稍后重试");
            } else {
                //查询当前用户的购物车是否存在该商品如果存在的话就只进行数量增减
                oldCard.setNum(oldCard.getNum() + subShopCartDTO.getNumber());
                oldCard.setModifierdate(DateUtils.getDateForNow());
                oldCard.setModifiernum(oldCard.getModifiernum() + 1);
                Shopping shopping = rewrite_ShoppingRepository.saveAndFlush(oldCard);
                if (!CheckUtils.checkObj(shopping))
                    return Result.fail("网络繁忙请稍后重试");
            }
            return Result.suc("成功");
        }
    }

    //获取购物车列表
    @Override
    public Result getAllShoppingByUser(Long userid) {
        List<Shopping> list = rewrite_ShoppingRepository.findAllByUserid(userid);
        return Result.suc("成功", shoppingMapper.toDto(list));
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
