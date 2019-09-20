package com.weisen.www.code.yjf.shopmall.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.shopmall.domain.Order;
import com.weisen.www.code.yjf.shopmall.domain.Product;
import com.weisen.www.code.yjf.shopmall.domain.Specifications;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_CommodityRepository;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_OrderRepository;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_ShoppingRepository;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_SpecificationsRepository;
import com.weisen.www.code.yjf.shopmall.service.Rewrite_SpecificationsService;
import com.weisen.www.code.yjf.shopmall.service.dto.Rewrite_submitPaySumDTO;
import com.weisen.www.code.yjf.shopmall.service.dto.SpecificationsDTO;
import com.weisen.www.code.yjf.shopmall.service.dto.showdto.Rewrite_OrderDetailsDTO;
import com.weisen.www.code.yjf.shopmall.service.dto.submitdto.Rewrite_OrderDetailsPage;
import com.weisen.www.code.yjf.shopmall.service.dto.submitdto.Rewrite_SpecificationsPage;
import com.weisen.www.code.yjf.shopmall.service.mapper.SpecificationsMapper;
import com.weisen.www.code.yjf.shopmall.service.util.CheckUtils;
import com.weisen.www.code.yjf.shopmall.service.util.Result;
import com.weisen.www.code.yjf.shopmall.service.util.TimeUtil;

@Service
@Transactional
public class Rewrite_SpecificationsServiceImpl implements Rewrite_SpecificationsService {

    private final Rewrite_SpecificationsRepository rewrite_SpecificationsRepository;

    private final Rewrite_CommodityRepository rewrite_commodityRepository;

    private final Rewrite_ShoppingRepository rewrite_ShoppingRepository;
    
    private final Rewrite_OrderRepository rewrite_OrderRepository;
    
    private final SpecificationsMapper specificationsMapper;

    public Rewrite_SpecificationsServiceImpl(Rewrite_SpecificationsRepository rewrite_SpecificationsRepository,
    		Rewrite_CommodityRepository rewrite_commodityRepository,
    		Rewrite_ShoppingRepository rewrite_ShoppingRepository,
    		Rewrite_OrderRepository rewrite_OrderRepository,
    		SpecificationsMapper specificationsMapper) {
        this.rewrite_SpecificationsRepository = rewrite_SpecificationsRepository;
        this.rewrite_commodityRepository = rewrite_commodityRepository;
        this.rewrite_ShoppingRepository = rewrite_ShoppingRepository;
        this.rewrite_OrderRepository = rewrite_OrderRepository;
        this.specificationsMapper = specificationsMapper;
    }
    
    // 获取商品列表（直接查询规格表）
    @Override
    public Result getSpecificationsList(Rewrite_SpecificationsPage rewrite_SpecificationsPage) {
        List<Specifications> specificationsList = rewrite_SpecificationsRepository.getSpecificationsList(
        		rewrite_SpecificationsPage.getGoodsid(),
        		rewrite_SpecificationsPage.getPageNum() * rewrite_SpecificationsPage.getPageSize(),
        		rewrite_SpecificationsPage.getPageSize());
        int count = rewrite_SpecificationsRepository.getSpecificationsListCount(rewrite_SpecificationsPage.getGoodsid());
        List<SpecificationsDTO> dtoList = specificationsMapper.toDto(specificationsList);
        List<SpecificationsDTO> show = new ArrayList<>();
        dtoList.forEach(x->{
        	show.add(x);
        });
        return Result.suc("成功", show, count);
    }
    
    // 获取订单详情
    @Override
    public Result getOrderDetailsList(Rewrite_OrderDetailsPage rewrite_OrderDetailsPage) {
        List<Order> orderList = rewrite_OrderRepository.getOrderDetailsList(
        		rewrite_OrderDetailsPage.getOrderid(),
        		rewrite_OrderDetailsPage.getUserid(),
        		rewrite_OrderDetailsPage.getPageNum() * rewrite_OrderDetailsPage.getPageSize(),
        		rewrite_OrderDetailsPage.getPageSize());
        int count = rewrite_OrderRepository.getOrderDetailsListCount(rewrite_OrderDetailsPage.getOrderid(),rewrite_OrderDetailsPage.getUserid());
        List<Rewrite_OrderDetailsDTO> rewrite_OrderDetailsLists = new ArrayList<>();
        for (Order order : orderList) {
        	Rewrite_OrderDetailsDTO rewrite_OrderDetailsDTO = new Rewrite_OrderDetailsDTO();
        	rewrite_OrderDetailsDTO.setOrderid(order.getBigorder());
        	rewrite_OrderDetailsDTO.setUserid(order.getUserid());
        	rewrite_OrderDetailsDTO.setSpecificationsid(order.getSpecificationsid());
        	Specifications specifications = rewrite_SpecificationsRepository.getOne(Long.valueOf(order.getSpecificationsid()));
        	if(specifications!=null) {
        		rewrite_OrderDetailsDTO.setModel(specifications.getModel());
        		rewrite_OrderDetailsDTO.setSpecifications(specifications.getSpecifications());
        		rewrite_OrderDetailsDTO.setPrice(specifications.getPrice());
        	}
        	rewrite_OrderDetailsDTO.setConsignee(order.getConsignee());
        	rewrite_OrderDetailsDTO.setMobile(order.getMobile());
        	rewrite_OrderDetailsDTO.setAddress(order.getAddress());
        	rewrite_OrderDetailsLists.add(rewrite_OrderDetailsDTO);
		}
        return Result.suc("成功", rewrite_OrderDetailsLists, count);
    }

    // 创建订单规格
    @Override
    public Result createSpecifications(SpecificationsDTO specificationsDTO) {
        Specifications specifications = new Specifications();
        specifications.setCommodityid(specificationsDTO.getCommodityid());
        specifications.setDiscount(specificationsDTO.getDiscount());
        specifications.setModel(specificationsDTO.getModel());
        specifications.setNum(specificationsDTO.getNum());
        specifications.setDiscount(specificationsDTO.getDiscount());
        specifications.setCreator("");
        specifications.setCreatedate(TimeUtil.getDate());
        specifications.setLogicdelete(false);
        specifications.setOther("");
        rewrite_SpecificationsRepository.save(specifications);
        return Result.suc("成功");
    }

    // 查询商品的订单规格
    @Override
    public Result findAllByCommodity(String commodityid) {
        List<Specifications> list = rewrite_SpecificationsRepository.findAllByCommodityid(commodityid);
        return Result.suc("成功", list);
    }

    @Override
    public Result findById(Long specificationsId) {
        Specifications specifications = rewrite_SpecificationsRepository.findById(specificationsId).get();
        return Result.suc("成功", specifications);
    }


    public Result getProductDetail(Long id) {
        if (!CheckUtils.checkLongByZero(id))
            return Result.fail("未知的商品信息");
        else {
            //从商品介绍表中查找数据,从商品规格表中查找数据,从商品表中查找数据
            Product product = rewrite_commodityRepository.findAllProductById(id);
            if (!CheckUtils.checkObj(product))
                return Result.fail("服务繁忙,请稍后重试");
            return Result.suc("获取成功 ", product);
        }
    }

    /**
     * 根据传入的规格id和数量计算支付价格
     *
     * @param id
     * @param number
     * @return
     */
    public Result getAmout(Long id, Integer number) {
        if (!CheckUtils.checkLongByZero(id))
            return Result.fail("商品信息异常");
        else if (!CheckUtils.checkIntegerByZero(number))
            return Result.fail("购买数量异常");
        else {
            Optional<Specifications> byId = rewrite_SpecificationsRepository.findAmoutById(id);
            Specifications specifications = byId.get();
            return Result.suc("获取成功",Double.valueOf(specifications.getPrice()) * number);
        }
    }

    /**
     * 确认订单信息,找出规格,价格,名称
     * 如果是单商品,根据数量算出总价,并且将规格名称价格返回给前端
     * 如果是购物车结算,根据数量和商品价格算出总价,并且将规格名称价格返回给前端
     * @param rewrite_submitPaySumDTO
     * @return
     */
    public Result getOrderInfo(Rewrite_submitPaySumDTO rewrite_submitPaySumDTO) {
        if(!CheckUtils.checkObj(rewrite_submitPaySumDTO))
            return Result.fail("传入数据错误");
        else if (!CheckUtils.checkArray(rewrite_submitPaySumDTO.getIds()) && !CheckUtils.checkLongByZero(rewrite_submitPaySumDTO.getId()))
            return Result.fail("商品数据错误");
        else if (!CheckUtils.checkLongByZero(rewrite_submitPaySumDTO.getUserId()))
            return Result.fail("用户标识错误");
        else {
            Map<String, Object> data = new HashMap<>();
            BigDecimal totalAmount = new BigDecimal(0);
            //判断是购物车结算还是直接购买商品
            if( CheckUtils.checkArray(rewrite_submitPaySumDTO.getIds())){
                //购物车结算,直接根据数组去查询
                List<Map<String, String>> orderInfos = rewrite_ShoppingRepository.getOrderInfoByUserIdAndId(rewrite_submitPaySumDTO.getUserId(), rewrite_submitPaySumDTO.getIds());
                if(!CheckUtils.checkList(orderInfos))
                    return Result.fail();
                //然后计算总价
                for (Map<String, String> orderInfo : orderInfos) {
                    totalAmount = totalAmount.add(new BigDecimal(orderInfo.get("price")).multiply(new BigDecimal(orderInfo.get("number"))));
                }
                data.put("orderInfo",orderInfos);
                data.put("totalAmount",totalAmount);
            } else if (rewrite_submitPaySumDTO.getNumber() <= 0) {
                return Result.fail();
            } else {
                //直接购买根据id查询出商品信息
                //计算总价
                Map<String, Object> orderInfos = rewrite_SpecificationsRepository.findByIdToMap(rewrite_submitPaySumDTO.getId());
                HashMap<String, Object> orderInfo = new HashMap<>();
                orderInfo.putAll(orderInfos);
                System.out.println(orderInfo.values());
                System.out.println(orderInfo.size());
                if(orderInfo.size() == 0)
                    return Result.fail();
                totalAmount = new BigDecimal(orderInfo.get("price").toString()).multiply(new BigDecimal(rewrite_submitPaySumDTO.getNumber()));
                orderInfo.put("number",rewrite_submitPaySumDTO.getNumber().toString());
                data.put("totalAmount",totalAmount);
                data.put("orderInfo",orderInfo);
            }
            return Result.suc("获取成功",data);
        }
    }

    public Result getOrderInfoByOrderId(String ordreId) {
        if(!CheckUtils.checkString(ordreId))
            return Result.fail();
        else {
            Map<String, Object> data = new HashMap<>();
            BigDecimal totalAmout = new BigDecimal(0);
            List<Map<String, Object>> orderInfos = rewrite_SpecificationsRepository.findOrderInfoByOrderId(ordreId);
            System.out.println(orderInfos.size());
            for(int i = 0 ;i < orderInfos.size() ; i ++){
                totalAmout = new BigDecimal(Double.valueOf(orderInfos.get(i).get("price").toString()) * Double.valueOf(orderInfos.get(i).get("num").toString())).add(totalAmout);
                totalAmout = new BigDecimal(Double.valueOf(orderInfos.get(i).get("num").toString()) * Double.valueOf(orderInfos.get(i).get("postage").toString())).add(totalAmout);

            }
            data.put("orderInfo",orderInfos);
            data.put("totalAmout",totalAmout);
            return Result.suc("",data);
        }
    }
}
