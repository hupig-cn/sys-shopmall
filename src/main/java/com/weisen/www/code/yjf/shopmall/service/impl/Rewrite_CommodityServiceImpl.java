package com.weisen.www.code.yjf.shopmall.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.shopmall.domain.Commodity;
import com.weisen.www.code.yjf.shopmall.domain.Specifications;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_CommodityRepository;
import com.weisen.www.code.yjf.shopmall.repository.Rewrite_SpecificationsRepository;
import com.weisen.www.code.yjf.shopmall.service.Rewrite_CommodityService;
import com.weisen.www.code.yjf.shopmall.service.dto.CommodityDTO;
import com.weisen.www.code.yjf.shopmall.service.dto.Rewrite_ForNearShop;
import com.weisen.www.code.yjf.shopmall.service.dto.showdto.Rewrite_ShowCom;
import com.weisen.www.code.yjf.shopmall.service.mapper.SpecificationsMapper;
import com.weisen.www.code.yjf.shopmall.service.util.CheckUtils;
import com.weisen.www.code.yjf.shopmall.service.util.Result;
import com.weisen.www.code.yjf.shopmall.service.util.TimeUtil;
import com.weisen.www.code.yjf.shopmall.service.util.UsuallyConstant;

@Service
@Transactional
public class Rewrite_CommodityServiceImpl implements Rewrite_CommodityService {

    private final Rewrite_CommodityRepository rewrite_CommodityRepository;

    private final Rewrite_SpecificationsRepository rewrite_SpecificationsRepository;

    private final SpecificationsMapper specificationsMapper;

    public Rewrite_CommodityServiceImpl(Rewrite_CommodityRepository rewrite_CommodityRepository
        ,Rewrite_SpecificationsRepository rewrite_SpecificationsRepository,SpecificationsMapper specificationsMapper) {
        this.rewrite_CommodityRepository = rewrite_CommodityRepository;
        this.rewrite_SpecificationsRepository = rewrite_SpecificationsRepository;
        this.specificationsMapper = specificationsMapper;
    }

    //获取全部商品列表
    @Override
    public Result getAllCommodity(Rewrite_ForNearShop rewrite_ForNearShop) {
        // 校验参数
        int fromIndex = rewrite_ForNearShop.getPageNum() * rewrite_ForNearShop.getPageSize();
        List<Commodity> list = rewrite_CommodityRepository.getAllByName(rewrite_ForNearShop.getName(),fromIndex,rewrite_ForNearShop.getPageSize());
        List<Rewrite_ShowCom> show = new ArrayList<>();
        for (Commodity x:list) {
            List<Specifications> specifications = rewrite_SpecificationsRepository.findAllByCommodityid(x.getId().toString());
            Rewrite_ShowCom showcom = new Rewrite_ShowCom(x,specificationsMapper.toDto(specifications));
            show.add(showcom);
            showcom = null;
        }
        return Result.suc("成功",show);
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
        Optional<?> p = rewrite_CommodityRepository.findById(commodityId);
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
        Optional<?> p = rewrite_CommodityRepository.findById(commodityId);
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
    public Result findCommodityInfo(Long speId) {
        Map<String, Object> data = rewrite_CommodityRepository.findProdcutDetailBySpecificationsId(speId);
        if(0 == data.size())
            return Result.fail("");
        return Result.suc("成功",data);
    }

    // 根据销量查询商品
    @Override
    public Result findAllBySales() {

        return null;
    }

    // // 根据最新时间查询商品
    @Override
    public Result findAllByTime(Rewrite_ForNearShop rewrite_ForNearShop) {
        if(!CheckUtils.checkPageInfo(rewrite_ForNearShop.getPageNum(),rewrite_ForNearShop.getPageSize()))
            return Result.fail("分页信息错误");
        else {
            List<Map<String, Object>> allProduct = rewrite_CommodityRepository.findAllProduct(rewrite_ForNearShop.getPageNum() * rewrite_ForNearShop.getPageSize(),rewrite_ForNearShop.getPageSize());
            if(null ==  allProduct || 0 == allProduct.size())
                return Result.fail("网络繁忙请稍后重试");
            return Result.suc("获取成功",allProduct);
        }
//        int fromIndex = rewrite_ForNearShop.getPageNum() * rewrite_ForNearShop.getPageSize();
//        List<Commodity> com = rewrite_CommodityRepository.getAllByTime(fromIndex,rewrite_ForNearShop.getPageSize());
//        List<Rewrite_ShowCom> show = new ArrayList<>();
//        for (Commodity x:com) {
//            List<Specifications> specifications = rewrite_SpecificationsRepository.findAllByCommodityid(x.getId().toString());
//            Rewrite_ShowCom showcom = new Rewrite_ShowCom(x,specificationsMapper.toDto(specifications));
//            show.add(showcom);
//            showcom = null;
//        }
//        return Result.suc("成功",show);
    }

    @Override
    public Result findAllByContent(Rewrite_ForNearShop rewrite_ForNearShop) {
        if(!CheckUtils.checkPageInfo(rewrite_ForNearShop.getPageNum(),rewrite_ForNearShop.getPageSize()))
            return Result.fail("分页信息错误");
        else {
            List<Map<String, Object>> allProduct = rewrite_CommodityRepository.findAllProductByconent(rewrite_ForNearShop.getPageNum() * rewrite_ForNearShop.getPageSize(), rewrite_ForNearShop.getPageSize(),rewrite_ForNearShop.getName());
            if(null ==  allProduct || 0 == allProduct.size())
                return Result.fail("网络繁忙请稍后重试");
            return Result.suc("获取成功",allProduct);
        }
    }
}
