package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.service.CommodityService;
import com.weisen.www.code.yjf.shopmall.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.yjf.shopmall.service.dto.CommodityDTO;

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
 * REST controller for managing {@link com.weisen.www.code.yjf.shopmall.domain.Commodity}.
 */
@RestController
@RequestMapping("/api")
public class CommodityResource {

    private final Logger log = LoggerFactory.getLogger(CommodityResource.class);

    private static final String ENTITY_NAME = "shopmallCommodity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommodityService commodityService;

    public CommodityResource(CommodityService commodityService) {
        this.commodityService = commodityService;
    }

    /**
     * {@code POST  /commodities} : Create a new commodity.
     *
     * @param commodityDTO the commodityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new commodityDTO, or with status {@code 400 (Bad Request)} if the commodity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/commodities")
    public ResponseEntity<CommodityDTO> createCommodity(@RequestBody CommodityDTO commodityDTO) throws URISyntaxException {
        log.debug("REST request to save Commodity : {}", commodityDTO);
        if (commodityDTO.getId() != null) {
            throw new BadRequestAlertException("A new commodity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommodityDTO result = commodityService.save(commodityDTO);
        return ResponseEntity.created(new URI("/api/commodities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /commodities} : Updates an existing commodity.
     *
     * @param commodityDTO the commodityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commodityDTO,
     * or with status {@code 400 (Bad Request)} if the commodityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the commodityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/commodities")
    public ResponseEntity<CommodityDTO> updateCommodity(@RequestBody CommodityDTO commodityDTO) throws URISyntaxException {
        log.debug("REST request to update Commodity : {}", commodityDTO);
        if (commodityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CommodityDTO result = commodityService.save(commodityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, commodityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /commodities} : get all the commodities.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of commodities in body.
     */
    @GetMapping("/commodities")
    public ResponseEntity<List<CommodityDTO>> getAllCommodities(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Commodities");
        Page<CommodityDTO> page = commodityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /commodities/:id} : get the "id" commodity.
     *
     * @param id the id of the commodityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the commodityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/commodities/{id}")
    public ResponseEntity<CommodityDTO> getCommodity(@PathVariable Long id) {
        log.debug("REST request to get Commodity : {}", id);
        Optional<CommodityDTO> commodityDTO = commodityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(commodityDTO);
    }

    /**
     * {@code DELETE  /commodities/:id} : delete the "id" commodity.
     *
     * @param id the id of the commodityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/commodities/{id}")
    public ResponseEntity<Void> deleteCommodity(@PathVariable Long id) {
        log.debug("REST request to delete Commodity : {}", id);
        commodityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
