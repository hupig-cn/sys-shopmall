package com.weisen.www.code.yjf.shopmall.web.rest;
import com.weisen.www.code.yjf.shopmall.service.ShoppingService;
import com.weisen.www.code.yjf.shopmall.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.yjf.shopmall.service.dto.ShoppingDTO;
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
 * REST controller for managing Shopping.
 */
@RestController
@RequestMapping("/api")
public class ShoppingResource {

    private final Logger log = LoggerFactory.getLogger(ShoppingResource.class);

    private static final String ENTITY_NAME = "shopmallShopping";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ShoppingService shoppingService;

    public ShoppingResource(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    /**
     * POST  /shoppings : Create a new shopping.
     *
     * @param shoppingDTO the shoppingDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new shoppingDTO, or with status 400 (Bad Request) if the shopping has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/shoppings")
    public ResponseEntity<ShoppingDTO> createShopping(@RequestBody ShoppingDTO shoppingDTO) throws URISyntaxException {
        log.debug("REST request to save Shopping : {}", shoppingDTO);
        if (shoppingDTO.getId() != null) {
            throw new BadRequestAlertException("A new shopping cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShoppingDTO result = shoppingService.save(shoppingDTO);
        return ResponseEntity.created(new URI("/api/shoppings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /shoppings : Updates an existing shopping.
     *
     * @param shoppingDTO the shoppingDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated shoppingDTO,
     * or with status 400 (Bad Request) if the shoppingDTO is not valid,
     * or with status 500 (Internal Server Error) if the shoppingDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/shoppings")
    public ResponseEntity<ShoppingDTO> updateShopping(@RequestBody ShoppingDTO shoppingDTO) throws URISyntaxException {
        log.debug("REST request to update Shopping : {}", shoppingDTO);
        if (shoppingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ShoppingDTO result = shoppingService.save(shoppingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, shoppingDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /shoppings : get all the shoppings.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of shoppings in body
     */
    @GetMapping("/shoppings")
    public ResponseEntity<List<ShoppingDTO>> getAllShoppings(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Shoppings");
        Page<ShoppingDTO> page = shoppingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /shoppings/:id : get the "id" shopping.
     *
     * @param id the id of the shoppingDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the shoppingDTO, or with status 404 (Not Found)
     */
    @GetMapping("/shoppings/{id}")
    public ResponseEntity<ShoppingDTO> getShopping(@PathVariable Long id) {
        log.debug("REST request to get Shopping : {}", id);
        Optional<ShoppingDTO> shoppingDTO = shoppingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(shoppingDTO);
    }

    /**
     * DELETE  /shoppings/:id : delete the "id" shopping.
     *
     * @param id the id of the shoppingDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/shoppings/{id}")
    public ResponseEntity<Void> deleteShopping(@PathVariable Long id) {
        log.debug("REST request to delete Shopping : {}", id);
        shoppingService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
