package com.weisen.www.code.yjf.shopmall.service.impl;

import com.weisen.www.code.yjf.shopmall.service.CommodityService;
import com.weisen.www.code.yjf.shopmall.domain.Commodity;
import com.weisen.www.code.yjf.shopmall.repository.CommodityRepository;
import com.weisen.www.code.yjf.shopmall.service.dto.CommodityDTO;
import com.weisen.www.code.yjf.shopmall.service.mapper.CommodityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Commodity.
 */
@Service
@Transactional
public class CommodityServiceImpl implements CommodityService {

    private final Logger log = LoggerFactory.getLogger(CommodityServiceImpl.class);

    private final CommodityRepository commodityRepository;

    private final CommodityMapper commodityMapper;

    public CommodityServiceImpl(CommodityRepository commodityRepository, CommodityMapper commodityMapper) {
        this.commodityRepository = commodityRepository;
        this.commodityMapper = commodityMapper;
    }

    /**
     * Save a commodity.
     *
     * @param commodityDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CommodityDTO save(CommodityDTO commodityDTO) {
        log.debug("Request to save Commodity : {}", commodityDTO);
        Commodity commodity = commodityMapper.toEntity(commodityDTO);
        commodity = commodityRepository.save(commodity);
        return commodityMapper.toDto(commodity);
    }

    /**
     * Get all the commodities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CommodityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Commodities");
        return commodityRepository.findAll(pageable)
            .map(commodityMapper::toDto);
    }


    /**
     * Get one commodity by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CommodityDTO> findOne(Long id) {
        log.debug("Request to get Commodity : {}", id);
        return commodityRepository.findById(id)
            .map(commodityMapper::toDto);
    }

    /**
     * Delete the commodity by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Commodity : {}", id);        commodityRepository.deleteById(id);
    }
}
