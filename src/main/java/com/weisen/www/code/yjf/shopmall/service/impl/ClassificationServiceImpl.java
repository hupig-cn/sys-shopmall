package com.weisen.www.code.yjf.shopmall.service.impl;

import com.weisen.www.code.yjf.shopmall.service.ClassificationService;
import com.weisen.www.code.yjf.shopmall.domain.Classification;
import com.weisen.www.code.yjf.shopmall.repository.ClassificationRepository;
import com.weisen.www.code.yjf.shopmall.service.dto.ClassificationDTO;
import com.weisen.www.code.yjf.shopmall.service.mapper.ClassificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Classification}.
 */
@Service
@Transactional
public class ClassificationServiceImpl implements ClassificationService {

    private final Logger log = LoggerFactory.getLogger(ClassificationServiceImpl.class);

    private final ClassificationRepository classificationRepository;

    private final ClassificationMapper classificationMapper;

    public ClassificationServiceImpl(ClassificationRepository classificationRepository, ClassificationMapper classificationMapper) {
        this.classificationRepository = classificationRepository;
        this.classificationMapper = classificationMapper;
    }

    /**
     * Save a classification.
     *
     * @param classificationDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ClassificationDTO save(ClassificationDTO classificationDTO) {
        log.debug("Request to save Classification : {}", classificationDTO);
        Classification classification = classificationMapper.toEntity(classificationDTO);
        classification = classificationRepository.save(classification);
        return classificationMapper.toDto(classification);
    }

    /**
     * Get all the classifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ClassificationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Classifications");
        return classificationRepository.findAll(pageable)
            .map(classificationMapper::toDto);
    }


    /**
     * Get one classification by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ClassificationDTO> findOne(Long id) {
        log.debug("Request to get Classification : {}", id);
        return classificationRepository.findById(id)
            .map(classificationMapper::toDto);
    }

    /**
     * Delete the classification by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Classification : {}", id);
        classificationRepository.deleteById(id);
    }
}
