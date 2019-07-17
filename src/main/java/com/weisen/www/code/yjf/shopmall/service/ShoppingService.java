package com.weisen.www.code.yjf.shopmall.service;

import com.weisen.www.code.yjf.shopmall.service.dto.ShoppingDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Shopping.
 */
public interface ShoppingService {

    /**
     * Save a shopping.
     *
     * @param shoppingDTO the entity to save
     * @return the persisted entity
     */
    ShoppingDTO save(ShoppingDTO shoppingDTO);

    /**
     * Get all the shoppings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ShoppingDTO> findAll(Pageable pageable);


    /**
     * Get the "id" shopping.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ShoppingDTO> findOne(Long id);

    /**
     * Delete the "id" shopping.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
