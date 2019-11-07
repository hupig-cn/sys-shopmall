package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.ShopmallApp;
import com.weisen.www.code.yjf.shopmall.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.shopmall.domain.V2OrderExtends;
import com.weisen.www.code.yjf.shopmall.repository.V2OrderExtendsRepository;
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
 * Integration tests for the {@Link V2OrderExtendsResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, ShopmallApp.class})
public class V2OrderExtendsResourceIT {

    private static final Long DEFAULT_OID = 1L;
    private static final Long UPDATED_OID = 2L;

    private static final Float DEFAULT_PAID_AMOUNT = 1F;
    private static final Float UPDATED_PAID_AMOUNT = 2F;

    private static final String DEFAULT_PAID_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_PAID_METHOD = "BBBBBBBBBB";

    private static final String DEFAULT_PAID_TIME = "AAAAAAAAAA";
    private static final String UPDATED_PAID_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_EXPRESS_STATE = "AAAAAAAAAA";
    private static final String UPDATED_EXPRESS_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_EXPRESS_TIME = "AAAAAAAAAA";
    private static final String UPDATED_EXPRESS_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_EXPRESS_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_EXPRESS_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_EXPRESS_NO = "AAAAAAAAAA";
    private static final String UPDATED_EXPRESS_NO = "BBBBBBBBBB";

    private static final String DEFAULT_EXPRESS_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_EXPRESS_REMARK = "BBBBBBBBBB";

    @Autowired
    private V2OrderExtendsRepository v2OrderExtendsRepository;

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

    private MockMvc restV2OrderExtendsMockMvc;

    private V2OrderExtends v2OrderExtends;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final V2OrderExtendsResource v2OrderExtendsResource = new V2OrderExtendsResource(v2OrderExtendsRepository);
        this.restV2OrderExtendsMockMvc = MockMvcBuilders.standaloneSetup(v2OrderExtendsResource)
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
    public static V2OrderExtends createEntity(EntityManager em) {
        V2OrderExtends v2OrderExtends = new V2OrderExtends()
            .oid(DEFAULT_OID)
            .paidAmount(DEFAULT_PAID_AMOUNT)
            .paidMethod(DEFAULT_PAID_METHOD)
            .paidTime(DEFAULT_PAID_TIME)
            .expressState(DEFAULT_EXPRESS_STATE)
            .expressTime(DEFAULT_EXPRESS_TIME)
            .expressCompany(DEFAULT_EXPRESS_COMPANY)
            .expressNo(DEFAULT_EXPRESS_NO)
            .expressRemark(DEFAULT_EXPRESS_REMARK);
        return v2OrderExtends;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static V2OrderExtends createUpdatedEntity(EntityManager em) {
        V2OrderExtends v2OrderExtends = new V2OrderExtends()
            .oid(UPDATED_OID)
            .paidAmount(UPDATED_PAID_AMOUNT)
            .paidMethod(UPDATED_PAID_METHOD)
            .paidTime(UPDATED_PAID_TIME)
            .expressState(UPDATED_EXPRESS_STATE)
            .expressTime(UPDATED_EXPRESS_TIME)
            .expressCompany(UPDATED_EXPRESS_COMPANY)
            .expressNo(UPDATED_EXPRESS_NO)
            .expressRemark(UPDATED_EXPRESS_REMARK);
        return v2OrderExtends;
    }

    @BeforeEach
    public void initTest() {
        v2OrderExtends = createEntity(em);
    }

    @Test
    @Transactional
    public void createV2OrderExtends() throws Exception {
        int databaseSizeBeforeCreate = v2OrderExtendsRepository.findAll().size();

        // Create the V2OrderExtends
        restV2OrderExtendsMockMvc.perform(post("/api/v-2-order-extends")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(v2OrderExtends)))
            .andExpect(status().isCreated());

        // Validate the V2OrderExtends in the database
        List<V2OrderExtends> v2OrderExtendsList = v2OrderExtendsRepository.findAll();
        assertThat(v2OrderExtendsList).hasSize(databaseSizeBeforeCreate + 1);
        V2OrderExtends testV2OrderExtends = v2OrderExtendsList.get(v2OrderExtendsList.size() - 1);
        assertThat(testV2OrderExtends.getOid()).isEqualTo(DEFAULT_OID);
        assertThat(testV2OrderExtends.getPaidAmount()).isEqualTo(DEFAULT_PAID_AMOUNT);
        assertThat(testV2OrderExtends.getPaidMethod()).isEqualTo(DEFAULT_PAID_METHOD);
        assertThat(testV2OrderExtends.getPaidTime()).isEqualTo(DEFAULT_PAID_TIME);
        assertThat(testV2OrderExtends.getExpressState()).isEqualTo(DEFAULT_EXPRESS_STATE);
        assertThat(testV2OrderExtends.getExpressTime()).isEqualTo(DEFAULT_EXPRESS_TIME);
        assertThat(testV2OrderExtends.getExpressCompany()).isEqualTo(DEFAULT_EXPRESS_COMPANY);
        assertThat(testV2OrderExtends.getExpressNo()).isEqualTo(DEFAULT_EXPRESS_NO);
        assertThat(testV2OrderExtends.getExpressRemark()).isEqualTo(DEFAULT_EXPRESS_REMARK);
    }

    @Test
    @Transactional
    public void createV2OrderExtendsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = v2OrderExtendsRepository.findAll().size();

        // Create the V2OrderExtends with an existing ID
        v2OrderExtends.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restV2OrderExtendsMockMvc.perform(post("/api/v-2-order-extends")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(v2OrderExtends)))
            .andExpect(status().isBadRequest());

        // Validate the V2OrderExtends in the database
        List<V2OrderExtends> v2OrderExtendsList = v2OrderExtendsRepository.findAll();
        assertThat(v2OrderExtendsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllV2OrderExtends() throws Exception {
        // Initialize the database
        v2OrderExtendsRepository.saveAndFlush(v2OrderExtends);

        // Get all the v2OrderExtendsList
        restV2OrderExtendsMockMvc.perform(get("/api/v-2-order-extends?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(v2OrderExtends.getId().intValue())))
            .andExpect(jsonPath("$.[*].oid").value(hasItem(DEFAULT_OID.intValue())))
            .andExpect(jsonPath("$.[*].paidAmount").value(hasItem(DEFAULT_PAID_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].paidMethod").value(hasItem(DEFAULT_PAID_METHOD.toString())))
            .andExpect(jsonPath("$.[*].paidTime").value(hasItem(DEFAULT_PAID_TIME.toString())))
            .andExpect(jsonPath("$.[*].expressState").value(hasItem(DEFAULT_EXPRESS_STATE.toString())))
            .andExpect(jsonPath("$.[*].expressTime").value(hasItem(DEFAULT_EXPRESS_TIME.toString())))
            .andExpect(jsonPath("$.[*].expressCompany").value(hasItem(DEFAULT_EXPRESS_COMPANY.toString())))
            .andExpect(jsonPath("$.[*].expressNo").value(hasItem(DEFAULT_EXPRESS_NO.toString())))
            .andExpect(jsonPath("$.[*].expressRemark").value(hasItem(DEFAULT_EXPRESS_REMARK.toString())));
    }
    
    @Test
    @Transactional
    public void getV2OrderExtends() throws Exception {
        // Initialize the database
        v2OrderExtendsRepository.saveAndFlush(v2OrderExtends);

        // Get the v2OrderExtends
        restV2OrderExtendsMockMvc.perform(get("/api/v-2-order-extends/{id}", v2OrderExtends.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(v2OrderExtends.getId().intValue()))
            .andExpect(jsonPath("$.oid").value(DEFAULT_OID.intValue()))
            .andExpect(jsonPath("$.paidAmount").value(DEFAULT_PAID_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.paidMethod").value(DEFAULT_PAID_METHOD.toString()))
            .andExpect(jsonPath("$.paidTime").value(DEFAULT_PAID_TIME.toString()))
            .andExpect(jsonPath("$.expressState").value(DEFAULT_EXPRESS_STATE.toString()))
            .andExpect(jsonPath("$.expressTime").value(DEFAULT_EXPRESS_TIME.toString()))
            .andExpect(jsonPath("$.expressCompany").value(DEFAULT_EXPRESS_COMPANY.toString()))
            .andExpect(jsonPath("$.expressNo").value(DEFAULT_EXPRESS_NO.toString()))
            .andExpect(jsonPath("$.expressRemark").value(DEFAULT_EXPRESS_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingV2OrderExtends() throws Exception {
        // Get the v2OrderExtends
        restV2OrderExtendsMockMvc.perform(get("/api/v-2-order-extends/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateV2OrderExtends() throws Exception {
        // Initialize the database
        v2OrderExtendsRepository.saveAndFlush(v2OrderExtends);

        int databaseSizeBeforeUpdate = v2OrderExtendsRepository.findAll().size();

        // Update the v2OrderExtends
        V2OrderExtends updatedV2OrderExtends = v2OrderExtendsRepository.findById(v2OrderExtends.getId()).get();
        // Disconnect from session so that the updates on updatedV2OrderExtends are not directly saved in db
        em.detach(updatedV2OrderExtends);
        updatedV2OrderExtends
            .oid(UPDATED_OID)
            .paidAmount(UPDATED_PAID_AMOUNT)
            .paidMethod(UPDATED_PAID_METHOD)
            .paidTime(UPDATED_PAID_TIME)
            .expressState(UPDATED_EXPRESS_STATE)
            .expressTime(UPDATED_EXPRESS_TIME)
            .expressCompany(UPDATED_EXPRESS_COMPANY)
            .expressNo(UPDATED_EXPRESS_NO)
            .expressRemark(UPDATED_EXPRESS_REMARK);

        restV2OrderExtendsMockMvc.perform(put("/api/v-2-order-extends")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedV2OrderExtends)))
            .andExpect(status().isOk());

        // Validate the V2OrderExtends in the database
        List<V2OrderExtends> v2OrderExtendsList = v2OrderExtendsRepository.findAll();
        assertThat(v2OrderExtendsList).hasSize(databaseSizeBeforeUpdate);
        V2OrderExtends testV2OrderExtends = v2OrderExtendsList.get(v2OrderExtendsList.size() - 1);
        assertThat(testV2OrderExtends.getOid()).isEqualTo(UPDATED_OID);
        assertThat(testV2OrderExtends.getPaidAmount()).isEqualTo(UPDATED_PAID_AMOUNT);
        assertThat(testV2OrderExtends.getPaidMethod()).isEqualTo(UPDATED_PAID_METHOD);
        assertThat(testV2OrderExtends.getPaidTime()).isEqualTo(UPDATED_PAID_TIME);
        assertThat(testV2OrderExtends.getExpressState()).isEqualTo(UPDATED_EXPRESS_STATE);
        assertThat(testV2OrderExtends.getExpressTime()).isEqualTo(UPDATED_EXPRESS_TIME);
        assertThat(testV2OrderExtends.getExpressCompany()).isEqualTo(UPDATED_EXPRESS_COMPANY);
        assertThat(testV2OrderExtends.getExpressNo()).isEqualTo(UPDATED_EXPRESS_NO);
        assertThat(testV2OrderExtends.getExpressRemark()).isEqualTo(UPDATED_EXPRESS_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingV2OrderExtends() throws Exception {
        int databaseSizeBeforeUpdate = v2OrderExtendsRepository.findAll().size();

        // Create the V2OrderExtends

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restV2OrderExtendsMockMvc.perform(put("/api/v-2-order-extends")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(v2OrderExtends)))
            .andExpect(status().isBadRequest());

        // Validate the V2OrderExtends in the database
        List<V2OrderExtends> v2OrderExtendsList = v2OrderExtendsRepository.findAll();
        assertThat(v2OrderExtendsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteV2OrderExtends() throws Exception {
        // Initialize the database
        v2OrderExtendsRepository.saveAndFlush(v2OrderExtends);

        int databaseSizeBeforeDelete = v2OrderExtendsRepository.findAll().size();

        // Delete the v2OrderExtends
        restV2OrderExtendsMockMvc.perform(delete("/api/v-2-order-extends/{id}", v2OrderExtends.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<V2OrderExtends> v2OrderExtendsList = v2OrderExtendsRepository.findAll();
        assertThat(v2OrderExtendsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(V2OrderExtends.class);
        V2OrderExtends v2OrderExtends1 = new V2OrderExtends();
        v2OrderExtends1.setId(1L);
        V2OrderExtends v2OrderExtends2 = new V2OrderExtends();
        v2OrderExtends2.setId(v2OrderExtends1.getId());
        assertThat(v2OrderExtends1).isEqualTo(v2OrderExtends2);
        v2OrderExtends2.setId(2L);
        assertThat(v2OrderExtends1).isNotEqualTo(v2OrderExtends2);
        v2OrderExtends1.setId(null);
        assertThat(v2OrderExtends1).isNotEqualTo(v2OrderExtends2);
    }
}
