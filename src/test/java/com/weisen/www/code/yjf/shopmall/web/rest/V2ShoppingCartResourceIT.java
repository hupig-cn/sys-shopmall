package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.ShopmallApp;
import com.weisen.www.code.yjf.shopmall.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.shopmall.domain.V2ShoppingCart;
import com.weisen.www.code.yjf.shopmall.repository.V2ShoppingCartRepository;
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
 * Integration tests for the {@Link V2ShoppingCartResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, ShopmallApp.class})
public class V2ShoppingCartResourceIT {

    private static final Long DEFAULT_USERID = 1L;
    private static final Long UPDATED_USERID = 2L;

    private static final Long DEFAULT_GOODS_ID = 1L;
    private static final Long UPDATED_GOODS_ID = 2L;

    private static final Float DEFAULT_SKU_PRICE = 1F;
    private static final Float UPDATED_SKU_PRICE = 2F;

    private static final String DEFAULT_SKU_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_SKU_CONTENT = "BBBBBBBBBB";

    private static final Long DEFAULT_QUANTITY = 1L;
    private static final Long UPDATED_QUANTITY = 2L;

    private static final String DEFAULT_CTIME = "AAAAAAAAAA";
    private static final String UPDATED_CTIME = "BBBBBBBBBB";

    @Autowired
    private V2ShoppingCartRepository v2ShoppingCartRepository;

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

    private MockMvc restV2ShoppingCartMockMvc;

    private V2ShoppingCart v2ShoppingCart;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final V2ShoppingCartResource v2ShoppingCartResource = new V2ShoppingCartResource(v2ShoppingCartRepository);
        this.restV2ShoppingCartMockMvc = MockMvcBuilders.standaloneSetup(v2ShoppingCartResource)
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
    public static V2ShoppingCart createEntity(EntityManager em) {
        V2ShoppingCart v2ShoppingCart = new V2ShoppingCart()
            .userid(DEFAULT_USERID)
            .goodsId(DEFAULT_GOODS_ID)
            .skuPrice(DEFAULT_SKU_PRICE)
            .skuContent(DEFAULT_SKU_CONTENT)
            .quantity(DEFAULT_QUANTITY)
            .ctime(DEFAULT_CTIME);
        return v2ShoppingCart;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static V2ShoppingCart createUpdatedEntity(EntityManager em) {
        V2ShoppingCart v2ShoppingCart = new V2ShoppingCart()
            .userid(UPDATED_USERID)
            .goodsId(UPDATED_GOODS_ID)
            .skuPrice(UPDATED_SKU_PRICE)
            .skuContent(UPDATED_SKU_CONTENT)
            .quantity(UPDATED_QUANTITY)
            .ctime(UPDATED_CTIME);
        return v2ShoppingCart;
    }

    @BeforeEach
    public void initTest() {
        v2ShoppingCart = createEntity(em);
    }

    @Test
    @Transactional
    public void createV2ShoppingCart() throws Exception {
        int databaseSizeBeforeCreate = v2ShoppingCartRepository.findAll().size();

        // Create the V2ShoppingCart
        restV2ShoppingCartMockMvc.perform(post("/api/v-2-shopping-carts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(v2ShoppingCart)))
            .andExpect(status().isCreated());

        // Validate the V2ShoppingCart in the database
        List<V2ShoppingCart> v2ShoppingCartList = v2ShoppingCartRepository.findAll();
        assertThat(v2ShoppingCartList).hasSize(databaseSizeBeforeCreate + 1);
        V2ShoppingCart testV2ShoppingCart = v2ShoppingCartList.get(v2ShoppingCartList.size() - 1);
        assertThat(testV2ShoppingCart.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testV2ShoppingCart.getGoodsId()).isEqualTo(DEFAULT_GOODS_ID);
        assertThat(testV2ShoppingCart.getSkuPrice()).isEqualTo(DEFAULT_SKU_PRICE);
        assertThat(testV2ShoppingCart.getSkuContent()).isEqualTo(DEFAULT_SKU_CONTENT);
        assertThat(testV2ShoppingCart.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testV2ShoppingCart.getCtime()).isEqualTo(DEFAULT_CTIME);
    }

    @Test
    @Transactional
    public void createV2ShoppingCartWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = v2ShoppingCartRepository.findAll().size();

        // Create the V2ShoppingCart with an existing ID
        v2ShoppingCart.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restV2ShoppingCartMockMvc.perform(post("/api/v-2-shopping-carts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(v2ShoppingCart)))
            .andExpect(status().isBadRequest());

        // Validate the V2ShoppingCart in the database
        List<V2ShoppingCart> v2ShoppingCartList = v2ShoppingCartRepository.findAll();
        assertThat(v2ShoppingCartList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllV2ShoppingCarts() throws Exception {
        // Initialize the database
        v2ShoppingCartRepository.saveAndFlush(v2ShoppingCart);

        // Get all the v2ShoppingCartList
        restV2ShoppingCartMockMvc.perform(get("/api/v-2-shopping-carts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(v2ShoppingCart.getId().intValue())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.intValue())))
            .andExpect(jsonPath("$.[*].goodsId").value(hasItem(DEFAULT_GOODS_ID.intValue())))
            .andExpect(jsonPath("$.[*].skuPrice").value(hasItem(DEFAULT_SKU_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].skuContent").value(hasItem(DEFAULT_SKU_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].ctime").value(hasItem(DEFAULT_CTIME.toString())));
    }
    
    @Test
    @Transactional
    public void getV2ShoppingCart() throws Exception {
        // Initialize the database
        v2ShoppingCartRepository.saveAndFlush(v2ShoppingCart);

        // Get the v2ShoppingCart
        restV2ShoppingCartMockMvc.perform(get("/api/v-2-shopping-carts/{id}", v2ShoppingCart.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(v2ShoppingCart.getId().intValue()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.intValue()))
            .andExpect(jsonPath("$.goodsId").value(DEFAULT_GOODS_ID.intValue()))
            .andExpect(jsonPath("$.skuPrice").value(DEFAULT_SKU_PRICE.doubleValue()))
            .andExpect(jsonPath("$.skuContent").value(DEFAULT_SKU_CONTENT.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.ctime").value(DEFAULT_CTIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingV2ShoppingCart() throws Exception {
        // Get the v2ShoppingCart
        restV2ShoppingCartMockMvc.perform(get("/api/v-2-shopping-carts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateV2ShoppingCart() throws Exception {
        // Initialize the database
        v2ShoppingCartRepository.saveAndFlush(v2ShoppingCart);

        int databaseSizeBeforeUpdate = v2ShoppingCartRepository.findAll().size();

        // Update the v2ShoppingCart
        V2ShoppingCart updatedV2ShoppingCart = v2ShoppingCartRepository.findById(v2ShoppingCart.getId()).get();
        // Disconnect from session so that the updates on updatedV2ShoppingCart are not directly saved in db
        em.detach(updatedV2ShoppingCart);
        updatedV2ShoppingCart
            .userid(UPDATED_USERID)
            .goodsId(UPDATED_GOODS_ID)
            .skuPrice(UPDATED_SKU_PRICE)
            .skuContent(UPDATED_SKU_CONTENT)
            .quantity(UPDATED_QUANTITY)
            .ctime(UPDATED_CTIME);

        restV2ShoppingCartMockMvc.perform(put("/api/v-2-shopping-carts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedV2ShoppingCart)))
            .andExpect(status().isOk());

        // Validate the V2ShoppingCart in the database
        List<V2ShoppingCart> v2ShoppingCartList = v2ShoppingCartRepository.findAll();
        assertThat(v2ShoppingCartList).hasSize(databaseSizeBeforeUpdate);
        V2ShoppingCart testV2ShoppingCart = v2ShoppingCartList.get(v2ShoppingCartList.size() - 1);
        assertThat(testV2ShoppingCart.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testV2ShoppingCart.getGoodsId()).isEqualTo(UPDATED_GOODS_ID);
        assertThat(testV2ShoppingCart.getSkuPrice()).isEqualTo(UPDATED_SKU_PRICE);
        assertThat(testV2ShoppingCart.getSkuContent()).isEqualTo(UPDATED_SKU_CONTENT);
        assertThat(testV2ShoppingCart.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testV2ShoppingCart.getCtime()).isEqualTo(UPDATED_CTIME);
    }

    @Test
    @Transactional
    public void updateNonExistingV2ShoppingCart() throws Exception {
        int databaseSizeBeforeUpdate = v2ShoppingCartRepository.findAll().size();

        // Create the V2ShoppingCart

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restV2ShoppingCartMockMvc.perform(put("/api/v-2-shopping-carts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(v2ShoppingCart)))
            .andExpect(status().isBadRequest());

        // Validate the V2ShoppingCart in the database
        List<V2ShoppingCart> v2ShoppingCartList = v2ShoppingCartRepository.findAll();
        assertThat(v2ShoppingCartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteV2ShoppingCart() throws Exception {
        // Initialize the database
        v2ShoppingCartRepository.saveAndFlush(v2ShoppingCart);

        int databaseSizeBeforeDelete = v2ShoppingCartRepository.findAll().size();

        // Delete the v2ShoppingCart
        restV2ShoppingCartMockMvc.perform(delete("/api/v-2-shopping-carts/{id}", v2ShoppingCart.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<V2ShoppingCart> v2ShoppingCartList = v2ShoppingCartRepository.findAll();
        assertThat(v2ShoppingCartList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(V2ShoppingCart.class);
        V2ShoppingCart v2ShoppingCart1 = new V2ShoppingCart();
        v2ShoppingCart1.setId(1L);
        V2ShoppingCart v2ShoppingCart2 = new V2ShoppingCart();
        v2ShoppingCart2.setId(v2ShoppingCart1.getId());
        assertThat(v2ShoppingCart1).isEqualTo(v2ShoppingCart2);
        v2ShoppingCart2.setId(2L);
        assertThat(v2ShoppingCart1).isNotEqualTo(v2ShoppingCart2);
        v2ShoppingCart1.setId(null);
        assertThat(v2ShoppingCart1).isNotEqualTo(v2ShoppingCart2);
    }
}
