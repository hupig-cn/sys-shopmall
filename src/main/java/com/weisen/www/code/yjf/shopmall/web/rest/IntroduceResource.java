package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.service.IntroduceService;
import com.weisen.www.code.yjf.shopmall.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.yjf.shopmall.service.dto.IntroduceDTO;

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
 * REST controller for managing {@link com.weisen.www.code.yjf.shopmall.domain.Introduce}.
 */
@RestController
@RequestMapping("/api")
public class IntroduceResource {

    private final Logger log = LoggerFactory.getLogger(IntroduceResource.class);

    private static final String ENTITY_NAME = "shopmallIntroduce";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IntroduceService introduceService;

    public IntroduceResource(IntroduceService introduceService) {
        this.introduceService = introduceService;
    }

    /**
     * {@code POST  /introduces} : Create a new introduce.
     *
     * @param introduceDTO the introduceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new introduceDTO, or with status {@code 400 (Bad Request)} if the introduce has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/introduces")
    public ResponseEntity<IntroduceDTO> createIntroduce(@RequestBody IntroduceDTO introduceDTO) throws URISyntaxException {
        log.debug("REST request to save Introduce : {}", introduceDTO);
        if (introduceDTO.getId() != null) {
            throw new BadRequestAlertException("A new introduce cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IntroduceDTO result = introduceService.save(introduceDTO);
        return ResponseEntity.created(new URI("/api/introduces/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /introduces} : Updates an existing introduce.
     *
     * @param introduceDTO the introduceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated introduceDTO,
     * or with status {@code 400 (Bad Request)} if the introduceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the introduceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/introduces")
    public ResponseEntity<IntroduceDTO> updateIntroduce(@RequestBody IntroduceDTO introduceDTO) throws URISyntaxException {
        log.debug("REST request to update Introduce : {}", introduceDTO);
        if (introduceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IntroduceDTO result = introduceService.save(introduceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, introduceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /introduces} : get all the introduces.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of introduces in body.
     */
    @GetMapping("/introduces")
    public ResponseEntity<List<IntroduceDTO>> getAllIntroduces(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Introduces");
        Page<IntroduceDTO> page = introduceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /introduces/:id} : get the "id" introduce.
     *
     * @param id the id of the introduceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the introduceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/introduces/{id}")
    public ResponseEntity<IntroduceDTO> getIntroduce(@PathVariable Long id) {
        log.debug("REST request to get Introduce : {}", id);
        Optional<IntroduceDTO> introduceDTO = introduceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(introduceDTO);
    }

    /**
     * {@code DELETE  /introduces/:id} : delete the "id" introduce.
     *
     * @param id the id of the introduceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/introduces/{id}")
    public ResponseEntity<Void> deleteIntroduce(@PathVariable Long id) {
        log.debug("REST request to delete Introduce : {}", id);
        introduceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
