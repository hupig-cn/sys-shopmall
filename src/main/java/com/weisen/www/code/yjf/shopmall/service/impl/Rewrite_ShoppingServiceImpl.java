package com.weisen.www.code.yjf.shopmall.service.impl;

import com.weisen.www.code.yjf.shopmall.domain.Shopping;
import com.weisen.www.code.yjf.shopmall.repository.ShoppingRepository;
import com.weisen.www.code.yjf.shopmall.service.Rewrite_ShoppingService;
import com.weisen.www.code.yjf.shopmall.service.mapper.ShoppingMapper;
import com.weisen.www.code.yjf.shopmall.service.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Shopping}.
 */
@Service
@Transactional
public class Rewrite_ShoppingServiceImpl implements Rewrite_ShoppingService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_ShoppingServiceImpl.class);

    private final ShoppingRepository shoppingRepository;

    private final ShoppingMapper shoppingMapper;

    public Rewrite_ShoppingServiceImpl(ShoppingRepository shoppingRepository, ShoppingMapper shoppingMapper) {
        this.shoppingRepository = shoppingRepository;
        this.shoppingMapper = shoppingMapper;
    }

    @Override
    public Result joinCar() {
        return null;
    }
}
