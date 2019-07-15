package com.weisen.www.code.yjf.shopmall.web.rest;
import com.weisen.www.code.yjf.shopmall.service.ProdcutimageService;
import com.weisen.www.code.yjf.shopmall.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.yjf.shopmall.service.dto.ProdcutimageDTO;
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
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Prodcutimage.
 */
@RestController
@RequestMapping("/api")
public class ProdcutimageResource {

    private final Logger log = LoggerFactory.getLogger(ProdcutimageResource.class);

    private static final String ENTITY_NAME = "shopmallProdcutimage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProdcutimageService prodcutimageService;

    public ProdcutimageResource(ProdcutimageService prodcutimageService) {
        this.prodcutimageService = prodcutimageService;
    }

    /**
     * POST  /prodcutimages : Create a new prodcutimage.
     *
     * @param prodcutimageDTO the prodcutimageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new prodcutimageDTO, or with status 400 (Bad Request) if the prodcutimage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/prodcutimages")
    public ResponseEntity<ProdcutimageDTO> createProdcutimage(@RequestBody ProdcutimageDTO prodcutimageDTO) throws URISyntaxException {
        log.debug("REST request to save Prodcutimage : {}", prodcutimageDTO);
        if (prodcutimageDTO.getId() != null) {
            throw new BadRequestAlertException("A new prodcutimage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProdcutimageDTO result = prodcutimageService.save(prodcutimageDTO);
        return ResponseEntity.created(new URI("/api/prodcutimages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /prodcutimages : Updates an existing prodcutimage.
     *
     * @param prodcutimageDTO the prodcutimageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated prodcutimageDTO,
     * or with status 400 (Bad Request) if the prodcutimageDTO is not valid,
     * or with status 500 (Internal Server Error) if the prodcutimageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/prodcutimages")
    public ResponseEntity<ProdcutimageDTO> updateProdcutimage(@RequestBody ProdcutimageDTO prodcutimageDTO) throws URISyntaxException {
        log.debug("REST request to update Prodcutimage : {}", prodcutimageDTO);
        if (prodcutimageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProdcutimageDTO result = prodcutimageService.save(prodcutimageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, prodcutimageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /prodcutimages : get all the prodcutimages.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prodcutimages in body
     */
    @GetMapping("/prodcutimages")
    public ResponseEntity<List<ProdcutimageDTO>> getAllProdcutimages(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Prodcutimages");
        Page<ProdcutimageDTO> page = prodcutimageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /prodcutimages/:id : get the "id" prodcutimage.
     *
     * @param id the id of the prodcutimageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prodcutimageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/prodcutimages/{id}")
    public ResponseEntity<ProdcutimageDTO> getProdcutimage(@PathVariable Long id) {
        log.debug("REST request to get Prodcutimage : {}", id);
        Optional<ProdcutimageDTO> prodcutimageDTO = prodcutimageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(prodcutimageDTO);
    }

    /**
     * DELETE  /prodcutimages/:id : delete the "id" prodcutimage.
     *
     * @param id the id of the prodcutimageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/prodcutimages/{id}")
    public ResponseEntity<Void> deleteProdcutimage(@PathVariable Long id) {
        log.debug("REST request to delete Prodcutimage : {}", id);
        prodcutimageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
