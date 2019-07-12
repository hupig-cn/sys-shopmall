package com.weisen.www.code.yjf.shopmall.service;

import com.weisen.www.code.yjf.shopmall.service.dto.ProdcutimageDTO;
import com.weisen.www.code.yjf.shopmall.service.util.Result;

public interface Rewrite_ProdcutimageService {

    // 创建商品规格图片
    Result createImage(ProdcutimageDTO prodcutimageDTO);

    // 查询商品规格的图片
    Result findAllBySpecifications(Long specificationsid);

    // 修改商品规格的图片
    Result updateImage(ProdcutimageDTO prodcutimageDTO);

    // 删除商品规格的图片
    Result deleteImage(Long imageId);
}
