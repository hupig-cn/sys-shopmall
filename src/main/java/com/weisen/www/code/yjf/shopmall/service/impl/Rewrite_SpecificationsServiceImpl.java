package com.weisen.www.code.yjf.shopmall.service.impl;

import com.weisen.www.code.yjf.shopmall.domain.Product;
import com.weisen.www.code.yjf.shopmall.domain.Specifications;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_CommodityRepository;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_SpecificationsRepository;
import com.weisen.www.code.yjf.shopmall.service.Rewrite_SpecificationsService;
import com.weisen.www.code.yjf.shopmall.service.dto.SpecificationsDTO;
import com.weisen.www.code.yjf.shopmall.service.mapper.SpecificationsMapper;
import com.weisen.www.code.yjf.shopmall.service.util.CheckUtils;
import com.weisen.www.code.yjf.shopmall.service.util.Result;
import com.weisen.www.code.yjf.shopmall.service.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class Rewrite_SpecificationsServiceImpl implements Rewrite_SpecificationsService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_ShoppingServiceImpl.class);

    private final Rewrite_SpecificationsRepository rewrite_SpecificationsRepository;

    private final SpecificationsMapper specificationsMapper;

    private final Rewrite_CommodityRepository rewrite_commodityRepository;

    public Rewrite_SpecificationsServiceImpl(Rewrite_SpecificationsRepository rewrite_SpecificationsRepository,
                                             SpecificationsMapper specificationsMapper, Rewrite_CommodityRepository rewrite_commodityRepository) {
        this.rewrite_SpecificationsRepository = rewrite_SpecificationsRepository;
        this.specificationsMapper = specificationsMapper;
        this.rewrite_commodityRepository = rewrite_commodityRepository;
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
            Optional<Specifications> byId = rewrite_SpecificationsRepository.findById(id);
            Specifications specifications = byId.get();
            return Result.suc("获取成功",Integer.valueOf(specifications.getPrice()) * number);
        }
    }
}
