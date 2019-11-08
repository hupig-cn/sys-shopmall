package com.weisen.www.code.yjf.shopmall.service.impl;

import com.weisen.www.code.yjf.shopmall.domain.Order;
import com.weisen.www.code.yjf.shopmall.domain.Shopping;
import com.weisen.www.code.yjf.shopmall.domain.Specifications;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_CommodityRepository;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_OrderRepository;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_ShopRepository;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_SpecificationsRepository;
import com.weisen.www.code.yjf.shopmall.service.Rewrite_ShopService;
import com.weisen.www.code.yjf.shopmall.service.dto.Rewrite_CommitOrderDTO;
import com.weisen.www.code.yjf.shopmall.service.dto.Shop;
import com.weisen.www.code.yjf.shopmall.service.dto.ShopDTO;
import com.weisen.www.code.yjf.shopmall.service.util.CheckUtils;
import com.weisen.www.code.yjf.shopmall.service.util.OrderConstant;
import com.weisen.www.code.yjf.shopmall.service.util.Result;
import com.weisen.www.code.yjf.shopmall.service.util.TimeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/29 11:31
 */
@Service
@Transactional
public class Rewrite_ShopServiceImpl implements Rewrite_ShopService {

    @Value("${images-path}")
    private String imagesPath;

    private final Rewrite_ShopRepository rewrite_shopRepository;

    private final Rewrite_SpecificationsRepository rewrite_specificationsRepository;

    private final Rewrite_CommodityRepository rewrite_commodityRepository;

    private final Rewrite_OrderRepository rewrite_orderRepository;

    public Rewrite_ShopServiceImpl(Rewrite_ShopRepository rewrite_shopRepository, Rewrite_SpecificationsRepository rewrite_specificationsRepository, Rewrite_CommodityRepository rewrite_commodityRepository, Rewrite_OrderRepository rewrite_orderRepository) {
        this.rewrite_shopRepository = rewrite_shopRepository;
        this.rewrite_specificationsRepository = rewrite_specificationsRepository;
        this.rewrite_commodityRepository = rewrite_commodityRepository;
        this.rewrite_orderRepository = rewrite_orderRepository;
    }


    @Override
    public Result ShoppingCartList(String userid,Integer pageNum,Integer pageSize) {
        List<Shopping> shoppingByUserid = rewrite_shopRepository.findShoppingByUseridOrderByModifierdateDesc(Long.valueOf(userid));
        List<ShopDTO> s = new ArrayList<>();
        for (int i = 1 + (pageSize * (pageNum)); i <= (pageNum+1) * (pageSize); i++) {
            if (i > shoppingByUserid.size()) {
                return Result.suc("查询成功", s, shoppingByUserid.size());
            }
            Shopping shopping = shoppingByUserid.get(i-1);
            String specificationsid = shopping.getSpecificationsid();
            String commodityid = shopping.getCommodityid();

            List<Specifications> c = rewrite_specificationsRepository.findAllByCommodityid(commodityid);
            if (c.size() == 0) {
                return Result.fail("输入参数有误");
            }
            Specifications specifications = c.get(0);
            ShopDTO shopDTO = new ShopDTO();
            shopDTO.setId(shopping.getId()+"");
            shopDTO.setUrl(imagesPath + specifications.getFileid());
            shopDTO.setCommodityid(commodityid);
            shopDTO.setSpecificationsid(specificationsid);
            shopDTO.setSpecificationstitle(specifications.getSpecifications());
            shopDTO.setModel(specifications.getModel());
            shopDTO.setPrice(specifications.getPrice());
            shopDTO.setNum(shopping.getNum() + "");

            s.add(shopDTO);
        }
        return Result.suc("查询成功", s, shoppingByUserid.size());
    }

    @Override
    public Result AddShoppingCart(Long userid,String commodityid,String num) {

        if (Long.valueOf(num) > 99) {
            return Result.fail("购买数量过多");
        }
        List<Specifications> aa = rewrite_specificationsRepository.findAllByCommodityid(commodityid);
        Specifications specifications = aa.get(0);
        Integer kucun = specifications.getNum();
        if (kucun == null) {
            kucun = 10000;
            specifications.setNum(kucun);
        }
        if ((kucun - Long.valueOf(num)) < 0) {
            return Result.fail("购买数量超过数量");
        }
        Long shopping;
        //判断用户买过这个商家的东西没有
        List<Shopping> shoppingByUserid = rewrite_shopRepository.findShoppingByUserid(userid);
        if (shoppingByUserid == null || shoppingByUserid.size() == 0) {
            shopping = createShopping(userid, commodityid, num);
        } else {
            Shopping shoppingByCommodityid = rewrite_shopRepository.findShoppingByCommodityidAndUserid(commodityid,userid);
            if (shoppingByCommodityid == null) {
                shopping = createShopping(userid, commodityid, num);
            } else {
                Integer num1 = shoppingByCommodityid.getNum();
                if ((kucun - Long.valueOf(num) - num1) < 0 || num1 + Long.valueOf(num) > 99) {
                    return Result.fail("购买数量超过数量");
                } else {
                    shoppingByCommodityid.setNum((int) (num1 + Long.valueOf(num)));
                    shoppingByCommodityid.setModifierdate(TimeUtil.getDate());
                    Shopping save = rewrite_shopRepository.save(shoppingByCommodityid);
                    shopping = save.getId();
                }
            }
        }
        int i = kucun - Integer.valueOf(num);
        specifications.setNum(i);
        rewrite_specificationsRepository.save(specifications);
        return Result.suc("添加成功", shopping);
    }

    @Override
    public Long createShopping(Long userid,String commodityid,String num) {
        String specificationsid = commodityid;
        Shopping shopping = new Shopping();
        shopping.setUserid(userid);
        shopping.setCommodityid(commodityid);
        shopping.setSpecificationsid(specificationsid);
        shopping.setNum(Integer.valueOf(num));
        shopping.setCreator(userid + "");
        shopping.setCreatedate(TimeUtil.getDate());
        shopping.setModifier(userid + "");
        shopping.setModifierdate(TimeUtil.getDate());
        shopping.setModifiernum(1L);
        shopping.setLogicdelete(false);
        shopping.setOther("");
        Shopping save = rewrite_shopRepository.save(shopping);
        return save.getId();
    }

    @Override
    public Result ChangeInValue(String userid, String shoppingid, Integer num) {
        if (num < 0){
            return Result.fail("输入参数有误！");
        }
        Shopping shoppingById = rewrite_shopRepository.findShoppingById(Long.valueOf(shoppingid));
        Integer num1 = shoppingById.getNum();
        List<Specifications> aa = rewrite_specificationsRepository.findAllByCommodityid(shoppingById.getCommodityid());
        Specifications specifications = aa.get(0);
        Integer kucun = specifications.getNum();
        shoppingById.setNum(num);
        if (Long.valueOf(num) < 0 || Long.valueOf(num) > 99) {
            return Result.fail("购买数量超过数量");
        }else {
            kucun = kucun - num + num1;
            specifications.setNum(kucun);
            //判断之前的num跟现在的num对比
            rewrite_specificationsRepository.save(specifications);
            return Result.suc("修改成功");
        }
    }

    @Override
    public Result CancelThePurchase(String userid, String[] shoppingid) {
        for (int i = 0; i < shoppingid.length; i++) {
            Shopping shoppingById = rewrite_shopRepository.findShoppingById(Long.valueOf(shoppingid[i]));
            if (shoppingById == null) {
                return Result.fail("查无该商品");
            }
            rewrite_shopRepository.delete(shoppingById);
            Integer num = shoppingById.getNum();
            List<Specifications> aa = rewrite_specificationsRepository.findAllByCommodityid(shoppingById.getCommodityid());
            Specifications specifications = aa.get(0);
            Integer kucun = specifications.getNum();
            //判断之前的num跟现在的num对比
            kucun = kucun + num;
            specifications.setNum(kucun);
            rewrite_specificationsRepository.save(specifications);
        }
        return Result.suc("删除成功");
    }

    @Override
    public Result Empty(String userid) {
        int i = rewrite_shopRepository.deleteShoppingByUserid(Long.valueOf(userid));
        return Result.suc("删除成功", i);
    }

    @Override
    public Result sum(String[] shoppingid) {
        Double sum = 0.00;
        Integer numm = 0;
        if (shoppingid[0].equals("0")){
            return Result.suc("查询成功",sum,numm);
        }
        for (int i = 0; i < shoppingid.length; i++) {
            Shopping shoppingById = rewrite_shopRepository.findShoppingById(Long.valueOf(shoppingid[i]));
            Integer num = shoppingById.getNum();
            String commodityid = shoppingById.getCommodityid();
            List<Specifications> allByCommodityid = rewrite_specificationsRepository.findAllByCommodityid(commodityid);
            Specifications specifications = allByCommodityid.get(0);
            String price = specifications.getPrice();
            sum = Double.valueOf(price) * num + sum;
            numm = numm + num;
        }
        return Result.suc("查询成功", sum, numm);
    }

    @Override
    public Result createOrder(Rewrite_CommitOrderDTO shoppingid) {
        if (!CheckUtils.checkObj(shoppingid)) {
            return Result.fail();
        } else if (!CheckUtils.checkLongByZero(Long.valueOf(shoppingid.getUserid()))){
            return Result.fail();
        }else if (!CheckUtils.checkString(shoppingid.getConsignee())) {
            return Result.fail("收货人信息不能为空");
        }else if (!CheckUtils.checkString(shoppingid.getMobile())) {
            return Result.fail("收货人电话号码不能为空");
        }else if (!CheckUtils.checkString(shoppingid.getAddress())) {
            return Result.fail("收货地址不能为空");
        }
        String[] commodityids = shoppingid.getCommodityids();
        String[] nums = shoppingid.getNums();
        String[] bigorder = shoppingid.getBigorder();
        String[] ids = shoppingid.getIds();
        for (int i = 0; i < ids.length; i++) {
            Shopping shoppingById = rewrite_shopRepository.findShoppingById(Long.valueOf(ids[i]));
            if (shoppingById == null){
                return Result.fail("该商品已被操作！");
            }
        }
        for (int i = 0; i < commodityids.length; i++) {
            Order order = new Order();
            order.setBigorder(bigorder[i]);
            order.setOrdernum(OrderConstant.getOrderCode(shoppingid.getUserid()));
            order.setState(OrderConstant.UN_PAID);
            order.setUserid(shoppingid.getUserid());
            order.setCommodityid(commodityids[i]);
            order.setSpecificationsid(commodityids[i]);
            order.setConsignee(shoppingid.getConsignee());
            order.setMobile(shoppingid.getMobile());
            order.setAddress(shoppingid.getAddress());
            order.setNum(nums[i]);
            order.setPaymethod("");
            order.setPayresult("1");
            order.setCreator(shoppingid.getUserid());
            order.setCreatedate(TimeUtil.getDate());
            rewrite_orderRepository.save(order);
            rewrite_shopRepository.deleteShoppingById(Long.valueOf(ids[i]));
        }

        return Result.suc("生成成功");
    }

    @Override
    public Result Selected(String[] shoppingid,String userid) {
        List<Shopping> shoppingByUserid = rewrite_shopRepository.findShoppingByUserid(Long.valueOf(userid));
        if (shoppingByUserid.size() == 0 ){
            return Result.fail("没有购物车，不能选中");
        }
        for (int i = 0; i < shoppingid.length; i++) {
            String s = shoppingid[i];
            Shopping shopping = rewrite_shopRepository.findShoppingByIdAndUserid(Long.valueOf(s), Long.valueOf(userid));
            if (shopping == null){
                return Result.fail("无该商品");
            }
            if (shopping.isLogicdelete().equals(false)){
                shopping.setLogicdelete(true);
            }else {
                shopping.setLogicdelete(false);
            }
            shopping.setId(shopping.getId());
            rewrite_shopRepository.save(shopping);
        }
        return Result.suc("ok");
    }


}
