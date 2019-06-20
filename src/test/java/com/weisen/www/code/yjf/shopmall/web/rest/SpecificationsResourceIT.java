package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.ShopmallApp;
import com.weisen.www.code.yjf.shopmall.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.shopmall.domain.Specifications;
import com.weisen.www.code.yjf.shopmall.repository.SpecificationsRepository;
import com.weisen.www.code.yjf.shopmall.service.SpecificationsService;
import com.weisen.www.code.yjf.shopmall.service.dto.SpecificationsDTO;
import com.weisen.www.code.yjf.shopmall.service.mapper.SpecificationsMapper;
import com.weisen.www.code.yjf.shopmall.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
 * Integration tests for the {@Link SpecificationsResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, ShopmallApp.class})
public class SpecificationsResourceIT {

    private static final String DEFAULT_COMMODITYID = "AAAAAAAAAA";
    private static final String UPDATED_COMMODITYID = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL = "AAAAAAAAAA";
    private static final String UPDATED_MODEL = "BBBBBBBBBB";

    private static final String DEFAULT_SPECIFICATIONS = "AAAAAAAAAA";
    private static final String UPDATED_SPECIFICATIONS = "BBBBBBBBBB";

    private static final String DEFAULT_NUM = "AAAAAAAAAA";
    private static final String UPDATED_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_PRICE = "AAAAAAAAAA";
    private static final String UPDATED_PRICE = "BBBBBBBBBB";

    private static final String DEFAULT_DISCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_DISCOUNT = "BBBBBBBBBB";

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

    private static final String DEFAULT_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_OTHER = "BBBBBBBBBB";

    @Autowired
    private SpecificationsRepository specificationsRepository;

    @Autowired
    private SpecificationsMapper specificationsMapper;

    @Autowired
    private SpecificationsService specificationsService;

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

    private MockMvc restSpecificationsMockMvc;

    private Specifications specifications;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SpecificationsResource specificationsResource = new SpecificationsResource(specificationsService);
        this.restSpecificationsMockMvc = MockMvcBuilders.standaloneSetup(specificationsResource)
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
    public static Specifications createEntity(EntityManager em) {
        Specifications specifications = new Specifications()
            .commodityid(DEFAULT_COMMODITYID)
            .model(DEFAULT_MODEL)
            .specifications(DEFAULT_SPECIFICATIONS)
            .num(DEFAULT_NUM)
            .price(DEFAULT_PRICE)
            .discount(DEFAULT_DISCOUNT)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return specifications;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Specifications createUpdatedEntity(EntityManager em) {
        Specifications specifications = new Specifications()
            .commodityid(UPDATED_COMMODITYID)
            .model(UPDATED_MODEL)
            .specifications(UPDATED_SPECIFICATIONS)
            .num(UPDATED_NUM)
            .price(UPDATED_PRICE)
            .discount(UPDATED_DISCOUNT)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return specifications;
    }

    @BeforeEach
    public void initTest() {
        specifications = createEntity(em);
    }

    @Test
    @Transactional
    public void createSpecifications() throws Exception {
        int databaseSizeBeforeCreate = specificationsRepository.findAll().size();

        // Create the Specifications
        SpecificationsDTO specificationsDTO = specificationsMapper.toDto(specifications);
        restSpecificationsMockMvc.perform(post("/api/specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(specificationsDTO)))
            .andExpect(status().isCreated());

        // Validate the Specifications in the database
        List<Specifications> specificationsList = specificationsRepository.findAll();
        assertThat(specificationsList).hasSize(databaseSizeBeforeCreate + 1);
        Specifications testSpecifications = specificationsList.get(specificationsList.size() - 1);
        assertThat(testSpecifications.getCommodityid()).isEqualTo(DEFAULT_COMMODITYID);
        assertThat(testSpecifications.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testSpecifications.getSpecifications()).isEqualTo(DEFAULT_SPECIFICATIONS);
        assertThat(testSpecifications.getNum()).isEqualTo(DEFAULT_NUM);
        assertThat(testSpecifications.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testSpecifications.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testSpecifications.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testSpecifications.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testSpecifications.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testSpecifications.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testSpecifications.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testSpecifications.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testSpecifications.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createSpecificationsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = specificationsRepository.findAll().size();

        // Create the Specifications with an existing ID
        specifications.setId(1L);
        SpecificationsDTO specificationsDTO = specificationsMapper.toDto(specifications);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSpecificationsMockMvc.perform(post("/api/specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(specificationsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Specifications in the database
        List<Specifications> specificationsList = specificationsRepository.findAll();
        assertThat(specificationsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSpecifications() throws Exception {
        // Initialize the database
        specificationsRepository.saveAndFlush(specifications);

        // Get all the specificationsList
        restSpecificationsMockMvc.perform(get("/api/specifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(specifications.getId().intValue())))
            .andExpect(jsonPath("$.[*].commodityid").value(hasItem(DEFAULT_COMMODITYID.toString())))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL.toString())))
            .andExpect(jsonPath("$.[*].specifications").value(hasItem(DEFAULT_SPECIFICATIONS.toString())))
            .andExpect(jsonPath("$.[*].num").value(hasItem(DEFAULT_NUM.toString())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.toString())))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].createdate").value(hasItem(DEFAULT_CREATEDATE.toString())))
            .andExpect(jsonPath("$.[*].modifier").value(hasItem(DEFAULT_MODIFIER.toString())))
            .andExpect(jsonPath("$.[*].modifierdate").value(hasItem(DEFAULT_MODIFIERDATE.toString())))
            .andExpect(jsonPath("$.[*].modifiernum").value(hasItem(DEFAULT_MODIFIERNUM.intValue())))
            .andExpect(jsonPath("$.[*].logicdelete").value(hasItem(DEFAULT_LOGICDELETE.booleanValue())))
            .andExpect(jsonPath("$.[*].other").value(hasItem(DEFAULT_OTHER.toString())));
    }
    
    @Test
    @Transactional
    public void getSpecifications() throws Exception {
        // Initialize the database
        specificationsRepository.saveAndFlush(specifications);

        // Get the specifications
        restSpecificationsMockMvc.perform(get("/api/specifications/{id}", specifications.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(specifications.getId().intValue()))
            .andExpect(jsonPath("$.commodityid").value(DEFAULT_COMMODITYID.toString()))
            .andExpect(jsonPath("$.model").value(DEFAULT_MODEL.toString()))
            .andExpect(jsonPath("$.specifications").value(DEFAULT_SPECIFICATIONS.toString()))
            .andExpect(jsonPath("$.num").value(DEFAULT_NUM.toString()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.toString()))
            .andExpect(jsonPath("$.discount").value(DEFAULT_DISCOUNT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.createdate").value(DEFAULT_CREATEDATE.toString()))
            .andExpect(jsonPath("$.modifier").value(DEFAULT_MODIFIER.toString()))
            .andExpect(jsonPath("$.modifierdate").value(DEFAULT_MODIFIERDATE.toString()))
            .andExpect(jsonPath("$.modifiernum").value(DEFAULT_MODIFIERNUM.intValue()))
            .andExpect(jsonPath("$.logicdelete").value(DEFAULT_LOGICDELETE.booleanValue()))
            .andExpect(jsonPath("$.other").value(DEFAULT_OTHER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSpecifications() throws Exception {
        // Get the specifications
        restSpecificationsMockMvc.perform(get("/api/specifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSpecifications() throws Exception {
        // Initialize the database
        specificationsRepository.saveAndFlush(specifications);

        int databaseSizeBeforeUpdate = specificationsRepository.findAll().size();

        // Update the specifications
        Specifications updatedSpecifications = specificationsRepository.findById(specifications.getId()).get();
        // Disconnect from session so that the updates on updatedSpecifications are not directly saved in db
        em.detach(updatedSpecifications);
        updatedSpecifications
            .commodityid(UPDATED_COMMODITYID)
            .model(UPDATED_MODEL)
            .specifications(UPDATED_SPECIFICATIONS)
            .num(UPDATED_NUM)
            .price(UPDATED_PRICE)
            .discount(UPDATED_DISCOUNT)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        SpecificationsDTO specificationsDTO = specificationsMapper.toDto(updatedSpecifications);

        restSpecificationsMockMvc.perform(put("/api/specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(specificationsDTO)))
            .andExpect(status().isOk());

        // Validate the Specifications in the database
        List<Specifications> specificationsList = specificationsRepository.findAll();
        assertThat(specificationsList).hasSize(databaseSizeBeforeUpdate);
        Specifications testSpecifications = specificationsList.get(specificationsList.size() - 1);
        assertThat(testSpecifications.getCommodityid()).isEqualTo(UPDATED_COMMODITYID);
        assertThat(testSpecifications.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testSpecifications.getSpecifications()).isEqualTo(UPDATED_SPECIFICATIONS);
        assertThat(testSpecifications.getNum()).isEqualTo(UPDATED_NUM);
        assertThat(testSpecifications.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testSpecifications.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testSpecifications.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testSpecifications.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testSpecifications.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testSpecifications.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testSpecifications.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testSpecifications.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testSpecifications.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingSpecifications() throws Exception {
        int databaseSizeBeforeUpdate = specificationsRepository.findAll().size();

        // Create the Specifications
        SpecificationsDTO specificationsDTO = specificationsMapper.toDto(specifications);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSpecificationsMockMvc.perform(put("/api/specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(specificationsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Specifications in the database
        List<Specifications> specificationsList = specificationsRepository.findAll();
        assertThat(specificationsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSpecifications() throws Exception {
        // Initialize the database
        specificationsRepository.saveAndFlush(specifications);

        int databaseSizeBeforeDelete = specificationsRepository.findAll().size();

        // Delete the specifications
        restSpecificationsMockMvc.perform(delete("/api/specifications/{id}", specifications.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Specifications> specificationsList = specificationsRepository.findAll();
        assertThat(specificationsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Specifications.class);
        Specifications specifications1 = new Specifications();
        specifications1.setId(1L);
        Specifications specifications2 = new Specifications();
        specifications2.setId(specifications1.getId());
        assertThat(specifications1).isEqualTo(specifications2);
        specifications2.setId(2L);
        assertThat(specifications1).isNotEqualTo(specifications2);
        specifications1.setId(null);
        assertThat(specifications1).isNotEqualTo(specifications2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SpecificationsDTO.class);
        SpecificationsDTO specificationsDTO1 = new SpecificationsDTO();
        specificationsDTO1.setId(1L);
        SpecificationsDTO specificationsDTO2 = new SpecificationsDTO();
        assertThat(specificationsDTO1).isNotEqualTo(specificationsDTO2);
        specificationsDTO2.setId(specificationsDTO1.getId());
        assertThat(specificationsDTO1).isEqualTo(specificationsDTO2);
        specificationsDTO2.setId(2L);
        assertThat(specificationsDTO1).isNotEqualTo(specificationsDTO2);
        specificationsDTO1.setId(null);
        assertThat(specificationsDTO1).isNotEqualTo(specificationsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(specificationsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(specificationsMapper.fromId(null)).isNull();
    }
}
