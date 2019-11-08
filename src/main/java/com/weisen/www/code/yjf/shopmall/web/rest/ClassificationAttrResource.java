package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.domain.ClassificationAttr;
import com.weisen.www.code.yjf.shopmall.repository.ClassificationAttrRepository;
import com.weisen.www.code.yjf.shopmall.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.shopmall.domain.ClassificationAttr}.
 */
@RestController
@RequestMapping("/api")
public class ClassificationAttrResource {

    private final Logger log = LoggerFactory.getLogger(ClassificationAttrResource.class);

    private static final String ENTITY_NAME = "shopmallClassificationAttr";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClassificationAttrRepository classificationAttrRepository;

    public ClassificationAttrResource(ClassificationAttrRepository classificationAttrRepository) {
        this.classificationAttrRepository = classificationAttrRepository;
    }

    /**
     * {@code POST  /classification-attrs} : Create a new classificationAttr.
     *
     * @param classificationAttr the classificationAttr to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new classificationAttr, or with status {@code 400 (Bad Request)} if the classificationAttr has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/classification-attrs")
    public ResponseEntity<ClassificationAttr> createClassificationAttr(@RequestBody ClassificationAttr classificationAttr) throws URISyntaxException {
        log.debug("REST request to save ClassificationAttr : {}", classificationAttr);
        if (classificationAttr.getId() != null) {
            throw new BadRequestAlertException("A new classificationAttr cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClassificationAttr result = classificationAttrRepository.save(classificationAttr);
        return ResponseEntity.created(new URI("/api/classification-attrs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /classification-attrs} : Updates an existing classificationAttr.
     *
     * @param classificationAttr the classificationAttr to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated classificationAttr,
     * or with status {@code 400 (Bad Request)} if the classificationAttr is not valid,
     * or with status {@code 500 (Internal Server Error)} if the classificationAttr couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/classification-attrs")
    public ResponseEntity<ClassificationAttr> updateClassificationAttr(@RequestBody ClassificationAttr classificationAttr) throws URISyntaxException {
        log.debug("REST request to update ClassificationAttr : {}", classificationAttr);
        if (classificationAttr.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClassificationAttr result = classificationAttrRepository.save(classificationAttr);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, classificationAttr.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /classification-attrs} : get all the classificationAttrs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of classificationAttrs in body.
     */
    @GetMapping("/classification-attrs")
    public List<ClassificationAttr> getAllClassificationAttrs() {
        log.debug("REST request to get all ClassificationAttrs");
        return classificationAttrRepository.findAll();
    }

    /**
     * {@code GET  /classification-attrs/:id} : get the "id" classificationAttr.
     *
     * @param id the id of the classificationAttr to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the classificationAttr, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/classification-attrs/{id}")
    public ResponseEntity<ClassificationAttr> getClassificationAttr(@PathVariable Long id) {
        log.debug("REST request to get ClassificationAttr : {}", id);
        Optional<ClassificationAttr> classificationAttr = classificationAttrRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(classificationAttr);
    }

    /**
     * {@code DELETE  /classification-attrs/:id} : delete the "id" classificationAttr.
     *
     * @param id the id of the classificationAttr to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/classification-attrs/{id}")
    public ResponseEntity<Void> deleteClassificationAttr(@PathVariable Long id) {
        log.debug("REST request to delete ClassificationAttr : {}", id);
        classificationAttrRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
