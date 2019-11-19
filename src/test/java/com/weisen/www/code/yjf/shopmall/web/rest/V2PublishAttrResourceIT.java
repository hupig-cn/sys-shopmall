package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.ShopmallApp;
import com.weisen.www.code.yjf.shopmall.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.shopmall.domain.V2PublishAttr;
import com.weisen.www.code.yjf.shopmall.repository.V2PublishAttrRepository;
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
 * Integration tests for the {@Link V2PublishAttrResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, ShopmallApp.class})
public class V2PublishAttrResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_FORM_REQUIRED = 1L;
    private static final Long UPDATED_FORM_REQUIRED = 2L;

    private static final String DEFAULT_WIDGET_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_WIDGET_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_WIDGET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_WIDGET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CTIME = "AAAAAAAAAA";
    private static final String UPDATED_CTIME = "BBBBBBBBBB";

    @Autowired
    private V2PublishAttrRepository v2PublishAttrRepository;

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

    private MockMvc restV2PublishAttrMockMvc;

    private V2PublishAttr v2PublishAttr;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final V2PublishAttrResource v2PublishAttrResource = new V2PublishAttrResource(v2PublishAttrRepository);
        this.restV2PublishAttrMockMvc = MockMvcBuilders.standaloneSetup(v2PublishAttrResource)
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
    public static V2PublishAttr createEntity(EntityManager em) {
        V2PublishAttr v2PublishAttr = new V2PublishAttr()
            .name(DEFAULT_NAME)
            .formRequired(DEFAULT_FORM_REQUIRED)
            .widgetType(DEFAULT_WIDGET_TYPE)
            .widgetName(DEFAULT_WIDGET_NAME)
            .ctime(DEFAULT_CTIME);
        return v2PublishAttr;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static V2PublishAttr createUpdatedEntity(EntityManager em) {
        V2PublishAttr v2PublishAttr = new V2PublishAttr()
            .name(UPDATED_NAME)
            .formRequired(UPDATED_FORM_REQUIRED)
            .widgetType(UPDATED_WIDGET_TYPE)
            .widgetName(UPDATED_WIDGET_NAME)
            .ctime(UPDATED_CTIME);
        return v2PublishAttr;
    }

    @BeforeEach
    public void initTest() {
        v2PublishAttr = createEntity(em);
    }

    @Test
    @Transactional
    public void createV2PublishAttr() throws Exception {
        int databaseSizeBeforeCreate = v2PublishAttrRepository.findAll().size();

        // Create the V2PublishAttr
        restV2PublishAttrMockMvc.perform(post("/api/v-2-publish-attrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(v2PublishAttr)))
            .andExpect(status().isCreated());

        // Validate the V2PublishAttr in the database
        List<V2PublishAttr> v2PublishAttrList = v2PublishAttrRepository.findAll();
        assertThat(v2PublishAttrList).hasSize(databaseSizeBeforeCreate + 1);
        V2PublishAttr testV2PublishAttr = v2PublishAttrList.get(v2PublishAttrList.size() - 1);
        assertThat(testV2PublishAttr.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testV2PublishAttr.getFormRequired()).isEqualTo(DEFAULT_FORM_REQUIRED);
        assertThat(testV2PublishAttr.getWidgetType()).isEqualTo(DEFAULT_WIDGET_TYPE);
        assertThat(testV2PublishAttr.getWidgetName()).isEqualTo(DEFAULT_WIDGET_NAME);
        assertThat(testV2PublishAttr.getCtime()).isEqualTo(DEFAULT_CTIME);
    }

    @Test
    @Transactional
    public void createV2PublishAttrWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = v2PublishAttrRepository.findAll().size();

        // Create the V2PublishAttr with an existing ID
        v2PublishAttr.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restV2PublishAttrMockMvc.perform(post("/api/v-2-publish-attrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(v2PublishAttr)))
            .andExpect(status().isBadRequest());

        // Validate the V2PublishAttr in the database
        List<V2PublishAttr> v2PublishAttrList = v2PublishAttrRepository.findAll();
        assertThat(v2PublishAttrList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllV2PublishAttrs() throws Exception {
        // Initialize the database
        v2PublishAttrRepository.saveAndFlush(v2PublishAttr);

        // Get all the v2PublishAttrList
        restV2PublishAttrMockMvc.perform(get("/api/v-2-publish-attrs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(v2PublishAttr.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].formRequired").value(hasItem(DEFAULT_FORM_REQUIRED.intValue())))
            .andExpect(jsonPath("$.[*].widgetType").value(hasItem(DEFAULT_WIDGET_TYPE.toString())))
            .andExpect(jsonPath("$.[*].widgetName").value(hasItem(DEFAULT_WIDGET_NAME.toString())))
            .andExpect(jsonPath("$.[*].ctime").value(hasItem(DEFAULT_CTIME.toString())));
    }
    
    @Test
    @Transactional
    public void getV2PublishAttr() throws Exception {
        // Initialize the database
        v2PublishAttrRepository.saveAndFlush(v2PublishAttr);

        // Get the v2PublishAttr
        restV2PublishAttrMockMvc.perform(get("/api/v-2-publish-attrs/{id}", v2PublishAttr.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(v2PublishAttr.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.formRequired").value(DEFAULT_FORM_REQUIRED.intValue()))
            .andExpect(jsonPath("$.widgetType").value(DEFAULT_WIDGET_TYPE.toString()))
            .andExpect(jsonPath("$.widgetName").value(DEFAULT_WIDGET_NAME.toString()))
            .andExpect(jsonPath("$.ctime").value(DEFAULT_CTIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingV2PublishAttr() throws Exception {
        // Get the v2PublishAttr
        restV2PublishAttrMockMvc.perform(get("/api/v-2-publish-attrs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateV2PublishAttr() throws Exception {
        // Initialize the database
        v2PublishAttrRepository.saveAndFlush(v2PublishAttr);

        int databaseSizeBeforeUpdate = v2PublishAttrRepository.findAll().size();

        // Update the v2PublishAttr
        V2PublishAttr updatedV2PublishAttr = v2PublishAttrRepository.findById(v2PublishAttr.getId()).get();
        // Disconnect from session so that the updates on updatedV2PublishAttr are not directly saved in db
        em.detach(updatedV2PublishAttr);
        updatedV2PublishAttr
            .name(UPDATED_NAME)
            .formRequired(UPDATED_FORM_REQUIRED)
            .widgetType(UPDATED_WIDGET_TYPE)
            .widgetName(UPDATED_WIDGET_NAME)
            .ctime(UPDATED_CTIME);

        restV2PublishAttrMockMvc.perform(put("/api/v-2-publish-attrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedV2PublishAttr)))
            .andExpect(status().isOk());

        // Validate the V2PublishAttr in the database
        List<V2PublishAttr> v2PublishAttrList = v2PublishAttrRepository.findAll();
        assertThat(v2PublishAttrList).hasSize(databaseSizeBeforeUpdate);
        V2PublishAttr testV2PublishAttr = v2PublishAttrList.get(v2PublishAttrList.size() - 1);
        assertThat(testV2PublishAttr.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testV2PublishAttr.getFormRequired()).isEqualTo(UPDATED_FORM_REQUIRED);
        assertThat(testV2PublishAttr.getWidgetType()).isEqualTo(UPDATED_WIDGET_TYPE);
        assertThat(testV2PublishAttr.getWidgetName()).isEqualTo(UPDATED_WIDGET_NAME);
        assertThat(testV2PublishAttr.getCtime()).isEqualTo(UPDATED_CTIME);
    }

    @Test
    @Transactional
    public void updateNonExistingV2PublishAttr() throws Exception {
        int databaseSizeBeforeUpdate = v2PublishAttrRepository.findAll().size();

        // Create the V2PublishAttr

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restV2PublishAttrMockMvc.perform(put("/api/v-2-publish-attrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(v2PublishAttr)))
            .andExpect(status().isBadRequest());

        // Validate the V2PublishAttr in the database
        List<V2PublishAttr> v2PublishAttrList = v2PublishAttrRepository.findAll();
        assertThat(v2PublishAttrList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteV2PublishAttr() throws Exception {
        // Initialize the database
        v2PublishAttrRepository.saveAndFlush(v2PublishAttr);

        int databaseSizeBeforeDelete = v2PublishAttrRepository.findAll().size();

        // Delete the v2PublishAttr
        restV2PublishAttrMockMvc.perform(delete("/api/v-2-publish-attrs/{id}", v2PublishAttr.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<V2PublishAttr> v2PublishAttrList = v2PublishAttrRepository.findAll();
        assertThat(v2PublishAttrList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(V2PublishAttr.class);
        V2PublishAttr v2PublishAttr1 = new V2PublishAttr();
        v2PublishAttr1.setId(1L);
        V2PublishAttr v2PublishAttr2 = new V2PublishAttr();
        v2PublishAttr2.setId(v2PublishAttr1.getId());
        assertThat(v2PublishAttr1).isEqualTo(v2PublishAttr2);
        v2PublishAttr2.setId(2L);
        assertThat(v2PublishAttr1).isNotEqualTo(v2PublishAttr2);
        v2PublishAttr1.setId(null);
        assertThat(v2PublishAttr1).isNotEqualTo(v2PublishAttr2);
    }
}
