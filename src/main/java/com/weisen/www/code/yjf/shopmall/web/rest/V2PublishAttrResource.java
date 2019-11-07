package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.domain.V2PublishAttr;
import com.weisen.www.code.yjf.shopmall.repository.V2PublishAttrRepository;
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
 * REST controller for managing {@link com.weisen.www.code.yjf.shopmall.domain.V2PublishAttr}.
 */
@RestController
@RequestMapping("/api")
public class V2PublishAttrResource {

    private final Logger log = LoggerFactory.getLogger(V2PublishAttrResource.class);

    private static final String ENTITY_NAME = "shopmallV2PublishAttr";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final V2PublishAttrRepository v2PublishAttrRepository;

    public V2PublishAttrResource(V2PublishAttrRepository v2PublishAttrRepository) {
        this.v2PublishAttrRepository = v2PublishAttrRepository;
    }

    /**
     * {@code POST  /v-2-publish-attrs} : Create a new v2PublishAttr.
     *
     * @param v2PublishAttr the v2PublishAttr to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new v2PublishAttr, or with status {@code 400 (Bad Request)} if the v2PublishAttr has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/v-2-publish-attrs")
    public ResponseEntity<V2PublishAttr> createV2PublishAttr(@RequestBody V2PublishAttr v2PublishAttr) throws URISyntaxException {
        log.debug("REST request to save V2PublishAttr : {}", v2PublishAttr);
        if (v2PublishAttr.getId() != null) {
            throw new BadRequestAlertException("A new v2PublishAttr cannot already have an ID", ENTITY_NAME, "idexists");
        }
        V2PublishAttr result = v2PublishAttrRepository.save(v2PublishAttr);
        return ResponseEntity.created(new URI("/api/v-2-publish-attrs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /v-2-publish-attrs} : Updates an existing v2PublishAttr.
     *
     * @param v2PublishAttr the v2PublishAttr to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated v2PublishAttr,
     * or with status {@code 400 (Bad Request)} if the v2PublishAttr is not valid,
     * or with status {@code 500 (Internal Server Error)} if the v2PublishAttr couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/v-2-publish-attrs")
    public ResponseEntity<V2PublishAttr> updateV2PublishAttr(@RequestBody V2PublishAttr v2PublishAttr) throws URISyntaxException {
        log.debug("REST request to update V2PublishAttr : {}", v2PublishAttr);
        if (v2PublishAttr.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        V2PublishAttr result = v2PublishAttrRepository.save(v2PublishAttr);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, v2PublishAttr.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /v-2-publish-attrs} : get all the v2PublishAttrs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of v2PublishAttrs in body.
     */
    @GetMapping("/v-2-publish-attrs")
    public List<V2PublishAttr> getAllV2PublishAttrs() {
        log.debug("REST request to get all V2PublishAttrs");
        return v2PublishAttrRepository.findAll();
    }

    /**
     * {@code GET  /v-2-publish-attrs/:id} : get the "id" v2PublishAttr.
     *
     * @param id the id of the v2PublishAttr to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the v2PublishAttr, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/v-2-publish-attrs/{id}")
    public ResponseEntity<V2PublishAttr> getV2PublishAttr(@PathVariable Long id) {
        log.debug("REST request to get V2PublishAttr : {}", id);
        Optional<V2PublishAttr> v2PublishAttr = v2PublishAttrRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(v2PublishAttr);
    }

    /**
     * {@code DELETE  /v-2-publish-attrs/:id} : delete the "id" v2PublishAttr.
     *
     * @param id the id of the v2PublishAttr to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/v-2-publish-attrs/{id}")
    public ResponseEntity<Void> deleteV2PublishAttr(@PathVariable Long id) {
        log.debug("REST request to delete V2PublishAttr : {}", id);
        v2PublishAttrRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
