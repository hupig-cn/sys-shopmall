package com.weisen.www.code.yjf.shopmall.service.impl;

import com.weisen.www.code.yjf.shopmall.domain.Order;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_OrderRepository;
import com.weisen.www.code.yjf.shopmall.service.Rewrite_OrderService;
import com.weisen.www.code.yjf.shopmall.service.dto.Rewrite_AnOrder;
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
import java.util.List;

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

    //查询商户的当日订单量
    @Override
    public int getTodayNum(Long userId) {
//        List<Order> order = rewrite_OrderRepository.findAllByUserid(userId.toString());
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String startTime =  today + " 00:00:00";
        String endTime = today + " 23:59:59";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int count = rewrite_OrderRepository.getAllOrderCount(startTime,endTime,OrderConstant.PAID);
        return count;
    }



    //查询用户当月订单量
    @Override
    public int getThisMonthNum(Long userId) {
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

        if(rewrite_AnOrder.getSpecifications()== null){

        }
        List<SpecificationsDTO> specificationsDTO = rewrite_AnOrder.getSpecifications();

        for (SpecificationsDTO list : specificationsDTO) {

            Order order = new Order();
            order.setBigorder(rewrite_AnOrder.getUserId());
//            order.setCommodityid(rewrite_AnOrder.getCommodityid()); // 商品id
            order.setCreatedate(TimeUtil.getDate());
            order.setCreator("");
            order.setOrdernum(OrderConstant.getOrderCode(rewrite_AnOrder.getUserId()));
            order.setNum(rewrite_AnOrder.getNum());
            order.setState(OrderConstant.UN_PAID);
            order.setUserid(rewrite_AnOrder.getUserId());
            order.setSpecificationsid(list.getId().toString());  // 规格id
            order.setPaymethod(rewrite_AnOrder.getPayWay());
            order.setPayresult(OrderConstant.UN_PAID);
            order.setLogicdelete(false);
            order.setOther("");

        }

        return Result.suc("成功");
    }
}
