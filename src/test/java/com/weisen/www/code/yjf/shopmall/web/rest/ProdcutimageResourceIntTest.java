package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.ShopmallApp;

import com.weisen.www.code.yjf.shopmall.config.SecurityBeanOverrideConfiguration;

import com.weisen.www.code.yjf.shopmall.domain.Prodcutimage;
import com.weisen.www.code.yjf.shopmall.repository.ProdcutimageRepository;
import com.weisen.www.code.yjf.shopmall.service.ProdcutimageService;
import com.weisen.www.code.yjf.shopmall.service.dto.ProdcutimageDTO;
import com.weisen.www.code.yjf.shopmall.service.mapper.ProdcutimageMapper;
import com.weisen.www.code.yjf.shopmall.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.weisen.www.code.yjf.shopmall.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProdcutimageResource REST controller.
 *
 * @see ProdcutimageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, ShopmallApp.class})
public class ProdcutimageResourceIntTest {

    private static final String DEFAULT_SPECIFICATIONSID = "AAAAAAAAAA";
    private static final String UPDATED_SPECIFICATIONSID = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_OTHER = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_CREATEDATE = "AAAAAAAAAA";
    private static final String UPDATED_CREATEDATE = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIER = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIERDATE = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIERDATE = "BBBBBBBBBB";

    private static final Long DEFAULT_MODIFIERNUM = 1L;
    private static final Long UPDATED_MODIFIERNUM = 2L;

    private static final Boolean DEFAULT_LOGICDELETE = false;
    private static final Boolean UPDATED_LOGICDELETE = true;

    @Autowired
    private ProdcutimageRepository prodcutimageRepository;

    @Autowired
    private ProdcutimageMapper prodcutimageMapper;

    @Autowired
    private ProdcutimageService prodcutimageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restProdcutimageMockMvc;

    private Prodcutimage prodcutimage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProdcutimageResource prodcutimageResource = new ProdcutimageResource(prodcutimageService);
        this.restProdcutimageMockMvc = MockMvcBuilders.standaloneSetup(prodcutimageResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Prodcutimage createEntity(EntityManager em) {
        Prodcutimage prodcutimage = new Prodcutimage()
            .specificationsid(DEFAULT_SPECIFICATIONSID)
            .url(DEFAULT_URL)
            .other(DEFAULT_OTHER)
            .type(DEFAULT_TYPE)
            .state(DEFAULT_STATE)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE);
        return prodcutimage;
    }

    @Before
    public void initTest() {
        prodcutimage = createEntity(em);
    }

    @Test
    @Transactional
    public void createProdcutimage() throws Exception {
        int databaseSizeBeforeCreate = prodcutimageRepository.findAll().size();

        // Create the Prodcutimage
        ProdcutimageDTO prodcutimageDTO = prodcutimageMapper.toDto(prodcutimage);
        restProdcutimageMockMvc.perform(post("/api/prodcutimages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prodcutimageDTO)))
            .andExpect(status().isCreated());

        // Validate the Prodcutimage in the database
        List<Prodcutimage> prodcutimageList = prodcutimageRepository.findAll();
        assertThat(prodcutimageList).hasSize(databaseSizeBeforeCreate + 1);
        Prodcutimage testProdcutimage = prodcutimageList.get(prodcutimageList.size() - 1);
        assertThat(testProdcutimage.getSpecificationsid()).isEqualTo(DEFAULT_SPECIFICATIONSID);
        assertThat(testProdcutimage.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testProdcutimage.getOther()).isEqualTo(DEFAULT_OTHER);
        assertThat(testProdcutimage.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testProdcutimage.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testProdcutimage.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testProdcutimage.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testProdcutimage.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testProdcutimage.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testProdcutimage.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testProdcutimage.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
    }

    @Test
    @Transactional
    public void createProdcutimageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = prodcutimageRepository.findAll().size();

        // Create the Prodcutimage with an existing ID
        prodcutimage.setId(1L);
        ProdcutimageDTO prodcutimageDTO = prodcutimageMapper.toDto(prodcutimage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProdcutimageMockMvc.perform(post("/api/prodcutimages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prodcutimageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Prodcutimage in the database
        List<Prodcutimage> prodcutimageList = prodcutimageRepository.findAll();
        assertThat(prodcutimageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProdcutimages() throws Exception {
        // Initialize the database
        prodcutimageRepository.saveAndFlush(prodcutimage);

        // Get all the prodcutimageList
        restProdcutimageMockMvc.perform(get("/api/prodcutimages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(prodcutimage.getId().intValue())))
            .andExpect(jsonPath("$.[*].specificationsid").value(hasItem(DEFAULT_SPECIFICATIONSID.toString())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())))
            .andExpect(jsonPath("$.[*].other").value(hasItem(DEFAULT_OTHER.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].createdate").value(hasItem(DEFAULT_CREATEDATE.toString())))
            .andExpect(jsonPath("$.[*].modifier").value(hasItem(DEFAULT_MODIFIER.toString())))
            .andExpect(jsonPath("$.[*].modifierdate").value(hasItem(DEFAULT_MODIFIERDATE.toString())))
            .andExpect(jsonPath("$.[*].modifiernum").value(hasItem(DEFAULT_MODIFIERNUM.intValue())))
            .andExpect(jsonPath("$.[*].logicdelete").value(hasItem(DEFAULT_LOGICDELETE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getProdcutimage() throws Exception {
        // Initialize the database
        prodcutimageRepository.saveAndFlush(prodcutimage);

        // Get the prodcutimage
        restProdcutimageMockMvc.perform(get("/api/prodcutimages/{id}", prodcutimage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(prodcutimage.getId().intValue()))
            .andExpect(jsonPath("$.specificationsid").value(DEFAULT_SPECIFICATIONSID.toString()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL.toString()))
            .andExpect(jsonPath("$.other").value(DEFAULT_OTHER.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.createdate").value(DEFAULT_CREATEDATE.toString()))
            .andExpect(jsonPath("$.modifier").value(DEFAULT_MODIFIER.toString()))
            .andExpect(jsonPath("$.modifierdate").value(DEFAULT_MODIFIERDATE.toString()))
            .andExpect(jsonPath("$.modifiernum").value(DEFAULT_MODIFIERNUM.intValue()))
            .andExpect(jsonPath("$.logicdelete").value(DEFAULT_LOGICDELETE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProdcutimage() throws Exception {
        // Get the prodcutimage
        restProdcutimageMockMvc.perform(get("/api/prodcutimages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProdcutimage() throws Exception {
        // Initialize the database
        prodcutimageRepository.saveAndFlush(prodcutimage);

        int databaseSizeBeforeUpdate = prodcutimageRepository.findAll().size();

        // Update the prodcutimage
        Prodcutimage updatedProdcutimage = prodcutimageRepository.findById(prodcutimage.getId()).get();
        // Disconnect from session so that the updates on updatedProdcutimage are not directly saved in db
        em.detach(updatedProdcutimage);
        updatedProdcutimage
            .specificationsid(UPDATED_SPECIFICATIONSID)
            .url(UPDATED_URL)
            .other(UPDATED_OTHER)
            .type(UPDATED_TYPE)
            .state(UPDATED_STATE)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE);
        ProdcutimageDTO prodcutimageDTO = prodcutimageMapper.toDto(updatedProdcutimage);

        restProdcutimageMockMvc.perform(put("/api/prodcutimages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prodcutimageDTO)))
            .andExpect(status().isOk());

        // Validate the Prodcutimage in the database
        List<Prodcutimage> prodcutimageList = prodcutimageRepository.findAll();
        assertThat(prodcutimageList).hasSize(databaseSizeBeforeUpdate);
        Prodcutimage testProdcutimage = prodcutimageList.get(prodcutimageList.size() - 1);
        assertThat(testProdcutimage.getSpecificationsid()).isEqualTo(UPDATED_SPECIFICATIONSID);
        assertThat(testProdcutimage.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testProdcutimage.getOther()).isEqualTo(UPDATED_OTHER);
        assertThat(testProdcutimage.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testProdcutimage.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testProdcutimage.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testProdcutimage.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testProdcutimage.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testProdcutimage.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testProdcutimage.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testProdcutimage.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
    }

    @Test
    @Transactional
    public void updateNonExistingProdcutimage() throws Exception {
        int databaseSizeBeforeUpdate = prodcutimageRepository.findAll().size();

        // Create the Prodcutimage
        ProdcutimageDTO prodcutimageDTO = prodcutimageMapper.toDto(prodcutimage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProdcutimageMockMvc.perform(put("/api/prodcutimages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prodcutimageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Prodcutimage in the database
        List<Prodcutimage> prodcutimageList = prodcutimageRepository.findAll();
        assertThat(prodcutimageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProdcutimage() throws Exception {
        // Initialize the database
        prodcutimageRepository.saveAndFlush(prodcutimage);

        int databaseSizeBeforeDelete = prodcutimageRepository.findAll().size();

        // Delete the prodcutimage
        restProdcutimageMockMvc.perform(delete("/api/prodcutimages/{id}", prodcutimage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Prodcutimage> prodcutimageList = prodcutimageRepository.findAll();
        assertThat(prodcutimageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Prodcutimage.class);
        Prodcutimage prodcutimage1 = new Prodcutimage();
        prodcutimage1.setId(1L);
        Prodcutimage prodcutimage2 = new Prodcutimage();
        prodcutimage2.setId(prodcutimage1.getId());
        assertThat(prodcutimage1).isEqualTo(prodcutimage2);
        prodcutimage2.setId(2L);
        assertThat(prodcutimage1).isNotEqualTo(prodcutimage2);
        prodcutimage1.setId(null);
        assertThat(prodcutimage1).isNotEqualTo(prodcutimage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProdcutimageDTO.class);
        ProdcutimageDTO prodcutimageDTO1 = new ProdcutimageDTO();
        prodcutimageDTO1.setId(1L);
        ProdcutimageDTO prodcutimageDTO2 = new ProdcutimageDTO();
        assertThat(prodcutimageDTO1).isNotEqualTo(prodcutimageDTO2);
        prodcutimageDTO2.setId(prodcutimageDTO1.getId());
        assertThat(prodcutimageDTO1).isEqualTo(prodcutimageDTO2);
        prodcutimageDTO2.setId(2L);
        assertThat(prodcutimageDTO1).isNotEqualTo(prodcutimageDTO2);
        prodcutimageDTO1.setId(null);
        assertThat(prodcutimageDTO1).isNotEqualTo(prodcutimageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(prodcutimageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(prodcutimageMapper.fromId(null)).isNull();
    }
}
