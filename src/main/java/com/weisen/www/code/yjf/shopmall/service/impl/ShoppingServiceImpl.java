package com.weisen.www.code.yjf.shopmall.service.impl;

import com.weisen.www.code.yjf.shopmall.service.ShoppingService;
import com.weisen.www.code.yjf.shopmall.domain.Shopping;
import com.weisen.www.code.yjf.shopmall.repository.ShoppingRepository;
import com.weisen.www.code.yjf.shopmall.service.dto.ShoppingDTO;
import com.weisen.www.code.yjf.shopmall.service.mapper.ShoppingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Shopping.
 */
@Service
@Transactional
public class ShoppingServiceImpl implements ShoppingService {

    private final Logger log = LoggerFactory.getLogger(ShoppingServiceImpl.class);

    private final ShoppingRepository shoppingRepository;

    private final ShoppingMapper shoppingMapper;

    public ShoppingServiceImpl(ShoppingRepository shoppingRepository, ShoppingMapper shoppingMapper) {
        this.shoppingRepository = shoppingRepository;
        this.shoppingMapper = shoppingMapper;
    }

    /**
     * Save a shopping.
     *
     * @param shoppingDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ShoppingDTO save(ShoppingDTO shoppingDTO) {
        log.debug("Request to save Shopping : {}", shoppingDTO);
        Shopping shopping = shoppingMapper.toEntity(shoppingDTO);
        shopping = shoppingRepository.save(shopping);
        return shoppingMapper.toDto(shopping);
    }

    /**
     * Get all the shoppings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ShoppingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Shoppings");
        return shoppingRepository.findAll(pageable)
            .map(shoppingMapper::toDto);
    }


    /**
     * Get one shopping by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ShoppingDTO> findOne(Long id) {
        log.debug("Request to get Shopping : {}", id);
        return shoppingRepository.findById(id)
            .map(shoppingMapper::toDto);
    }

    /**
     * Delete the shopping by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Shopping : {}", id);
        shoppingRepository.deleteById(id);
    }
}
