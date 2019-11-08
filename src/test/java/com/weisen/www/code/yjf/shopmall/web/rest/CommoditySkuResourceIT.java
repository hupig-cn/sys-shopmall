package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.ShopmallApp;
import com.weisen.www.code.yjf.shopmall.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.shopmall.domain.CommoditySku;
import com.weisen.www.code.yjf.shopmall.repository.CommoditySkuRepository;
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
import java.math.BigDecimal;
import java.util.List;

import static com.weisen.www.code.yjf.shopmall.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link CommoditySkuResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, ShopmallApp.class})
public class CommoditySkuResourceIT {

    private static final Integer DEFAULT_GOODS_ID = 1;
    private static final Integer UPDATED_GOODS_ID = 2;

    private static final String DEFAULT_SKUID = "AAAAAAAAAA";
    private static final String UPDATED_SKUID = "BBBBBBBBBB";

    private static final String DEFAULT_SKU_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_SKU_CONTENT = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_UNIT_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_UNIT_PRICE = new BigDecimal(2);

    private static final Integer DEFAULT_STOCK = 1;
    private static final Integer UPDATED_STOCK = 2;

    private static final Integer DEFAULT_STOCKS = 1;
    private static final Integer UPDATED_STOCKS = 2;

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ICON = "AAAAAAAAAA";
    private static final String UPDATED_ICON = "BBBBBBBBBB";

    @Autowired
    private CommoditySkuRepository commoditySkuRepository;

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

    private MockMvc restCommoditySkuMockMvc;

    private CommoditySku commoditySku;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CommoditySkuResource commoditySkuResource = new CommoditySkuResource(commoditySkuRepository);
        this.restCommoditySkuMockMvc = MockMvcBuilders.standaloneSetup(commoditySkuResource)
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
    public static CommoditySku createEntity(EntityManager em) {
        CommoditySku commoditySku = new CommoditySku()
            .goodsId(DEFAULT_GOODS_ID)
            .skuid(DEFAULT_SKUID)
            .skuContent(DEFAULT_SKU_CONTENT)
            .unitPrice(DEFAULT_UNIT_PRICE)
            .stock(DEFAULT_STOCK)
            .stocks(DEFAULT_STOCKS)
            .code(DEFAULT_CODE)
            .icon(DEFAULT_ICON);
        return commoditySku;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommoditySku createUpdatedEntity(EntityManager em) {
        CommoditySku commoditySku = new CommoditySku()
            .goodsId(UPDATED_GOODS_ID)
            .skuid(UPDATED_SKUID)
            .skuContent(UPDATED_SKU_CONTENT)
            .unitPrice(UPDATED_UNIT_PRICE)
            .stock(UPDATED_STOCK)
            .stocks(UPDATED_STOCKS)
            .code(UPDATED_CODE)
            .icon(UPDATED_ICON);
        return commoditySku;
    }

    @BeforeEach
    public void initTest() {
        commoditySku = createEntity(em);
    }

    @Test
    @Transactional
    public void createCommoditySku() throws Exception {
        int databaseSizeBeforeCreate = commoditySkuRepository.findAll().size();

        // Create the CommoditySku
        restCommoditySkuMockMvc.perform(post("/api/commodity-skus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commoditySku)))
            .andExpect(status().isCreated());

        // Validate the CommoditySku in the database
        List<CommoditySku> commoditySkuList = commoditySkuRepository.findAll();
        assertThat(commoditySkuList).hasSize(databaseSizeBeforeCreate + 1);
        CommoditySku testCommoditySku = commoditySkuList.get(commoditySkuList.size() - 1);
        assertThat(testCommoditySku.getGoodsId()).isEqualTo(DEFAULT_GOODS_ID);
        assertThat(testCommoditySku.getSkuid()).isEqualTo(DEFAULT_SKUID);
        assertThat(testCommoditySku.getSkuContent()).isEqualTo(DEFAULT_SKU_CONTENT);
        assertThat(testCommoditySku.getUnitPrice()).isEqualTo(DEFAULT_UNIT_PRICE);
        assertThat(testCommoditySku.getStock()).isEqualTo(DEFAULT_STOCK);
        assertThat(testCommoditySku.getStocks()).isEqualTo(DEFAULT_STOCKS);
        assertThat(testCommoditySku.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCommoditySku.getIcon()).isEqualTo(DEFAULT_ICON);
    }

    @Test
    @Transactional
    public void createCommoditySkuWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = commoditySkuRepository.findAll().size();

        // Create the CommoditySku with an existing ID
        commoditySku.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommoditySkuMockMvc.perform(post("/api/commodity-skus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commoditySku)))
            .andExpect(status().isBadRequest());

        // Validate the CommoditySku in the database
        List<CommoditySku> commoditySkuList = commoditySkuRepository.findAll();
        assertThat(commoditySkuList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCommoditySkus() throws Exception {
        // Initialize the database
        commoditySkuRepository.saveAndFlush(commoditySku);

        // Get all the commoditySkuList
        restCommoditySkuMockMvc.perform(get("/api/commodity-skus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commoditySku.getId().intValue())))
            .andExpect(jsonPath("$.[*].goodsId").value(hasItem(DEFAULT_GOODS_ID)))
            .andExpect(jsonPath("$.[*].skuid").value(hasItem(DEFAULT_SKUID.toString())))
            .andExpect(jsonPath("$.[*].skuContent").value(hasItem(DEFAULT_SKU_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].unitPrice").value(hasItem(DEFAULT_UNIT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].stock").value(hasItem(DEFAULT_STOCK)))
            .andExpect(jsonPath("$.[*].stocks").value(hasItem(DEFAULT_STOCKS)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].icon").value(hasItem(DEFAULT_ICON.toString())));
    }
    
    @Test
    @Transactional
    public void getCommoditySku() throws Exception {
        // Initialize the database
        commoditySkuRepository.saveAndFlush(commoditySku);

        // Get the commoditySku
        restCommoditySkuMockMvc.perform(get("/api/commodity-skus/{id}", commoditySku.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(commoditySku.getId().intValue()))
            .andExpect(jsonPath("$.goodsId").value(DEFAULT_GOODS_ID))
            .andExpect(jsonPath("$.skuid").value(DEFAULT_SKUID.toString()))
            .andExpect(jsonPath("$.skuContent").value(DEFAULT_SKU_CONTENT.toString()))
            .andExpect(jsonPath("$.unitPrice").value(DEFAULT_UNIT_PRICE.intValue()))
            .andExpect(jsonPath("$.stock").value(DEFAULT_STOCK))
            .andExpect(jsonPath("$.stocks").value(DEFAULT_STOCKS))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.icon").value(DEFAULT_ICON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCommoditySku() throws Exception {
        // Get the commoditySku
        restCommoditySkuMockMvc.perform(get("/api/commodity-skus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCommoditySku() throws Exception {
        // Initialize the database
        commoditySkuRepository.saveAndFlush(commoditySku);

        int databaseSizeBeforeUpdate = commoditySkuRepository.findAll().size();

        // Update the commoditySku
        CommoditySku updatedCommoditySku = commoditySkuRepository.findById(commoditySku.getId()).get();
        // Disconnect from session so that the updates on updatedCommoditySku are not directly saved in db
        em.detach(updatedCommoditySku);
        updatedCommoditySku
            .goodsId(UPDATED_GOODS_ID)
            .skuid(UPDATED_SKUID)
            .skuContent(UPDATED_SKU_CONTENT)
            .unitPrice(UPDATED_UNIT_PRICE)
            .stock(UPDATED_STOCK)
            .stocks(UPDATED_STOCKS)
            .code(UPDATED_CODE)
            .icon(UPDATED_ICON);

        restCommoditySkuMockMvc.perform(put("/api/commodity-skus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCommoditySku)))
            .andExpect(status().isOk());

        // Validate the CommoditySku in the database
        List<CommoditySku> commoditySkuList = commoditySkuRepository.findAll();
        assertThat(commoditySkuList).hasSize(databaseSizeBeforeUpdate);
        CommoditySku testCommoditySku = commoditySkuList.get(commoditySkuList.size() - 1);
        assertThat(testCommoditySku.getGoodsId()).isEqualTo(UPDATED_GOODS_ID);
        assertThat(testCommoditySku.getSkuid()).isEqualTo(UPDATED_SKUID);
        assertThat(testCommoditySku.getSkuContent()).isEqualTo(UPDATED_SKU_CONTENT);
        assertThat(testCommoditySku.getUnitPrice()).isEqualTo(UPDATED_UNIT_PRICE);
        assertThat(testCommoditySku.getStock()).isEqualTo(UPDATED_STOCK);
        assertThat(testCommoditySku.getStocks()).isEqualTo(UPDATED_STOCKS);
        assertThat(testCommoditySku.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCommoditySku.getIcon()).isEqualTo(UPDATED_ICON);
    }

    @Test
    @Transactional
    public void updateNonExistingCommoditySku() throws Exception {
        int databaseSizeBeforeUpdate = commoditySkuRepository.findAll().size();

        // Create the CommoditySku

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommoditySkuMockMvc.perform(put("/api/commodity-skus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(commoditySku)))
            .andExpect(status().isBadRequest());

        // Validate the CommoditySku in the database
        List<CommoditySku> commoditySkuList = commoditySkuRepository.findAll();
        assertThat(commoditySkuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCommoditySku() throws Exception {
        // Initialize the database
        commoditySkuRepository.saveAndFlush(commoditySku);

        int databaseSizeBeforeDelete = commoditySkuRepository.findAll().size();

        // Delete the commoditySku
        restCommoditySkuMockMvc.perform(delete("/api/commodity-skus/{id}", commoditySku.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<CommoditySku> commoditySkuList = commoditySkuRepository.findAll();
        assertThat(commoditySkuList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommoditySku.class);
        CommoditySku commoditySku1 = new CommoditySku();
        commoditySku1.setId(1L);
        CommoditySku commoditySku2 = new CommoditySku();
        commoditySku2.setId(commoditySku1.getId());
        assertThat(commoditySku1).isEqualTo(commoditySku2);
        commoditySku2.setId(2L);
        assertThat(commoditySku1).isNotEqualTo(commoditySku2);
        commoditySku1.setId(null);
        assertThat(commoditySku1).isNotEqualTo(commoditySku2);
    }
}
