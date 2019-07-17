package com.weisen.www.code.yjf.shopmall.service;

import com.weisen.www.code.yjf.shopmall.service.dto.SpecificationsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Specifications.
 */
public interface SpecificationsService {

    /**
     * Save a specifications.
     *
     * @param specificationsDTO the entity to save
     * @return the persisted entity
     */
    SpecificationsDTO save(SpecificationsDTO specificationsDTO);

    /**
     * Get all the specifications.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SpecificationsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" specifications.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SpecificationsDTO> findOne(Long id);

    /**
     * Delete the "id" specifications.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
