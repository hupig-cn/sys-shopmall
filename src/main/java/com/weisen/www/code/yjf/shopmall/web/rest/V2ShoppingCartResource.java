package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.domain.V2ShoppingCart;
import com.weisen.www.code.yjf.shopmall.repository.V2ShoppingCartRepository;
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
 * REST controller for managing {@link com.weisen.www.code.yjf.shopmall.domain.V2ShoppingCart}.
 */
@RestController
@RequestMapping("/api")
public class V2ShoppingCartResource {

    private final Logger log = LoggerFactory.getLogger(V2ShoppingCartResource.class);

    private static final String ENTITY_NAME = "shopmallV2ShoppingCart";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final V2ShoppingCartRepository v2ShoppingCartRepository;

    public V2ShoppingCartResource(V2ShoppingCartRepository v2ShoppingCartRepository) {
        this.v2ShoppingCartRepository = v2ShoppingCartRepository;
    }

    /**
     * {@code POST  /v-2-shopping-carts} : Create a new v2ShoppingCart.
     *
     * @param v2ShoppingCart the v2ShoppingCart to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new v2ShoppingCart, or with status {@code 400 (Bad Request)} if the v2ShoppingCart has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/v-2-shopping-carts")
    public ResponseEntity<V2ShoppingCart> createV2ShoppingCart(@RequestBody V2ShoppingCart v2ShoppingCart) throws URISyntaxException {
        log.debug("REST request to save V2ShoppingCart : {}", v2ShoppingCart);
        if (v2ShoppingCart.getId() != null) {
            throw new BadRequestAlertException("A new v2ShoppingCart cannot already have an ID", ENTITY_NAME, "idexists");
        }
        V2ShoppingCart result = v2ShoppingCartRepository.save(v2ShoppingCart);
        return ResponseEntity.created(new URI("/api/v-2-shopping-carts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /v-2-shopping-carts} : Updates an existing v2ShoppingCart.
     *
     * @param v2ShoppingCart the v2ShoppingCart to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated v2ShoppingCart,
     * or with status {@code 400 (Bad Request)} if the v2ShoppingCart is not valid,
     * or with status {@code 500 (Internal Server Error)} if the v2ShoppingCart couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/v-2-shopping-carts")
    public ResponseEntity<V2ShoppingCart> updateV2ShoppingCart(@RequestBody V2ShoppingCart v2ShoppingCart) throws URISyntaxException {
        log.debug("REST request to update V2ShoppingCart : {}", v2ShoppingCart);
        if (v2ShoppingCart.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        V2ShoppingCart result = v2ShoppingCartRepository.save(v2ShoppingCart);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, v2ShoppingCart.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /v-2-shopping-carts} : get all the v2ShoppingCarts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of v2ShoppingCarts in body.
     */
    @GetMapping("/v-2-shopping-carts")
    public List<V2ShoppingCart> getAllV2ShoppingCarts() {
        log.debug("REST request to get all V2ShoppingCarts");
        return v2ShoppingCartRepository.findAll();
    }

    /**
     * {@code GET  /v-2-shopping-carts/:id} : get the "id" v2ShoppingCart.
     *
     * @param id the id of the v2ShoppingCart to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the v2ShoppingCart, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/v-2-shopping-carts/{id}")
    public ResponseEntity<V2ShoppingCart> getV2ShoppingCart(@PathVariable Long id) {
        log.debug("REST request to get V2ShoppingCart : {}", id);
        Optional<V2ShoppingCart> v2ShoppingCart = v2ShoppingCartRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(v2ShoppingCart);
    }

    /**
     * {@code DELETE  /v-2-shopping-carts/:id} : delete the "id" v2ShoppingCart.
     *
     * @param id the id of the v2ShoppingCart to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/v-2-shopping-carts/{id}")
    public ResponseEntity<Void> deleteV2ShoppingCart(@PathVariable Long id) {
        log.debug("REST request to delete V2ShoppingCart : {}", id);
        v2ShoppingCartRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
