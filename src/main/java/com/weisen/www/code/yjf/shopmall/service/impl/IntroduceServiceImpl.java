package com.weisen.www.code.yjf.shopmall.service.impl;

import com.weisen.www.code.yjf.shopmall.service.IntroduceService;
import com.weisen.www.code.yjf.shopmall.domain.Introduce;
import com.weisen.www.code.yjf.shopmall.repository.IntroduceRepository;
import com.weisen.www.code.yjf.shopmall.service.dto.IntroduceDTO;
import com.weisen.www.code.yjf.shopmall.service.mapper.IntroduceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Introduce}.
 */
@Service
@Transactional
public class IntroduceServiceImpl implements IntroduceService {

    private final Logger log = LoggerFactory.getLogger(IntroduceServiceImpl.class);

    private final IntroduceRepository introduceRepository;

    private final IntroduceMapper introduceMapper;

    public IntroduceServiceImpl(IntroduceRepository introduceRepository, IntroduceMapper introduceMapper) {
        this.introduceRepository = introduceRepository;
        this.introduceMapper = introduceMapper;
    }

    /**
     * Save a introduce.
     *
     * @param introduceDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public IntroduceDTO save(IntroduceDTO introduceDTO) {
        log.debug("Request to save Introduce : {}", introduceDTO);
        Introduce introduce = introduceMapper.toEntity(introduceDTO);
        introduce = introduceRepository.save(introduce);
        return introduceMapper.toDto(introduce);
    }

    /**
     * Get all the introduces.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<IntroduceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Introduces");
        return introduceRepository.findAll(pageable)
            .map(introduceMapper::toDto);
    }


    /**
     * Get one introduce by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<IntroduceDTO> findOne(Long id) {
        log.debug("Request to get Introduce : {}", id);
        return introduceRepository.findById(id)
            .map(introduceMapper::toDto);
    }

    /**
     * Delete the introduce by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Introduce : {}", id);
        introduceRepository.deleteById(id);
    }
}
