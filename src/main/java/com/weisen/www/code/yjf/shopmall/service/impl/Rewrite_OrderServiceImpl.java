package com.weisen.www.code.yjf.shopmall.service.impl;

import com.weisen.www.code.yjf.shopmall.domain.Order;
import com.weisen.www.code.yjf.shopmall.domain.Specifications;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_OrderRepository;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_ShoppingRepository;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_SpecificationsRepository;
import com.weisen.www.code.yjf.shopmall.service.Rewrite_OrderService;
import com.weisen.www.code.yjf.shopmall.service.dto.Rewrite_AnOrder;
import com.weisen.www.code.yjf.shopmall.service.dto.Rewrite_PriceDTO;
import com.weisen.www.code.yjf.shopmall.service.mapper.OrderMapper;
import com.weisen.www.code.yjf.shopmall.service.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 订单Impl
 */
@Service
@Transactional
public class Rewrite_OrderServiceImpl implements Rewrite_OrderService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_OrderServiceImpl.class);

    private final Rewrite_OrderRepository rewrite_OrderRepository;

    private final Rewrite_ShoppingRepository rewrite_shoppingRepository;

    private final Rewrite_SpecificationsRepository rewrite_specificationsRepository;

    private final OrderMapper orderMapper;

    public Rewrite_OrderServiceImpl(Rewrite_OrderRepository rewrite_OrderRepository, Rewrite_ShoppingRepository rewrite_shoppingRepository, Rewrite_SpecificationsRepository rewrite_specificationsRepository, OrderMapper orderMapper) {
        this.rewrite_OrderRepository = rewrite_OrderRepository;
        this.rewrite_shoppingRepository = rewrite_shoppingRepository;
        this.rewrite_specificationsRepository = rewrite_specificationsRepository;
        this.orderMapper = orderMapper;
    }

    //查询商城的当日订单量
    @Override
    public int getTodayNum ( ) {
//        List<Order> order = rewrite_OrderRepository.findAllByUserid(userId.toString());
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String startTime =  today + " 00:00:00";
        String endTime = today + " 23:59:59";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int count = rewrite_OrderRepository.getAllOrderCount(startTime,endTime,OrderConstant.PAID);
        return count;
    }
    //查询商城当月订单量
    @Override
    public int getThisMonthNum( ) {
//        List<Order> order = rewrite_OrderRepository.findAllByUserid(userId.toString());
        String today = new SimpleDateFormat("yyyy-MM").format(new Date());
        String startTime =  today + "-01 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endTime = TimeUtil.getPreMonth(sdf.format(new Date())) + "-01 00:00:00";
        int count = rewrite_OrderRepository.getAllOrderCount(startTime,endTime,OrderConstant.PAID);
        return count;
    }

    //查询用户的所订单
    @Override
    public List<Order> selectAllUserOrder(Long userId) {
        return null;
    }

    //查询商户今日收入
    @Override
    public Rewrite_PriceDTO getTodayPrice(Long userId) {
        return null;
    }

    //获取商户本月的销售额
    @Override
    public Rewrite_PriceDTO getThisMonthPrice(Long userId) {
        return null;
    }

    //获取商户上月的销售
    @Override
    public Rewrite_PriceDTO getLastMonthPrice(Long userId) {
        return null;
    }

    //生成订单
    @Override
    public Result createMallOrder(Rewrite_AnOrder rewrite_AnOrder) {
        //校验参数
        if(!CheckUtils.checkObj(rewrite_AnOrder))
            return Result.fail();
        else if (!CheckUtils.checkLongByZero(rewrite_AnOrder.getUserId()))
            return  Result.fail();
        else if ( !CheckUtils.checkLongByZero(rewrite_AnOrder.getBigOrder()))
            return Result.fail();
        else if (!CheckUtils.checkString(rewrite_AnOrder.getConsignee()))
            return Result.fail("收货人信息不能为空");
        else if (!CheckUtils.checkString(rewrite_AnOrder.getMobile()))
            return Result.fail("收货人电话号码不能为空");
        else if (!CheckUtils.checkString(rewrite_AnOrder.getAddress()))
            return Result.fail("收货地址不能为空");
        else {
            if( CheckUtils.checkArray(rewrite_AnOrder.getIds())){
                //根据购物车id查找出商品信息
                List<Specifications> infoByIds = rewrite_shoppingRepository.findInfoByIds(rewrite_AnOrder.getIds());
                //将数量和商品信息保存到订单表中
                for (Specifications infoById : infoByIds) {
                    Order order = new Order();
                    order.setBigorder(rewrite_AnOrder.getBigOrder().toString());
                    order.setCommodityid(infoById.getCommodityid());
                    order.setCreatedate(DateUtils.getDateForNow());
                    order.setNum(infoById.getNum().toString());
                    order.setSpecificationsid(infoById.getId().toString());
                    order.setUserid(rewrite_AnOrder.getUserId().toString());
                    order.setPayresult(OrderConstant.UN_PAID);
                    order.setState(OrderConstant.UN_PAID);
                    order.setAddress(rewrite_AnOrder.getAddress());
                    order.setConsignee(rewrite_AnOrder.getConsignee());
                    order.setMobile(rewrite_AnOrder.getMobile());
                    order.setOrdernum(OrderConstant.getOrderCode(rewrite_AnOrder.getUserId().toString()));
                    order.setLogicdelete(false);
                    Order save = rewrite_OrderRepository.save(order);
                    if(!CheckUtils.checkObj(save))
                        return Result.fail("网络繁忙请稍后重试");
                }
            }else if ( CheckUtils.checkLongByZero(rewrite_AnOrder.getId()) && CheckUtils.checkIntegerByZero(rewrite_AnOrder.getNumber())) {
                Optional<Specifications> byId = rewrite_specificationsRepository.findById(rewrite_AnOrder.getId());
                Specifications specifications = byId.get();
                Order order = new Order();
                order.setAddress(rewrite_AnOrder.getAddress());
                order.setConsignee(rewrite_AnOrder.getConsignee());
                order.setMobile(rewrite_AnOrder.getMobile());
                order.setBigorder(rewrite_AnOrder.getBigOrder().toString());
                order.setCommodityid(specifications.getCommodityid());
                order.setCreatedate(DateUtils.getDateForNow());
                order.setNum(rewrite_AnOrder.getNumber().toString());
                order.setSpecificationsid(specifications.getId().toString());
                order.setUserid(rewrite_AnOrder.getUserId().toString());
                order.setPayresult(OrderConstant.UN_PAID);
                order.setState(OrderConstant.UN_PAID);
                order.setOrdernum(OrderConstant.getOrderCode(rewrite_AnOrder.getUserId().toString()));
                order.setLogicdelete(false);
                Order save = rewrite_OrderRepository.save(order);
                if(!CheckUtils.checkObj(save))
                    return Result.fail("网络繁忙请稍后重试");
            } else {
                    return Result.fail("创建订单要素缺少");
            }
        }
        return Result.suc("成功");
    }
}
