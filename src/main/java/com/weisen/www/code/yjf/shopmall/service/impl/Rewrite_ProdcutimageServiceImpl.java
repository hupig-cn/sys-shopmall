package com.weisen.www.code.yjf.shopmall.service.impl;

import com.weisen.www.code.yjf.shopmall.repository.Rewrite_OrderRepository;
import com.weisen.www.code.yjf.shopmall.service.Rewrite_ProdcutimageService;
import com.weisen.www.code.yjf.shopmall.service.dto.ProdcutimageDTO;
import com.weisen.www.code.yjf.shopmall.service.mapper.OrderMapper;
import com.weisen.www.code.yjf.shopmall.service.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Rewrite_ProdcutimageServiceImpl implements Rewrite_ProdcutimageService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_ProdcutimageServiceImpl.class);

    private final Rewrite_OrderRepository rewrite_OrderRepository;

    private final OrderMapper orderMapper;

    public Rewrite_ProdcutimageServiceImpl(Rewrite_OrderRepository rewrite_OrderRepository, OrderMapper orderMapper) {
        this.rewrite_OrderRepository = rewrite_OrderRepository;
        this.orderMapper = orderMapper;
    }


    @Override
    public Result createImage(ProdcutimageDTO prodcutimageDTO) {
        return null;
    }

    @Override
    public Result findAllBySpecifications(Long specificationsid) {
        return null;
    }

    @Override
    public Result updateImage(ProdcutimageDTO prodcutimageDTO) {
        return null;
    }

    @Override
    public Result deleteImage(Long imageId) {
        return null;
    }
}
