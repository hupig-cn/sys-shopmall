package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.ShopmallApp;
import com.weisen.www.code.yjf.shopmall.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.shopmall.domain.Commodity;
import com.weisen.www.code.yjf.shopmall.repository.CommodityRepository;
import com.weisen.www.code.yjf.shopmall.service.CommodityService;
import com.weisen.www.code.yjf.shopmall.service.dto.CommodityDTO;
import com.weisen.www.code.yjf.shopmall.service.mapper.CommodityMapper;
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
 * Integration tests for the {@Link CommodityResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, ShopmallApp.class})
public class CommodityResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CLASSIFICATIONID = "AAAAAAAAAA";
    private static final String UPDATED_CLASSIFICATIONID = "BBBBBBBBBB";

    private static final String DEFAULT_COMMODITYSTATE = "AAAAAAAAAA";
    private static final String UPDATED_COMMODITYSTATE = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAGE = "AAAAAAAAAA";
    private static final String UPDATED_POSTAGE = "BBBBBBBBBB";

    private static final String DEFAULT_SALEVALUE = "AAAAAAAAAA";
    private static final String UPDATED_SALEVALUE = "BBBBBBBBBB";

    private static final String DEFAULT_WEIGHT = "AAAAAAAAAA";
    private static final String UPDATED_WEIGHT = "BBBBBBBBBB";

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
    private CommodityRepository commodityRepository;

    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private CommodityService commodityService;

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

    private MockMvc restCommodityMockMvc;

    private Commodity commodity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CommodityResource commodityResource = new CommodityResource(commodityService);
        this.restCommodityMockMvc = MockMvcBuilders.standaloneSetup(commodityResource)
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
    public static Commodity createEntity(EntityManager em) {
        Commodity commodity = new Commodity()
            .name(DEFAULT_NAME)
            .classificationid(DEFAULT_CLASSIFICATIONID)
            .commoditystate(DEFAULT_COMMODITYSTATE)
            .postage(DEFAULT_POSTAGE)
            .salevalue(DEFAULT_SALEVALUE)
            .weight(DEFAULT_WEIGHT)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return commodity;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Commodity createUpdatedEntity(EntityManager em) {
        Commodity commodity = new Commodity()
            .name(UPDATED_NAME)
            .classificationid(UPDATED_CLASSIFICATIONID)
            .commoditystate(UPDATED_COMMODITYSTATE)
            .postage(UPDATED_POSTAGE)
            .salevalue(UPDATED_SALEVALUE)
            .weight(UPDATED_WEIGHT)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return commodity;
    }

    @BeforeEach
    public void initTest() {
        commodity = createEntity(em);
    }

    @Test
    @Transactional
    public void createCommodity() throws Exception {
        int databaseSizeBeforeCreate = commodityRepository.findAll().size();

        // Create the Commodity
        CommodityDTO commodityDTO = commodityMapper.toDto(commodity);
        restCommodityMockMvc.perform(post("/api/commodities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commodityDTO)))
            .andExpect(status().isCreated());

        // Validate the Commodity in the database
        List<Commodity> commodityList = commodityRepository.findAll();
        assertThat(commodityList).hasSize(databaseSizeBeforeCreate + 1);
        Commodity testCommodity = commodityList.get(commodityList.size() - 1);
        assertThat(testCommodity.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCommodity.getClassificationid()).isEqualTo(DEFAULT_CLASSIFICATIONID);
        assertThat(testCommodity.getCommoditystate()).isEqualTo(DEFAULT_COMMODITYSTATE);
        assertThat(testCommodity.getPostage()).isEqualTo(DEFAULT_POSTAGE);
        assertThat(testCommodity.getSalevalue()).isEqualTo(DEFAULT_SALEVALUE);
        assertThat(testCommodity.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testCommodity.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testCommodity.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testCommodity.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testCommodity.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testCommodity.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testCommodity.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testCommodity.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createCommodityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = commodityRepository.findAll().size();

        // Create the Commodity with an existing ID
        commodity.setId(1L);
        CommodityDTO commodityDTO = commodityMapper.toDto(commodity);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommodityMockMvc.perform(post("/api/commodities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commodityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Commodity in the database
        List<Commodity> commodityList = commodityRepository.findAll();
        assertThat(commodityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCommodities() throws Exception {
        // Initialize the database
        commodityRepository.saveAndFlush(commodity);

        // Get all the commodityList
        restCommodityMockMvc.perform(get("/api/commodities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commodity.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].classificationid").value(hasItem(DEFAULT_CLASSIFICATIONID.toString())))
            .andExpect(jsonPath("$.[*].commoditystate").value(hasItem(DEFAULT_COMMODITYSTATE.toString())))
            .andExpect(jsonPath("$.[*].postage").value(hasItem(DEFAULT_POSTAGE.toString())))
            .andExpect(jsonPath("$.[*].salevalue").value(hasItem(DEFAULT_SALEVALUE.toString())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.toString())))
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
    public void getCommodity() throws Exception {
        // Initialize the database
        commodityRepository.saveAndFlush(commodity);

        // Get the commodity
        restCommodityMockMvc.perform(get("/api/commodities/{id}", commodity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(commodity.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.classificationid").value(DEFAULT_CLASSIFICATIONID.toString()))
            .andExpect(jsonPath("$.commoditystate").value(DEFAULT_COMMODITYSTATE.toString()))
            .andExpect(jsonPath("$.postage").value(DEFAULT_POSTAGE.toString()))
            .andExpect(jsonPath("$.salevalue").value(DEFAULT_SALEVALUE.toString()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.toString()))
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
    public void getNonExistingCommodity() throws Exception {
        // Get the commodity
        restCommodityMockMvc.perform(get("/api/commodities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCommodity() throws Exception {
        // Initialize the database
        commodityRepository.saveAndFlush(commodity);

        int databaseSizeBeforeUpdate = commodityRepository.findAll().size();

        // Update the commodity
        Commodity updatedCommodity = commodityRepository.findById(commodity.getId()).get();
        // Disconnect from session so that the updates on updatedCommodity are not directly saved in db
        em.detach(updatedCommodity);
        updatedCommodity
            .name(UPDATED_NAME)
            .classificationid(UPDATED_CLASSIFICATIONID)
            .commoditystate(UPDATED_COMMODITYSTATE)
            .postage(UPDATED_POSTAGE)
            .salevalue(UPDATED_SALEVALUE)
            .weight(UPDATED_WEIGHT)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        CommodityDTO commodityDTO = commodityMapper.toDto(updatedCommodity);

        restCommodityMockMvc.perform(put("/api/commodities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commodityDTO)))
            .andExpect(status().isOk());

        // Validate the Commodity in the database
        List<Commodity> commodityList = commodityRepository.findAll();
        assertThat(commodityList).hasSize(databaseSizeBeforeUpdate);
        Commodity testCommodity = commodityList.get(commodityList.size() - 1);
        assertThat(testCommodity.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCommodity.getClassificationid()).isEqualTo(UPDATED_CLASSIFICATIONID);
        assertThat(testCommodity.getCommoditystate()).isEqualTo(UPDATED_COMMODITYSTATE);
        assertThat(testCommodity.getPostage()).isEqualTo(UPDATED_POSTAGE);
        assertThat(testCommodity.getSalevalue()).isEqualTo(UPDATED_SALEVALUE);
        assertThat(testCommodity.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testCommodity.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testCommodity.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testCommodity.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testCommodity.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testCommodity.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testCommodity.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testCommodity.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingCommodity() throws Exception {
        int databaseSizeBeforeUpdate = commodityRepository.findAll().size();

        // Create the Commodity
        CommodityDTO commodityDTO = commodityMapper.toDto(commodity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommodityMockMvc.perform(put("/api/commodities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commodityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Commodity in the database
        List<Commodity> commodityList = commodityRepository.findAll();
        assertThat(commodityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCommodity() throws Exception {
        // Initialize the database
        commodityRepository.saveAndFlush(commodity);

        int databaseSizeBeforeDelete = commodityRepository.findAll().size();

        // Delete the commodity
        restCommodityMockMvc.perform(delete("/api/commodities/{id}", commodity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Commodity> commodityList = commodityRepository.findAll();
        assertThat(commodityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Commodity.class);
        Commodity commodity1 = new Commodity();
        commodity1.setId(1L);
        Commodity commodity2 = new Commodity();
        commodity2.setId(commodity1.getId());
        assertThat(commodity1).isEqualTo(commodity2);
        commodity2.setId(2L);
        assertThat(commodity1).isNotEqualTo(commodity2);
        commodity1.setId(null);
        assertThat(commodity1).isNotEqualTo(commodity2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommodityDTO.class);
        CommodityDTO commodityDTO1 = new CommodityDTO();
        commodityDTO1.setId(1L);
        CommodityDTO commodityDTO2 = new CommodityDTO();
        assertThat(commodityDTO1).isNotEqualTo(commodityDTO2);
        commodityDTO2.setId(commodityDTO1.getId());
        assertThat(commodityDTO1).isEqualTo(commodityDTO2);
        commodityDTO2.setId(2L);
        assertThat(commodityDTO1).isNotEqualTo(commodityDTO2);
        commodityDTO1.setId(null);
        assertThat(commodityDTO1).isNotEqualTo(commodityDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(commodityMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(commodityMapper.fromId(null)).isNull();
    }
}
