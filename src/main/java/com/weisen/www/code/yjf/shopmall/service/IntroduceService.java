package com.weisen.www.code.yjf.shopmall.service;

import com.weisen.www.code.yjf.shopmall.service.dto.IntroduceDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.shopmall.domain.Introduce}.
 */
public interface IntroduceService {

    /**
     * Save a introduce.
     *
     * @param introduceDTO the entity to save.
     * @return the persisted entity.
     */
    IntroduceDTO save(IntroduceDTO introduceDTO);

    /**
     * Get all the introduces.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<IntroduceDTO> findAll(Pageable pageable);


    /**
     * Get the "id" introduce.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<IntroduceDTO> findOne(Long id);

    /**
     * Delete the "id" introduce.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
