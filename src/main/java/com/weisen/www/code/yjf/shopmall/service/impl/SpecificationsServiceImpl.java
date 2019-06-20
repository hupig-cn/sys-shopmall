package com.weisen.www.code.yjf.shopmall.service.impl;

import com.weisen.www.code.yjf.shopmall.service.SpecificationsService;
import com.weisen.www.code.yjf.shopmall.domain.Specifications;
import com.weisen.www.code.yjf.shopmall.repository.SpecificationsRepository;
import com.weisen.www.code.yjf.shopmall.service.dto.SpecificationsDTO;
import com.weisen.www.code.yjf.shopmall.service.mapper.SpecificationsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Specifications}.
 */
@Service
@Transactional
public class SpecificationsServiceImpl implements SpecificationsService {

    private final Logger log = LoggerFactory.getLogger(SpecificationsServiceImpl.class);

    private final SpecificationsRepository specificationsRepository;

    private final SpecificationsMapper specificationsMapper;

    public SpecificationsServiceImpl(SpecificationsRepository specificationsRepository, SpecificationsMapper specificationsMapper) {
        this.specificationsRepository = specificationsRepository;
        this.specificationsMapper = specificationsMapper;
    }

    /**
     * Save a specifications.
     *
     * @param specificationsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SpecificationsDTO save(SpecificationsDTO specificationsDTO) {
        log.debug("Request to save Specifications : {}", specificationsDTO);
        Specifications specifications = specificationsMapper.toEntity(specificationsDTO);
        specifications = specificationsRepository.save(specifications);
        return specificationsMapper.toDto(specifications);
    }

    /**
     * Get all the specifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SpecificationsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Specifications");
        return specificationsRepository.findAll(pageable)
            .map(specificationsMapper::toDto);
    }


    /**
     * Get one specifications by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SpecificationsDTO> findOne(Long id) {
        log.debug("Request to get Specifications : {}", id);
        return specificationsRepository.findById(id)
            .map(specificationsMapper::toDto);
    }

    /**
     * Delete the specifications by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Specifications : {}", id);
        specificationsRepository.deleteById(id);
    }
}
