package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.ShopmallApp;
import com.weisen.www.code.yjf.shopmall.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.shopmall.domain.V2PublishAttrvalue;
import com.weisen.www.code.yjf.shopmall.repository.V2PublishAttrvalueRepository;
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
 * Integration tests for the {@Link V2PublishAttrvalueResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, ShopmallApp.class})
public class V2PublishAttrvalueResourceIT {

    private static final Long DEFAULT_AID = 1L;
    private static final Long UPDATED_AID = 2L;

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_PUBLISH_OVERWRITE = "AAAAAAAAAA";
    private static final String UPDATED_PUBLISH_OVERWRITE = "BBBBBBBBBB";

    private static final String DEFAULT_PUBLISH_DISPLAYSORT = "AAAAAAAAAA";
    private static final String UPDATED_PUBLISH_DISPLAYSORT = "BBBBBBBBBB";

    private static final Long DEFAULT_STATE = 1L;
    private static final Long UPDATED_STATE = 2L;

    private static final String DEFAULT_CTIME = "AAAAAAAAAA";
    private static final String UPDATED_CTIME = "BBBBBBBBBB";

    @Autowired
    private V2PublishAttrvalueRepository v2PublishAttrvalueRepository;

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

    private MockMvc restV2PublishAttrvalueMockMvc;

    private V2PublishAttrvalue v2PublishAttrvalue;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final V2PublishAttrvalueResource v2PublishAttrvalueResource = new V2PublishAttrvalueResource(v2PublishAttrvalueRepository);
        this.restV2PublishAttrvalueMockMvc = MockMvcBuilders.standaloneSetup(v2PublishAttrvalueResource)
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
    public static V2PublishAttrvalue createEntity(EntityManager em) {
        V2PublishAttrvalue v2PublishAttrvalue = new V2PublishAttrvalue()
            .aid(DEFAULT_AID)
            .title(DEFAULT_TITLE)
            .value(DEFAULT_VALUE)
            .publishOverwrite(DEFAULT_PUBLISH_OVERWRITE)
            .publishDisplaysort(DEFAULT_PUBLISH_DISPLAYSORT)
            .state(DEFAULT_STATE)
            .ctime(DEFAULT_CTIME);
        return v2PublishAttrvalue;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static V2PublishAttrvalue createUpdatedEntity(EntityManager em) {
        V2PublishAttrvalue v2PublishAttrvalue = new V2PublishAttrvalue()
            .aid(UPDATED_AID)
            .title(UPDATED_TITLE)
            .value(UPDATED_VALUE)
            .publishOverwrite(UPDATED_PUBLISH_OVERWRITE)
            .publishDisplaysort(UPDATED_PUBLISH_DISPLAYSORT)
            .state(UPDATED_STATE)
            .ctime(UPDATED_CTIME);
        return v2PublishAttrvalue;
    }

    @BeforeEach
    public void initTest() {
        v2PublishAttrvalue = createEntity(em);
    }

    @Test
    @Transactional
    public void createV2PublishAttrvalue() throws Exception {
        int databaseSizeBeforeCreate = v2PublishAttrvalueRepository.findAll().size();

        // Create the V2PublishAttrvalue
        restV2PublishAttrvalueMockMvc.perform(post("/api/v-2-publish-attrvalues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(v2PublishAttrvalue)))
            .andExpect(status().isCreated());

        // Validate the V2PublishAttrvalue in the database
        List<V2PublishAttrvalue> v2PublishAttrvalueList = v2PublishAttrvalueRepository.findAll();
        assertThat(v2PublishAttrvalueList).hasSize(databaseSizeBeforeCreate + 1);
        V2PublishAttrvalue testV2PublishAttrvalue = v2PublishAttrvalueList.get(v2PublishAttrvalueList.size() - 1);
        assertThat(testV2PublishAttrvalue.getAid()).isEqualTo(DEFAULT_AID);
        assertThat(testV2PublishAttrvalue.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testV2PublishAttrvalue.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testV2PublishAttrvalue.getPublishOverwrite()).isEqualTo(DEFAULT_PUBLISH_OVERWRITE);
        assertThat(testV2PublishAttrvalue.getPublishDisplaysort()).isEqualTo(DEFAULT_PUBLISH_DISPLAYSORT);
        assertThat(testV2PublishAttrvalue.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testV2PublishAttrvalue.getCtime()).isEqualTo(DEFAULT_CTIME);
    }

    @Test
    @Transactional
    public void createV2PublishAttrvalueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = v2PublishAttrvalueRepository.findAll().size();

        // Create the V2PublishAttrvalue with an existing ID
        v2PublishAttrvalue.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restV2PublishAttrvalueMockMvc.perform(post("/api/v-2-publish-attrvalues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(v2PublishAttrvalue)))
            .andExpect(status().isBadRequest());

        // Validate the V2PublishAttrvalue in the database
        List<V2PublishAttrvalue> v2PublishAttrvalueList = v2PublishAttrvalueRepository.findAll();
        assertThat(v2PublishAttrvalueList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllV2PublishAttrvalues() throws Exception {
        // Initialize the database
        v2PublishAttrvalueRepository.saveAndFlush(v2PublishAttrvalue);

        // Get all the v2PublishAttrvalueList
        restV2PublishAttrvalueMockMvc.perform(get("/api/v-2-publish-attrvalues?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(v2PublishAttrvalue.getId().intValue())))
            .andExpect(jsonPath("$.[*].aid").value(hasItem(DEFAULT_AID.intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())))
            .andExpect(jsonPath("$.[*].publishOverwrite").value(hasItem(DEFAULT_PUBLISH_OVERWRITE.toString())))
            .andExpect(jsonPath("$.[*].publishDisplaysort").value(hasItem(DEFAULT_PUBLISH_DISPLAYSORT.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.intValue())))
            .andExpect(jsonPath("$.[*].ctime").value(hasItem(DEFAULT_CTIME.toString())));
    }
    
    @Test
    @Transactional
    public void getV2PublishAttrvalue() throws Exception {
        // Initialize the database
        v2PublishAttrvalueRepository.saveAndFlush(v2PublishAttrvalue);

        // Get the v2PublishAttrvalue
        restV2PublishAttrvalueMockMvc.perform(get("/api/v-2-publish-attrvalues/{id}", v2PublishAttrvalue.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(v2PublishAttrvalue.getId().intValue()))
            .andExpect(jsonPath("$.aid").value(DEFAULT_AID.intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()))
            .andExpect(jsonPath("$.publishOverwrite").value(DEFAULT_PUBLISH_OVERWRITE.toString()))
            .andExpect(jsonPath("$.publishDisplaysort").value(DEFAULT_PUBLISH_DISPLAYSORT.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.intValue()))
            .andExpect(jsonPath("$.ctime").value(DEFAULT_CTIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingV2PublishAttrvalue() throws Exception {
        // Get the v2PublishAttrvalue
        restV2PublishAttrvalueMockMvc.perform(get("/api/v-2-publish-attrvalues/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateV2PublishAttrvalue() throws Exception {
        // Initialize the database
        v2PublishAttrvalueRepository.saveAndFlush(v2PublishAttrvalue);

        int databaseSizeBeforeUpdate = v2PublishAttrvalueRepository.findAll().size();

        // Update the v2PublishAttrvalue
        V2PublishAttrvalue updatedV2PublishAttrvalue = v2PublishAttrvalueRepository.findById(v2PublishAttrvalue.getId()).get();
        // Disconnect from session so that the updates on updatedV2PublishAttrvalue are not directly saved in db
        em.detach(updatedV2PublishAttrvalue);
        updatedV2PublishAttrvalue
            .aid(UPDATED_AID)
            .title(UPDATED_TITLE)
            .value(UPDATED_VALUE)
            .publishOverwrite(UPDATED_PUBLISH_OVERWRITE)
            .publishDisplaysort(UPDATED_PUBLISH_DISPLAYSORT)
            .state(UPDATED_STATE)
            .ctime(UPDATED_CTIME);

        restV2PublishAttrvalueMockMvc.perform(put("/api/v-2-publish-attrvalues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedV2PublishAttrvalue)))
            .andExpect(status().isOk());

        // Validate the V2PublishAttrvalue in the database
        List<V2PublishAttrvalue> v2PublishAttrvalueList = v2PublishAttrvalueRepository.findAll();
        assertThat(v2PublishAttrvalueList).hasSize(databaseSizeBeforeUpdate);
        V2PublishAttrvalue testV2PublishAttrvalue = v2PublishAttrvalueList.get(v2PublishAttrvalueList.size() - 1);
        assertThat(testV2PublishAttrvalue.getAid()).isEqualTo(UPDATED_AID);
        assertThat(testV2PublishAttrvalue.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testV2PublishAttrvalue.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testV2PublishAttrvalue.getPublishOverwrite()).isEqualTo(UPDATED_PUBLISH_OVERWRITE);
        assertThat(testV2PublishAttrvalue.getPublishDisplaysort()).isEqualTo(UPDATED_PUBLISH_DISPLAYSORT);
        assertThat(testV2PublishAttrvalue.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testV2PublishAttrvalue.getCtime()).isEqualTo(UPDATED_CTIME);
    }

    @Test
    @Transactional
    public void updateNonExistingV2PublishAttrvalue() throws Exception {
        int databaseSizeBeforeUpdate = v2PublishAttrvalueRepository.findAll().size();

        // Create the V2PublishAttrvalue

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restV2PublishAttrvalueMockMvc.perform(put("/api/v-2-publish-attrvalues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(v2PublishAttrvalue)))
            .andExpect(status().isBadRequest());

        // Validate the V2PublishAttrvalue in the database
        List<V2PublishAttrvalue> v2PublishAttrvalueList = v2PublishAttrvalueRepository.findAll();
        assertThat(v2PublishAttrvalueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteV2PublishAttrvalue() throws Exception {
        // Initialize the database
        v2PublishAttrvalueRepository.saveAndFlush(v2PublishAttrvalue);

        int databaseSizeBeforeDelete = v2PublishAttrvalueRepository.findAll().size();

        // Delete the v2PublishAttrvalue
        restV2PublishAttrvalueMockMvc.perform(delete("/api/v-2-publish-attrvalues/{id}", v2PublishAttrvalue.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<V2PublishAttrvalue> v2PublishAttrvalueList = v2PublishAttrvalueRepository.findAll();
        assertThat(v2PublishAttrvalueList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(V2PublishAttrvalue.class);
        V2PublishAttrvalue v2PublishAttrvalue1 = new V2PublishAttrvalue();
        v2PublishAttrvalue1.setId(1L);
        V2PublishAttrvalue v2PublishAttrvalue2 = new V2PublishAttrvalue();
        v2PublishAttrvalue2.setId(v2PublishAttrvalue1.getId());
        assertThat(v2PublishAttrvalue1).isEqualTo(v2PublishAttrvalue2);
        v2PublishAttrvalue2.setId(2L);
        assertThat(v2PublishAttrvalue1).isNotEqualTo(v2PublishAttrvalue2);
        v2PublishAttrvalue1.setId(null);
        assertThat(v2PublishAttrvalue1).isNotEqualTo(v2PublishAttrvalue2);
    }
}
