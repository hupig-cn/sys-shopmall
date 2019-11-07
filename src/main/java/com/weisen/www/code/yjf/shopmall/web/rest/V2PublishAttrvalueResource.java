package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.domain.V2PublishAttrvalue;
import com.weisen.www.code.yjf.shopmall.repository.V2PublishAttrvalueRepository;
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
 * REST controller for managing {@link com.weisen.www.code.yjf.shopmall.domain.V2PublishAttrvalue}.
 */
@RestController
@RequestMapping("/api")
public class V2PublishAttrvalueResource {

    private final Logger log = LoggerFactory.getLogger(V2PublishAttrvalueResource.class);

    private static final String ENTITY_NAME = "shopmallV2PublishAttrvalue";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final V2PublishAttrvalueRepository v2PublishAttrvalueRepository;

    public V2PublishAttrvalueResource(V2PublishAttrvalueRepository v2PublishAttrvalueRepository) {
        this.v2PublishAttrvalueRepository = v2PublishAttrvalueRepository;
    }

    /**
     * {@code POST  /v-2-publish-attrvalues} : Create a new v2PublishAttrvalue.
     *
     * @param v2PublishAttrvalue the v2PublishAttrvalue to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new v2PublishAttrvalue, or with status {@code 400 (Bad Request)} if the v2PublishAttrvalue has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/v-2-publish-attrvalues")
    public ResponseEntity<V2PublishAttrvalue> createV2PublishAttrvalue(@RequestBody V2PublishAttrvalue v2PublishAttrvalue) throws URISyntaxException {
        log.debug("REST request to save V2PublishAttrvalue : {}", v2PublishAttrvalue);
        if (v2PublishAttrvalue.getId() != null) {
            throw new BadRequestAlertException("A new v2PublishAttrvalue cannot already have an ID", ENTITY_NAME, "idexists");
        }
        V2PublishAttrvalue result = v2PublishAttrvalueRepository.save(v2PublishAttrvalue);
        return ResponseEntity.created(new URI("/api/v-2-publish-attrvalues/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /v-2-publish-attrvalues} : Updates an existing v2PublishAttrvalue.
     *
     * @param v2PublishAttrvalue the v2PublishAttrvalue to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated v2PublishAttrvalue,
     * or with status {@code 400 (Bad Request)} if the v2PublishAttrvalue is not valid,
     * or with status {@code 500 (Internal Server Error)} if the v2PublishAttrvalue couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/v-2-publish-attrvalues")
    public ResponseEntity<V2PublishAttrvalue> updateV2PublishAttrvalue(@RequestBody V2PublishAttrvalue v2PublishAttrvalue) throws URISyntaxException {
        log.debug("REST request to update V2PublishAttrvalue : {}", v2PublishAttrvalue);
        if (v2PublishAttrvalue.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        V2PublishAttrvalue result = v2PublishAttrvalueRepository.save(v2PublishAttrvalue);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, v2PublishAttrvalue.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /v-2-publish-attrvalues} : get all the v2PublishAttrvalues.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of v2PublishAttrvalues in body.
     */
    @GetMapping("/v-2-publish-attrvalues")
    public List<V2PublishAttrvalue> getAllV2PublishAttrvalues() {
        log.debug("REST request to get all V2PublishAttrvalues");
        return v2PublishAttrvalueRepository.findAll();
    }

    /**
     * {@code GET  /v-2-publish-attrvalues/:id} : get the "id" v2PublishAttrvalue.
     *
     * @param id the id of the v2PublishAttrvalue to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the v2PublishAttrvalue, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/v-2-publish-attrvalues/{id}")
    public ResponseEntity<V2PublishAttrvalue> getV2PublishAttrvalue(@PathVariable Long id) {
        log.debug("REST request to get V2PublishAttrvalue : {}", id);
        Optional<V2PublishAttrvalue> v2PublishAttrvalue = v2PublishAttrvalueRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(v2PublishAttrvalue);
    }

    /**
     * {@code DELETE  /v-2-publish-attrvalues/:id} : delete the "id" v2PublishAttrvalue.
     *
     * @param id the id of the v2PublishAttrvalue to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/v-2-publish-attrvalues/{id}")
    public ResponseEntity<Void> deleteV2PublishAttrvalue(@PathVariable Long id) {
        log.debug("REST request to delete V2PublishAttrvalue : {}", id);
        v2PublishAttrvalueRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
