package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.service.ClassificationService;
import com.weisen.www.code.yjf.shopmall.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.yjf.shopmall.service.dto.ClassificationDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.shopmall.domain.Classification}.
 */
@RestController
@RequestMapping("/api")
public class ClassificationResource {

    private final Logger log = LoggerFactory.getLogger(ClassificationResource.class);

    private static final String ENTITY_NAME = "shopmallClassification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClassificationService classificationService;

    public ClassificationResource(ClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    /**
     * {@code POST  /classifications} : Create a new classification.
     *
     * @param classificationDTO the classificationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new classificationDTO, or with status {@code 400 (Bad Request)} if the classification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/classifications")
    public ResponseEntity<ClassificationDTO> createClassification(@RequestBody ClassificationDTO classificationDTO) throws URISyntaxException {
        log.debug("REST request to save Classification : {}", classificationDTO);
        if (classificationDTO.getId() != null) {
            throw new BadRequestAlertException("A new classification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClassificationDTO result = classificationService.save(classificationDTO);
        return ResponseEntity.created(new URI("/api/classifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /classifications} : Updates an existing classification.
     *
     * @param classificationDTO the classificationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated classificationDTO,
     * or with status {@code 400 (Bad Request)} if the classificationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the classificationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/classifications")
    public ResponseEntity<ClassificationDTO> updateClassification(@RequestBody ClassificationDTO classificationDTO) throws URISyntaxException {
        log.debug("REST request to update Classification : {}", classificationDTO);
        if (classificationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClassificationDTO result = classificationService.save(classificationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, classificationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /classifications} : get all the classifications.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of classifications in body.
     */
    @GetMapping("/classifications")
    public ResponseEntity<List<ClassificationDTO>> getAllClassifications(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Classifications");
        Page<ClassificationDTO> page = classificationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /classifications/:id} : get the "id" classification.
     *
     * @param id the id of the classificationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the classificationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/classifications/{id}")
    public ResponseEntity<ClassificationDTO> getClassification(@PathVariable Long id) {
        log.debug("REST request to get Classification : {}", id);
        Optional<ClassificationDTO> classificationDTO = classificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(classificationDTO);
    }

    /**
     * {@code DELETE  /classifications/:id} : delete the "id" classification.
     *
     * @param id the id of the classificationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/classifications/{id}")
    public ResponseEntity<Void> deleteClassification(@PathVariable Long id) {
        log.debug("REST request to delete Classification : {}", id);
        classificationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
