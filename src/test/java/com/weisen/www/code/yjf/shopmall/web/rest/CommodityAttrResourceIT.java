package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.ShopmallApp;
import com.weisen.www.code.yjf.shopmall.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.shopmall.domain.CommodityAttr;
import com.weisen.www.code.yjf.shopmall.repository.CommodityAttrRepository;
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
 * Integration tests for the {@Link CommodityAttrResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, ShopmallApp.class})
public class CommodityAttrResourceIT {

    private static final Integer DEFAULT_GOODS_ID = 1;
    private static final Integer UPDATED_GOODS_ID = 2;

    private static final String DEFAULT_SPU = "AAAAAAAAAA";
    private static final String UPDATED_SPU = "BBBBBBBBBB";

    private static final String DEFAULT_SKU = "AAAAAAAAAA";
    private static final String UPDATED_SKU = "BBBBBBBBBB";

    private static final String DEFAULT_CTIME = "AAAAAAAAAA";
    private static final String UPDATED_CTIME = "BBBBBBBBBB";

    private static final String DEFAULT_SPU_LASTUPTIME = "AAAAAAAAAA";
    private static final String UPDATED_SPU_LASTUPTIME = "BBBBBBBBBB";

    private static final String DEFAULT_SKU_LASTUPTIME = "AAAAAAAAAA";
    private static final String UPDATED_SKU_LASTUPTIME = "BBBBBBBBBB";

    @Autowired
    private CommodityAttrRepository commodityAttrRepository;

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

    private MockMvc restCommodityAttrMockMvc;

    private CommodityAttr commodityAttr;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CommodityAttrResource commodityAttrResource = new CommodityAttrResource(commodityAttrRepository);
        this.restCommodityAttrMockMvc = MockMvcBuilders.standaloneSetup(commodityAttrResource)
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
    public static CommodityAttr createEntity(EntityManager em) {
        CommodityAttr commodityAttr = new CommodityAttr()
            .goodsId(DEFAULT_GOODS_ID)
            .spu(DEFAULT_SPU)
            .sku(DEFAULT_SKU)
            .ctime(DEFAULT_CTIME)
            .spuLastuptime(DEFAULT_SPU_LASTUPTIME)
            .skuLastuptime(DEFAULT_SKU_LASTUPTIME);
        return commodityAttr;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommodityAttr createUpdatedEntity(EntityManager em) {
        CommodityAttr commodityAttr = new CommodityAttr()
            .goodsId(UPDATED_GOODS_ID)
            .spu(UPDATED_SPU)
            .sku(UPDATED_SKU)
            .ctime(UPDATED_CTIME)
            .spuLastuptime(UPDATED_SPU_LASTUPTIME)
            .skuLastuptime(UPDATED_SKU_LASTUPTIME);
        return commodityAttr;
    }

    @BeforeEach
    public void initTest() {
        commodityAttr = createEntity(em);
    }

    @Test
    @Transactional
    public void createCommodityAttr() throws Exception {
        int databaseSizeBeforeCreate = commodityAttrRepository.findAll().size();

        // Create the CommodityAttr
        restCommodityAttrMockMvc.perform(post("/api/commodity-attrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commodityAttr)))
            .andExpect(status().isCreated());

        // Validate the CommodityAttr in the database
        List<CommodityAttr> commodityAttrList = commodityAttrRepository.findAll();
        assertThat(commodityAttrList).hasSize(databaseSizeBeforeCreate + 1);
        CommodityAttr testCommodityAttr = commodityAttrList.get(commodityAttrList.size() - 1);
        assertThat(testCommodityAttr.getGoodsId()).isEqualTo(DEFAULT_GOODS_ID);
        assertThat(testCommodityAttr.getSpu()).isEqualTo(DEFAULT_SPU);
        assertThat(testCommodityAttr.getSku()).isEqualTo(DEFAULT_SKU);
        assertThat(testCommodityAttr.getCtime()).isEqualTo(DEFAULT_CTIME);
        assertThat(testCommodityAttr.getSpuLastuptime()).isEqualTo(DEFAULT_SPU_LASTUPTIME);
        assertThat(testCommodityAttr.getSkuLastuptime()).isEqualTo(DEFAULT_SKU_LASTUPTIME);
    }

    @Test
    @Transactional
    public void createCommodityAttrWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = commodityAttrRepository.findAll().size();

        // Create the CommodityAttr with an existing ID
        commodityAttr.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommodityAttrMockMvc.perform(post("/api/commodity-attrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commodityAttr)))
            .andExpect(status().isBadRequest());

        // Validate the CommodityAttr in the database
        List<CommodityAttr> commodityAttrList = commodityAttrRepository.findAll();
        assertThat(commodityAttrList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCommodityAttrs() throws Exception {
        // Initialize the database
        commodityAttrRepository.saveAndFlush(commodityAttr);

        // Get all the commodityAttrList
        restCommodityAttrMockMvc.perform(get("/api/commodity-attrs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commodityAttr.getId().intValue())))
            .andExpect(jsonPath("$.[*].goodsId").value(hasItem(DEFAULT_GOODS_ID)))
            .andExpect(jsonPath("$.[*].spu").value(hasItem(DEFAULT_SPU.toString())))
            .andExpect(jsonPath("$.[*].sku").value(hasItem(DEFAULT_SKU.toString())))
            .andExpect(jsonPath("$.[*].ctime").value(hasItem(DEFAULT_CTIME.toString())))
            .andExpect(jsonPath("$.[*].spuLastuptime").value(hasItem(DEFAULT_SPU_LASTUPTIME.toString())))
            .andExpect(jsonPath("$.[*].skuLastuptime").value(hasItem(DEFAULT_SKU_LASTUPTIME.toString())));
    }
    
    @Test
    @Transactional
    public void getCommodityAttr() throws Exception {
        // Initialize the database
        commodityAttrRepository.saveAndFlush(commodityAttr);

        // Get the commodityAttr
        restCommodityAttrMockMvc.perform(get("/api/commodity-attrs/{id}", commodityAttr.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(commodityAttr.getId().intValue()))
            .andExpect(jsonPath("$.goodsId").value(DEFAULT_GOODS_ID))
            .andExpect(jsonPath("$.spu").value(DEFAULT_SPU.toString()))
            .andExpect(jsonPath("$.sku").value(DEFAULT_SKU.toString()))
            .andExpect(jsonPath("$.ctime").value(DEFAULT_CTIME.toString()))
            .andExpect(jsonPath("$.spuLastuptime").value(DEFAULT_SPU_LASTUPTIME.toString()))
            .andExpect(jsonPath("$.skuLastuptime").value(DEFAULT_SKU_LASTUPTIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCommodityAttr() throws Exception {
        // Get the commodityAttr
        restCommodityAttrMockMvc.perform(get("/api/commodity-attrs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCommodityAttr() throws Exception {
        // Initialize the database
        commodityAttrRepository.saveAndFlush(commodityAttr);

        int databaseSizeBeforeUpdate = commodityAttrRepository.findAll().size();

        // Update the commodityAttr
        CommodityAttr updatedCommodityAttr = commodityAttrRepository.findById(commodityAttr.getId()).get();
        // Disconnect from session so that the updates on updatedCommodityAttr are not directly saved in db
        em.detach(updatedCommodityAttr);
        updatedCommodityAttr
            .goodsId(UPDATED_GOODS_ID)
            .spu(UPDATED_SPU)
            .sku(UPDATED_SKU)
            .ctime(UPDATED_CTIME)
            .spuLastuptime(UPDATED_SPU_LASTUPTIME)
            .skuLastuptime(UPDATED_SKU_LASTUPTIME);

        restCommodityAttrMockMvc.perform(put("/api/commodity-attrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCommodityAttr)))
            .andExpect(status().isOk());

        // Validate the CommodityAttr in the database
        List<CommodityAttr> commodityAttrList = commodityAttrRepository.findAll();
        assertThat(commodityAttrList).hasSize(databaseSizeBeforeUpdate);
        CommodityAttr testCommodityAttr = commodityAttrList.get(commodityAttrList.size() - 1);
        assertThat(testCommodityAttr.getGoodsId()).isEqualTo(UPDATED_GOODS_ID);
        assertThat(testCommodityAttr.getSpu()).isEqualTo(UPDATED_SPU);
        assertThat(testCommodityAttr.getSku()).isEqualTo(UPDATED_SKU);
        assertThat(testCommodityAttr.getCtime()).isEqualTo(UPDATED_CTIME);
        assertThat(testCommodityAttr.getSpuLastuptime()).isEqualTo(UPDATED_SPU_LASTUPTIME);
        assertThat(testCommodityAttr.getSkuLastuptime()).isEqualTo(UPDATED_SKU_LASTUPTIME);
    }

    @Test
    @Transactional
    public void updateNonExistingCommodityAttr() throws Exception {
        int databaseSizeBeforeUpdate = commodityAttrRepository.findAll().size();

        // Create the CommodityAttr

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommodityAttrMockMvc.perform(put("/api/commodity-attrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commodityAttr)))
            .andExpect(status().isBadRequest());

        // Validate the CommodityAttr in the database
        List<CommodityAttr> commodityAttrList = commodityAttrRepository.findAll();
        assertThat(commodityAttrList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCommodityAttr() throws Exception {
        // Initialize the database
        commodityAttrRepository.saveAndFlush(commodityAttr);

        int databaseSizeBeforeDelete = commodityAttrRepository.findAll().size();

        // Delete the commodityAttr
        restCommodityAttrMockMvc.perform(delete("/api/commodity-attrs/{id}", commodityAttr.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<CommodityAttr> commodityAttrList = commodityAttrRepository.findAll();
        assertThat(commodityAttrList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommodityAttr.class);
        CommodityAttr commodityAttr1 = new CommodityAttr();
        commodityAttr1.setId(1L);
        CommodityAttr commodityAttr2 = new CommodityAttr();
        commodityAttr2.setId(commodityAttr1.getId());
        assertThat(commodityAttr1).isEqualTo(commodityAttr2);
        commodityAttr2.setId(2L);
        assertThat(commodityAttr1).isNotEqualTo(commodityAttr2);
        commodityAttr1.setId(null);
        assertThat(commodityAttr1).isNotEqualTo(commodityAttr2);
    }
}
