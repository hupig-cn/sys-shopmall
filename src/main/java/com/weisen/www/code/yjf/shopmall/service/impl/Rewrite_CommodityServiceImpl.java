package com.weisen.www.code.yjf.shopmall.service.impl;

import com.weisen.www.code.yjf.shopmall.domain.Commodity;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_CommodityRepository;
import com.weisen.www.code.yjf.shopmall.service.Rewrite_CommodityService;
import com.weisen.www.code.yjf.shopmall.service.dto.CommodityDTO;
import com.weisen.www.code.yjf.shopmall.service.mapper.CommodityMapper;
import com.weisen.www.code.yjf.shopmall.service.util.Result;
import com.weisen.www.code.yjf.shopmall.service.util.TimeUtil;
import com.weisen.www.code.yjf.shopmall.service.util.UsuallyConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class Rewrite_CommodityServiceImpl implements Rewrite_CommodityService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_ShoppingServiceImpl.class);

    private final Rewrite_CommodityRepository rewrite_CommodityRepository;

    private final CommodityMapper commodityMapper;

    public Rewrite_CommodityServiceImpl(Rewrite_CommodityRepository rewrite_CommodityRepository, CommodityMapper commodityMapper) {
        this.rewrite_CommodityRepository = rewrite_CommodityRepository;
        this.commodityMapper = commodityMapper;
    }

    //获取全部商品列表
    @Override
    public Result getAllCommodity() {
        List<Commodity> list = rewrite_CommodityRepository.findAll();
        // 按需求来  如果单纯获取的话就太多了
        return Result.suc("成功",commodityMapper.toDto(list));
    }

    //添加商品
    @Override
    public void createCommodity(CommodityDTO commodityDTO) {
        Commodity commodity = new Commodity();
        commodity.setBrandid(commodityDTO.getBrandid());
        commodity.setName(commodityDTO.getName());
        commodity.setClassificationid(commodityDTO.getClassificationid());
        commodity.setCommoditystate(commodityDTO.getCommoditystate());
        commodity.setCreatedate(TimeUtil.getDate());
        commodity.setPostage(commodityDTO.getPostage());
        commodity.setSalevalue(commodityDTO.getSalevalue());
        commodity.setWeight(commodityDTO.getWeight());
        commodity.setCreator("");
        commodity.setLogicdelete(false);
        commodity.setOther("");
        rewrite_CommodityRepository.save(commodity);
    }

    // 修改商品信息
    @Override
    public void updateCommodity(CommodityDTO commodityDTO) {
        Commodity commodity = new Commodity();
        commodity.setId(commodityDTO.getId());
        commodity.setBrandid(commodityDTO.getBrandid());
        commodity.setName(commodityDTO.getName());
        commodity.setClassificationid(commodityDTO.getClassificationid());
        commodity.setCommoditystate(commodityDTO.getCommoditystate());
        commodity.setPostage(commodityDTO.getPostage());
        commodity.setSalevalue(commodityDTO.getSalevalue());
        commodity.setWeight(commodityDTO.getWeight());
        commodity.setModifierdate(TimeUtil.getDate());
        commodity.setLogicdelete(false);
        commodity.setOther("");
        rewrite_CommodityRepository.saveAndFlush(commodity);
    }

    // 商品上架
    @Override
    public Result CommodityGetUp(Long commodityId) {
        Optional p = rewrite_CommodityRepository.findById(commodityId);
        if(!p.isPresent()){
            return Result.fail("商品不存在");
        }
        Commodity commodity = rewrite_CommodityRepository.findById(commodityId).get();
        commodity.setCommoditystate(UsuallyConstant.ONE);
        rewrite_CommodityRepository.saveAndFlush(commodity);
        return Result.suc("成功");
    }

    // 商品下架
    @Override
    public Result CommodityGetDown(Long commodityId) {
        Optional p = rewrite_CommodityRepository.findById(commodityId);
        if(!p.isPresent()){
            return Result.fail("商品不存在");
        }
        Commodity commodity = rewrite_CommodityRepository.findById(commodityId).get();
        commodity.setCommoditystate(UsuallyConstant.TWO);
        rewrite_CommodityRepository.saveAndFlush(commodity);
        return Result.suc("成功");
    }

    // 删除商品
    @Override
    public Result deleteCommodity(Long commodityId) {
        rewrite_CommodityRepository.deleteById(commodityId);
        // 关联的数据也需要删除
        return Result.suc("成功");
    }

    // 批量删除
    @Override
    public Result deleteListCommodity(List<Long> commodityId) {
        for (Long c: commodityId) {
            rewrite_CommodityRepository.deleteById(c);
        }
        return Result.suc("成功");
    }

    // 查看商品详情
    @Override
    public Result findCommodityInfo(Long commodityId) {
        Optional p = rewrite_CommodityRepository.findById(commodityId);
        if(!p.isPresent()){
            return Result.fail("商品不存在");
        }
        Commodity commodity = rewrite_CommodityRepository.findById(commodityId).get();
        return Result.suc("成功",commodityMapper.toDto(commodity));
    }


}
