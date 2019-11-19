package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.domain.CommodityAttr;
import com.weisen.www.code.yjf.shopmall.repository.CommodityAttrRepository;
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
 * REST controller for managing {@link com.weisen.www.code.yjf.shopmall.domain.CommodityAttr}.
 */
@RestController
@RequestMapping("/api")
public class CommodityAttrResource {

    private final Logger log = LoggerFactory.getLogger(CommodityAttrResource.class);

    private static final String ENTITY_NAME = "shopmallCommodityAttr";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommodityAttrRepository commodityAttrRepository;

    public CommodityAttrResource(CommodityAttrRepository commodityAttrRepository) {
        this.commodityAttrRepository = commodityAttrRepository;
    }

    /**
     * {@code POST  /commodity-attrs} : Create a new commodityAttr.
     *
     * @param commodityAttr the commodityAttr to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new commodityAttr, or with status {@code 400 (Bad Request)} if the commodityAttr has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/commodity-attrs")
    public ResponseEntity<CommodityAttr> createCommodityAttr(@RequestBody CommodityAttr commodityAttr) throws URISyntaxException {
        log.debug("REST request to save CommodityAttr : {}", commodityAttr);
        if (commodityAttr.getId() != null) {
            throw new BadRequestAlertException("A new commodityAttr cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommodityAttr result = commodityAttrRepository.save(commodityAttr);
        return ResponseEntity.created(new URI("/api/commodity-attrs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /commodity-attrs} : Updates an existing commodityAttr.
     *
     * @param commodityAttr the commodityAttr to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commodityAttr,
     * or with status {@code 400 (Bad Request)} if the commodityAttr is not valid,
     * or with status {@code 500 (Internal Server Error)} if the commodityAttr couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/commodity-attrs")
    public ResponseEntity<CommodityAttr> updateCommodityAttr(@RequestBody CommodityAttr commodityAttr) throws URISyntaxException {
        log.debug("REST request to update CommodityAttr : {}", commodityAttr);
        if (commodityAttr.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CommodityAttr result = commodityAttrRepository.save(commodityAttr);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, commodityAttr.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /commodity-attrs} : get all the commodityAttrs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of commodityAttrs in body.
     */
    @GetMapping("/commodity-attrs")
    public List<CommodityAttr> getAllCommodityAttrs() {
        log.debug("REST request to get all CommodityAttrs");
        return commodityAttrRepository.findAll();
    }

    /**
     * {@code GET  /commodity-attrs/:id} : get the "id" commodityAttr.
     *
     * @param id the id of the commodityAttr to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the commodityAttr, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/commodity-attrs/{id}")
    public ResponseEntity<CommodityAttr> getCommodityAttr(@PathVariable Long id) {
        log.debug("REST request to get CommodityAttr : {}", id);
        Optional<CommodityAttr> commodityAttr = commodityAttrRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(commodityAttr);
    }

    /**
     * {@code DELETE  /commodity-attrs/:id} : delete the "id" commodityAttr.
     *
     * @param id the id of the commodityAttr to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/commodity-attrs/{id}")
    public ResponseEntity<Void> deleteCommodityAttr(@PathVariable Long id) {
        log.debug("REST request to delete CommodityAttr : {}", id);
        commodityAttrRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
