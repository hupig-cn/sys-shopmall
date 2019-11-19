package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.domain.V2OrderExtends;
import com.weisen.www.code.yjf.shopmall.repository.V2OrderExtendsRepository;
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
 * REST controller for managing {@link com.weisen.www.code.yjf.shopmall.domain.V2OrderExtends}.
 */
@RestController
@RequestMapping("/api")
public class V2OrderExtendsResource {

    private final Logger log = LoggerFactory.getLogger(V2OrderExtendsResource.class);

    private static final String ENTITY_NAME = "shopmallV2OrderExtends";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final V2OrderExtendsRepository v2OrderExtendsRepository;

    public V2OrderExtendsResource(V2OrderExtendsRepository v2OrderExtendsRepository) {
        this.v2OrderExtendsRepository = v2OrderExtendsRepository;
    }

    /**
     * {@code POST  /v-2-order-extends} : Create a new v2OrderExtends.
     *
     * @param v2OrderExtends the v2OrderExtends to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new v2OrderExtends, or with status {@code 400 (Bad Request)} if the v2OrderExtends has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/v-2-order-extends")
    public ResponseEntity<V2OrderExtends> createV2OrderExtends(@RequestBody V2OrderExtends v2OrderExtends) throws URISyntaxException {
        log.debug("REST request to save V2OrderExtends : {}", v2OrderExtends);
        if (v2OrderExtends.getId() != null) {
            throw new BadRequestAlertException("A new v2OrderExtends cannot already have an ID", ENTITY_NAME, "idexists");
        }
        V2OrderExtends result = v2OrderExtendsRepository.save(v2OrderExtends);
        return ResponseEntity.created(new URI("/api/v-2-order-extends/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /v-2-order-extends} : Updates an existing v2OrderExtends.
     *
     * @param v2OrderExtends the v2OrderExtends to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated v2OrderExtends,
     * or with status {@code 400 (Bad Request)} if the v2OrderExtends is not valid,
     * or with status {@code 500 (Internal Server Error)} if the v2OrderExtends couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/v-2-order-extends")
    public ResponseEntity<V2OrderExtends> updateV2OrderExtends(@RequestBody V2OrderExtends v2OrderExtends) throws URISyntaxException {
        log.debug("REST request to update V2OrderExtends : {}", v2OrderExtends);
        if (v2OrderExtends.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        V2OrderExtends result = v2OrderExtendsRepository.save(v2OrderExtends);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, v2OrderExtends.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /v-2-order-extends} : get all the v2OrderExtends.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of v2OrderExtends in body.
     */
    @GetMapping("/v-2-order-extends")
    public List<V2OrderExtends> getAllV2OrderExtends() {
        log.debug("REST request to get all V2OrderExtends");
        return v2OrderExtendsRepository.findAll();
    }

    /**
     * {@code GET  /v-2-order-extends/:id} : get the "id" v2OrderExtends.
     *
     * @param id the id of the v2OrderExtends to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the v2OrderExtends, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/v-2-order-extends/{id}")
    public ResponseEntity<V2OrderExtends> getV2OrderExtends(@PathVariable Long id) {
        log.debug("REST request to get V2OrderExtends : {}", id);
        Optional<V2OrderExtends> v2OrderExtends = v2OrderExtendsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(v2OrderExtends);
    }

    /**
     * {@code DELETE  /v-2-order-extends/:id} : delete the "id" v2OrderExtends.
     *
     * @param id the id of the v2OrderExtends to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/v-2-order-extends/{id}")
    public ResponseEntity<Void> deleteV2OrderExtends(@PathVariable Long id) {
        log.debug("REST request to delete V2OrderExtends : {}", id);
        v2OrderExtendsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
