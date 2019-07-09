package com.weisen.www.code.yjf.shopmall.service.impl;

import com.weisen.www.code.yjf.shopmall.domain.Commodity;
import com.weisen.www.code.yjf.shopmall.domain.Specifications;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_CommodityRepository;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_SpecificationsRepository;
import com.weisen.www.code.yjf.shopmall.service.Rewrite_SpecificationsService;
import com.weisen.www.code.yjf.shopmall.service.dto.SpecificationsDTO;
import com.weisen.www.code.yjf.shopmall.service.mapper.CommodityMapper;
import com.weisen.www.code.yjf.shopmall.service.mapper.SpecificationsMapper;
import com.weisen.www.code.yjf.shopmall.service.util.Result;
import com.weisen.www.code.yjf.shopmall.service.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;

@Service
@Transactional
public class Rewrite_SpecificationsServiceImpl implements Rewrite_SpecificationsService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_ShoppingServiceImpl.class);

    private final Rewrite_SpecificationsRepository rewrite_SpecificationsRepository;

    private final SpecificationsMapper specificationsMapper;

    public Rewrite_SpecificationsServiceImpl(Rewrite_SpecificationsRepository rewrite_SpecificationsRepository, SpecificationsMapper specificationsMapper) {
        this.rewrite_SpecificationsRepository = rewrite_SpecificationsRepository;
        this.specificationsMapper = specificationsMapper;
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
        return Result.suc("成功",list);
    }

    @Override
    public Result findById(Long specificationsId) {
        Specifications specifications = rewrite_SpecificationsRepository.findById(specificationsId).get();
        return Result.suc("成功",specifications);
    }

}
