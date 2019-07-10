package com.weisen.www.code.yjf.shopmall.service.impl;

import com.weisen.www.code.yjf.shopmall.domain.Order;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_OrderRepository;
import com.weisen.www.code.yjf.shopmall.service.Rewrite_OrderService;
import com.weisen.www.code.yjf.shopmall.service.dto.Rewrite_AnOrder;
import com.weisen.www.code.yjf.shopmall.service.dto.Rewrite_OrderSpec;
import com.weisen.www.code.yjf.shopmall.service.dto.Rewrite_PriceDTO;
import com.weisen.www.code.yjf.shopmall.service.dto.SpecificationsDTO;
import com.weisen.www.code.yjf.shopmall.service.mapper.OrderMapper;
import com.weisen.www.code.yjf.shopmall.service.util.OrderConstant;
import com.weisen.www.code.yjf.shopmall.service.util.Result;
import com.weisen.www.code.yjf.shopmall.service.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单Impl
 */
@Service
@Transactional
public class Rewrite_OrderServiceImpl implements Rewrite_OrderService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_OrderServiceImpl.class);

    private final Rewrite_OrderRepository rewrite_OrderRepository;

    private final OrderMapper orderMapper;

    public Rewrite_OrderServiceImpl(Rewrite_OrderRepository rewrite_OrderRepository, OrderMapper orderMapper) {
        this.rewrite_OrderRepository = rewrite_OrderRepository;
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

        if(rewrite_AnOrder.getOrderSpec() == null){

        }
        List<Rewrite_OrderSpec> orderSpec = rewrite_AnOrder.getOrderSpec();

        for (Rewrite_OrderSpec list : orderSpec) {
            for (SpecificationsDTO x:list.getSpec()) {
                Order order = new Order();
                order.setBigorder(rewrite_AnOrder.getUserId());
                order.setCommodityid(x.getCommodityid()); // 商品id
                order.setCreatedate(TimeUtil.getDate());
                order.setCreator("");
                order.setOrdernum(OrderConstant.getOrderCode(rewrite_AnOrder.getUserId()));
                order.setNum(list.getNum());
                order.setState(OrderConstant.UN_PAID);
                order.setUserid(rewrite_AnOrder.getUserId());
                order.setSpecificationsid(x.getId().toString());  // 规格id
                order.setPaymethod(rewrite_AnOrder.getPayWay());
                order.setPayresult(OrderConstant.UN_PAID);
                order.setLogicdelete(false);
                order.setOther("");
            }
        }

        return Result.suc("成功");
    }
}
