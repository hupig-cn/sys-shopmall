package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.domain.CommoditySku;
import com.weisen.www.code.yjf.shopmall.repository.CommoditySkuRepository;
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
 * REST controller for managing {@link com.weisen.www.code.yjf.shopmall.domain.CommoditySku}.
 */
@RestController
@RequestMapping("/api")
public class CommoditySkuResource {

    private final Logger log = LoggerFactory.getLogger(CommoditySkuResource.class);

    private static final String ENTITY_NAME = "shopmallCommoditySku";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommoditySkuRepository commoditySkuRepository;

    public CommoditySkuResource(CommoditySkuRepository commoditySkuRepository) {
        this.commoditySkuRepository = commoditySkuRepository;
    }

    /**
     * {@code POST  /commodity-skus} : Create a new commoditySku.
     *
     * @param commoditySku the commoditySku to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new commoditySku, or with status {@code 400 (Bad Request)} if the commoditySku has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/commodity-skus")
    public ResponseEntity<CommoditySku> createCommoditySku(@RequestBody CommoditySku commoditySku) throws URISyntaxException {
        log.debug("REST request to save CommoditySku : {}", commoditySku);
        if (commoditySku.getId() != null) {
            throw new BadRequestAlertException("A new commoditySku cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommoditySku result = commoditySkuRepository.save(commoditySku);
        return ResponseEntity.created(new URI("/api/commodity-skus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /commodity-skus} : Updates an existing commoditySku.
     *
     * @param commoditySku the commoditySku to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commoditySku,
     * or with status {@code 400 (Bad Request)} if the commoditySku is not valid,
     * or with status {@code 500 (Internal Server Error)} if the commoditySku couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/commodity-skus")
    public ResponseEntity<CommoditySku> updateCommoditySku(@RequestBody CommoditySku commoditySku) throws URISyntaxException {
        log.debug("REST request to update CommoditySku : {}", commoditySku);
        if (commoditySku.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CommoditySku result = commoditySkuRepository.save(commoditySku);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, commoditySku.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /commodity-skus} : get all the commoditySkus.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of commoditySkus in body.
     */
    @GetMapping("/commodity-skus")
    public List<CommoditySku> getAllCommoditySkus() {
        log.debug("REST request to get all CommoditySkus");
        return commoditySkuRepository.findAll();
    }

    /**
     * {@code GET  /commodity-skus/:id} : get the "id" commoditySku.
     *
     * @param id the id of the commoditySku to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the commoditySku, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/commodity-skus/{id}")
    public ResponseEntity<CommoditySku> getCommoditySku(@PathVariable Long id) {
        log.debug("REST request to get CommoditySku : {}", id);
        Optional<CommoditySku> commoditySku = commoditySkuRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(commoditySku);
    }

    /**
     * {@code DELETE  /commodity-skus/:id} : delete the "id" commoditySku.
     *
     * @param id the id of the commoditySku to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/commodity-skus/{id}")
    public ResponseEntity<Void> deleteCommoditySku(@PathVariable Long id) {
        log.debug("REST request to delete CommoditySku : {}", id);
        commoditySkuRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
