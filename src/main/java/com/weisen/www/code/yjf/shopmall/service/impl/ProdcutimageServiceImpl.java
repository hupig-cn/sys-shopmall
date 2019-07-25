package com.weisen.www.code.yjf.shopmall.service.impl;

import com.weisen.www.code.yjf.shopmall.service.ProdcutimageService;
import com.weisen.www.code.yjf.shopmall.domain.Prodcutimage;
import com.weisen.www.code.yjf.shopmall.repository.ProdcutimageRepository;
import com.weisen.www.code.yjf.shopmall.service.dto.ProdcutimageDTO;
import com.weisen.www.code.yjf.shopmall.service.mapper.ProdcutimageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Prodcutimage.
 */
@Service
@Transactional
public class ProdcutimageServiceImpl implements ProdcutimageService {

    private final Logger log = LoggerFactory.getLogger(ProdcutimageServiceImpl.class);

    private final ProdcutimageRepository prodcutimageRepository;

    private final ProdcutimageMapper prodcutimageMapper;

    public ProdcutimageServiceImpl(ProdcutimageRepository prodcutimageRepository, ProdcutimageMapper prodcutimageMapper) {
        this.prodcutimageRepository = prodcutimageRepository;
        this.prodcutimageMapper = prodcutimageMapper;
    }

    /**
     * Save a prodcutimage.
     *
     * @param prodcutimageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProdcutimageDTO save(ProdcutimageDTO prodcutimageDTO) {
        log.debug("Request to save Prodcutimage : {}", prodcutimageDTO);
        Prodcutimage prodcutimage = prodcutimageMapper.toEntity(prodcutimageDTO);
        prodcutimage = prodcutimageRepository.save(prodcutimage);
        return prodcutimageMapper.toDto(prodcutimage);
    }

    /**
     * Get all the prodcutimages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProdcutimageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Prodcutimages");
        return prodcutimageRepository.findAll(pageable)
            .map(prodcutimageMapper::toDto);
    }


    /**
     * Get one prodcutimage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProdcutimageDTO> findOne(Long id) {
        log.debug("Request to get Prodcutimage : {}", id);
        return prodcutimageRepository.findById(id)
            .map(prodcutimageMapper::toDto);
    }

    /**
     * Delete the prodcutimage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Prodcutimage : {}", id);
        prodcutimageRepository.deleteById(id);
    }
}
