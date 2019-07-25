package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.ShopmallApp;
import com.weisen.www.code.yjf.shopmall.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.shopmall.domain.Shopping;
import com.weisen.www.code.yjf.shopmall.repository.ShoppingRepository;
import com.weisen.www.code.yjf.shopmall.service.ShoppingService;
import com.weisen.www.code.yjf.shopmall.service.dto.ShoppingDTO;
import com.weisen.www.code.yjf.shopmall.service.mapper.ShoppingMapper;
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
 * Integration tests for the {@Link ShoppingResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, ShopmallApp.class})
public class ShoppingResourceIT {

    private static final Long DEFAULT_USERID = 1L;
    private static final Long UPDATED_USERID = 2L;

    private static final String DEFAULT_COMMODITYID = "AAAAAAAAAA";
    private static final String UPDATED_COMMODITYID = "BBBBBBBBBB";

    private static final String DEFAULT_SPECIFICATIONSID = "AAAAAAAAAA";
    private static final String UPDATED_SPECIFICATIONSID = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUM = 1;
    private static final Integer UPDATED_NUM = 2;

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
    private ShoppingRepository shoppingRepository;

    @Autowired
    private ShoppingMapper shoppingMapper;

    @Autowired
    private ShoppingService shoppingService;

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

    private MockMvc restShoppingMockMvc;

    private Shopping shopping;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ShoppingResource shoppingResource = new ShoppingResource(shoppingService);
        this.restShoppingMockMvc = MockMvcBuilders.standaloneSetup(shoppingResource)
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
    public static Shopping createEntity(EntityManager em) {
        Shopping shopping = new Shopping()
            .userid(DEFAULT_USERID)
            .commodityid(DEFAULT_COMMODITYID)
            .specificationsid(DEFAULT_SPECIFICATIONSID)
            .num(DEFAULT_NUM)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return shopping;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Shopping createUpdatedEntity(EntityManager em) {
        Shopping shopping = new Shopping()
            .userid(UPDATED_USERID)
            .commodityid(UPDATED_COMMODITYID)
            .specificationsid(UPDATED_SPECIFICATIONSID)
            .num(UPDATED_NUM)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return shopping;
    }

    @BeforeEach
    public void initTest() {
        shopping = createEntity(em);
    }

    @Test
    @Transactional
    public void createShopping() throws Exception {
        int databaseSizeBeforeCreate = shoppingRepository.findAll().size();

        // Create the Shopping
        ShoppingDTO shoppingDTO = shoppingMapper.toDto(shopping);
        restShoppingMockMvc.perform(post("/api/shoppings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shoppingDTO)))
            .andExpect(status().isCreated());

        // Validate the Shopping in the database
        List<Shopping> shoppingList = shoppingRepository.findAll();
        assertThat(shoppingList).hasSize(databaseSizeBeforeCreate + 1);
        Shopping testShopping = shoppingList.get(shoppingList.size() - 1);
        assertThat(testShopping.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testShopping.getCommodityid()).isEqualTo(DEFAULT_COMMODITYID);
        assertThat(testShopping.getSpecificationsid()).isEqualTo(DEFAULT_SPECIFICATIONSID);
        assertThat(testShopping.getNum()).isEqualTo(DEFAULT_NUM);
        assertThat(testShopping.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testShopping.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testShopping.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testShopping.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testShopping.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testShopping.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testShopping.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createShoppingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = shoppingRepository.findAll().size();

        // Create the Shopping with an existing ID
        shopping.setId(1L);
        ShoppingDTO shoppingDTO = shoppingMapper.toDto(shopping);

        // An entity with an existing ID cannot be created, so this API call must fail
        restShoppingMockMvc.perform(post("/api/shoppings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shoppingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Shopping in the database
        List<Shopping> shoppingList = shoppingRepository.findAll();
        assertThat(shoppingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllShoppings() throws Exception {
        // Initialize the database
        shoppingRepository.saveAndFlush(shopping);

        // Get all the shoppingList
        restShoppingMockMvc.perform(get("/api/shoppings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shopping.getId().intValue())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.toString())))
            .andExpect(jsonPath("$.[*].commodityid").value(hasItem(DEFAULT_COMMODITYID.toString())))
            .andExpect(jsonPath("$.[*].specificationsid").value(hasItem(DEFAULT_SPECIFICATIONSID.toString())))
            .andExpect(jsonPath("$.[*].num").value(hasItem(DEFAULT_NUM.toString())))
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
    public void getShopping() throws Exception {
        // Initialize the database
        shoppingRepository.saveAndFlush(shopping);

        // Get the shopping
        restShoppingMockMvc.perform(get("/api/shoppings/{id}", shopping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(shopping.getId().intValue()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.toString()))
            .andExpect(jsonPath("$.commodityid").value(DEFAULT_COMMODITYID.toString()))
            .andExpect(jsonPath("$.specificationsid").value(DEFAULT_SPECIFICATIONSID.toString()))
            .andExpect(jsonPath("$.num").value(DEFAULT_NUM.toString()))
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
    public void getNonExistingShopping() throws Exception {
        // Get the shopping
        restShoppingMockMvc.perform(get("/api/shoppings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateShopping() throws Exception {
        // Initialize the database
        shoppingRepository.saveAndFlush(shopping);

        int databaseSizeBeforeUpdate = shoppingRepository.findAll().size();

        // Update the shopping
        Shopping updatedShopping = shoppingRepository.findById(shopping.getId()).get();
        // Disconnect from session so that the updates on updatedShopping are not directly saved in db
        em.detach(updatedShopping);
        updatedShopping
            .userid(UPDATED_USERID)
            .commodityid(UPDATED_COMMODITYID)
            .specificationsid(UPDATED_SPECIFICATIONSID)
            .num(UPDATED_NUM)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        ShoppingDTO shoppingDTO = shoppingMapper.toDto(updatedShopping);

        restShoppingMockMvc.perform(put("/api/shoppings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shoppingDTO)))
            .andExpect(status().isOk());

        // Validate the Shopping in the database
        List<Shopping> shoppingList = shoppingRepository.findAll();
        assertThat(shoppingList).hasSize(databaseSizeBeforeUpdate);
        Shopping testShopping = shoppingList.get(shoppingList.size() - 1);
        assertThat(testShopping.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testShopping.getCommodityid()).isEqualTo(UPDATED_COMMODITYID);
        assertThat(testShopping.getSpecificationsid()).isEqualTo(UPDATED_SPECIFICATIONSID);
        assertThat(testShopping.getNum()).isEqualTo(UPDATED_NUM);
        assertThat(testShopping.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testShopping.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testShopping.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testShopping.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testShopping.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testShopping.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testShopping.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingShopping() throws Exception {
        int databaseSizeBeforeUpdate = shoppingRepository.findAll().size();

        // Create the Shopping
        ShoppingDTO shoppingDTO = shoppingMapper.toDto(shopping);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShoppingMockMvc.perform(put("/api/shoppings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shoppingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Shopping in the database
        List<Shopping> shoppingList = shoppingRepository.findAll();
        assertThat(shoppingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteShopping() throws Exception {
        // Initialize the database
        shoppingRepository.saveAndFlush(shopping);

        int databaseSizeBeforeDelete = shoppingRepository.findAll().size();

        // Delete the shopping
        restShoppingMockMvc.perform(delete("/api/shoppings/{id}", shopping.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Shopping> shoppingList = shoppingRepository.findAll();
        assertThat(shoppingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Shopping.class);
        Shopping shopping1 = new Shopping();
        shopping1.setId(1L);
        Shopping shopping2 = new Shopping();
        shopping2.setId(shopping1.getId());
        assertThat(shopping1).isEqualTo(shopping2);
        shopping2.setId(2L);
        assertThat(shopping1).isNotEqualTo(shopping2);
        shopping1.setId(null);
        assertThat(shopping1).isNotEqualTo(shopping2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShoppingDTO.class);
        ShoppingDTO shoppingDTO1 = new ShoppingDTO();
        shoppingDTO1.setId(1L);
        ShoppingDTO shoppingDTO2 = new ShoppingDTO();
        assertThat(shoppingDTO1).isNotEqualTo(shoppingDTO2);
        shoppingDTO2.setId(shoppingDTO1.getId());
        assertThat(shoppingDTO1).isEqualTo(shoppingDTO2);
        shoppingDTO2.setId(2L);
        assertThat(shoppingDTO1).isNotEqualTo(shoppingDTO2);
        shoppingDTO1.setId(null);
        assertThat(shoppingDTO1).isNotEqualTo(shoppingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(shoppingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(shoppingMapper.fromId(null)).isNull();
    }
}
