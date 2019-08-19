package com.weisen.www.code.yjf.shopmall.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.shopmall.domain.Prodcutimage;
import com.weisen.www.code.yjf.shopmall.repository.Reweite_ProdcutimageRepository;
import com.weisen.www.code.yjf.shopmall.service.Rewrite_ProdcutimageService;
import com.weisen.www.code.yjf.shopmall.service.dto.ProdcutimageDTO;
import com.weisen.www.code.yjf.shopmall.service.mapper.ProdcutimageMapper;
import com.weisen.www.code.yjf.shopmall.service.util.Result;
import com.weisen.www.code.yjf.shopmall.service.util.TimeUtil;

@Service
@Transactional
public class Rewrite_ProdcutimageServiceImpl implements Rewrite_ProdcutimageService {

    private final Reweite_ProdcutimageRepository reweite_ProdcutimageRepository;

    private final ProdcutimageMapper prodcutimageMapper;

    public Rewrite_ProdcutimageServiceImpl(Reweite_ProdcutimageRepository reweite_ProdcutimageRepository,ProdcutimageMapper prodcutimageMapper) {
        this.reweite_ProdcutimageRepository = reweite_ProdcutimageRepository;
        this.prodcutimageMapper = prodcutimageMapper;
    }

    // 创建商品规格图片
    @Override
    public Result createImage(ProdcutimageDTO prodcutimageDTO) {
        Prodcutimage prodcutimage = new Prodcutimage();
        prodcutimage.setSpecificationsid(prodcutimageDTO.getSpecificationsid());
        prodcutimage.setFileid(prodcutimageDTO.getFileid());
        prodcutimage.setOther(prodcutimageDTO.getOther());
        prodcutimage.setType(prodcutimageDTO.getType());
        prodcutimage.setState(prodcutimageDTO.getState());
        prodcutimage.setCreatedate(TimeUtil.getDate());
        prodcutimage.setCreator("");
        prodcutimage.setLogicdelete(false);
        reweite_ProdcutimageRepository.save(prodcutimage);
        return Result.suc("成功");
    }

    // 查询商品规格的图片
    @Override
    public Result findAllBySpecifications(Long specificationsid) {
        System.out.println(specificationsid);
        List<Prodcutimage> list = reweite_ProdcutimageRepository.findAllBySpecificationsid(specificationsid);
        System.out.println(list);
        return Result.suc("成功",prodcutimageMapper.toDto(list));
    }

    // 修改商品规格的图片
    @Override
    public Result updateImage(ProdcutimageDTO prodcutimageDTO) {
        Prodcutimage prodcutimage = new Prodcutimage();
        prodcutimage.setId(prodcutimageDTO.getId());
        prodcutimage.setSpecificationsid(prodcutimageDTO.getSpecificationsid());
        prodcutimage.setFileid(prodcutimageDTO.getFileid());
        prodcutimage.setOther(prodcutimageDTO.getOther());
        prodcutimage.setType(prodcutimageDTO.getType());
        prodcutimage.setState(prodcutimageDTO.getState());
//        prodcutimage.setCreatedate(TimeUtil.getDate());
//        prodcutimage.setCreator("");
        prodcutimage.setModifierdate(TimeUtil.getDate());
        prodcutimage.setModifier("");
        prodcutimage.setLogicdelete(false);
        reweite_ProdcutimageRepository.save(prodcutimage);
        return Result.suc("成功");
    }

    // 删除商品规格的图片
    @Override
    public Result deleteImage(Long imageId) {
        reweite_ProdcutimageRepository.deleteById(imageId);
        return Result.suc("成功");
    }
}
