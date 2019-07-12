package com.weisen.www.code.yjf.shopmall.service;

import com.weisen.www.code.yjf.shopmall.service.dto.ProdcutimageDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Prodcutimage.
 */
public interface ProdcutimageService {

    /**
     * Save a prodcutimage.
     *
     * @param prodcutimageDTO the entity to save
     * @return the persisted entity
     */
    ProdcutimageDTO save(ProdcutimageDTO prodcutimageDTO);

    /**
     * Get all the prodcutimages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ProdcutimageDTO> findAll(Pageable pageable);


    /**
     * Get the "id" prodcutimage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ProdcutimageDTO> findOne(Long id);

    /**
     * Delete the "id" prodcutimage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
