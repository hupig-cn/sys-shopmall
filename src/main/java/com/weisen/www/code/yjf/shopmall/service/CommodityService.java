package com.weisen.www.code.yjf.shopmall.service;

import com.weisen.www.code.yjf.shopmall.service.dto.CommodityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Commodity.
 */
public interface CommodityService {

    /**
     * Save a commodity.
     *
     * @param commodityDTO the entity to save
     * @return the persisted entity
     */
    CommodityDTO save(CommodityDTO commodityDTO);

    /**
     * Get all the commodities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CommodityDTO> findAll(Pageable pageable);


    /**
     * Get the "id" commodity.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CommodityDTO> findOne(Long id);

    /**
     * Delete the "id" commodity.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
