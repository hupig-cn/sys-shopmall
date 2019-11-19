package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.ShopmallApp;
import com.weisen.www.code.yjf.shopmall.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.shopmall.domain.ClassificationAttr;
import com.weisen.www.code.yjf.shopmall.repository.ClassificationAttrRepository;
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
 * Integration tests for the {@Link ClassificationAttrResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, ShopmallApp.class})
public class ClassificationAttrResourceIT {

    private static final Integer DEFAULT_CID = 1;
    private static final Integer UPDATED_CID = 2;

    private static final Integer DEFAULT_AID = 1;
    private static final Integer UPDATED_AID = 2;

    private static final Integer DEFAULT_AVID = 1;
    private static final Integer UPDATED_AVID = 2;

    @Autowired
    private ClassificationAttrRepository classificationAttrRepository;

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

    private MockMvc restClassificationAttrMockMvc;

    private ClassificationAttr classificationAttr;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ClassificationAttrResource classificationAttrResource = new ClassificationAttrResource(classificationAttrRepository);
        this.restClassificationAttrMockMvc = MockMvcBuilders.standaloneSetup(classificationAttrResource)
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
    public static ClassificationAttr createEntity(EntityManager em) {
        ClassificationAttr classificationAttr = new ClassificationAttr()
            .cid(DEFAULT_CID)
            .aid(DEFAULT_AID)
            .avid(DEFAULT_AVID);
        return classificationAttr;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClassificationAttr createUpdatedEntity(EntityManager em) {
        ClassificationAttr classificationAttr = new ClassificationAttr()
            .cid(UPDATED_CID)
            .aid(UPDATED_AID)
            .avid(UPDATED_AVID);
        return classificationAttr;
    }

    @BeforeEach
    public void initTest() {
        classificationAttr = createEntity(em);
    }

    @Test
    @Transactional
    public void createClassificationAttr() throws Exception {
        int databaseSizeBeforeCreate = classificationAttrRepository.findAll().size();

        // Create the ClassificationAttr
        restClassificationAttrMockMvc.perform(post("/api/classification-attrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(classificationAttr)))
            .andExpect(status().isCreated());

        // Validate the ClassificationAttr in the database
        List<ClassificationAttr> classificationAttrList = classificationAttrRepository.findAll();
        assertThat(classificationAttrList).hasSize(databaseSizeBeforeCreate + 1);
        ClassificationAttr testClassificationAttr = classificationAttrList.get(classificationAttrList.size() - 1);
        assertThat(testClassificationAttr.getCid()).isEqualTo(DEFAULT_CID);
        assertThat(testClassificationAttr.getAid()).isEqualTo(DEFAULT_AID);
        assertThat(testClassificationAttr.getAvid()).isEqualTo(DEFAULT_AVID);
    }

    @Test
    @Transactional
    public void createClassificationAttrWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = classificationAttrRepository.findAll().size();

        // Create the ClassificationAttr with an existing ID
        classificationAttr.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClassificationAttrMockMvc.perform(post("/api/classification-attrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(classificationAttr)))
            .andExpect(status().isBadRequest());

        // Validate the ClassificationAttr in the database
        List<ClassificationAttr> classificationAttrList = classificationAttrRepository.findAll();
        assertThat(classificationAttrList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllClassificationAttrs() throws Exception {
        // Initialize the database
        classificationAttrRepository.saveAndFlush(classificationAttr);

        // Get all the classificationAttrList
        restClassificationAttrMockMvc.perform(get("/api/classification-attrs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(classificationAttr.getId().intValue())))
            .andExpect(jsonPath("$.[*].cid").value(hasItem(DEFAULT_CID)))
            .andExpect(jsonPath("$.[*].aid").value(hasItem(DEFAULT_AID)))
            .andExpect(jsonPath("$.[*].avid").value(hasItem(DEFAULT_AVID)));
    }
    
    @Test
    @Transactional
    public void getClassificationAttr() throws Exception {
        // Initialize the database
        classificationAttrRepository.saveAndFlush(classificationAttr);

        // Get the classificationAttr
        restClassificationAttrMockMvc.perform(get("/api/classification-attrs/{id}", classificationAttr.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(classificationAttr.getId().intValue()))
            .andExpect(jsonPath("$.cid").value(DEFAULT_CID))
            .andExpect(jsonPath("$.aid").value(DEFAULT_AID))
            .andExpect(jsonPath("$.avid").value(DEFAULT_AVID));
    }

    @Test
    @Transactional
    public void getNonExistingClassificationAttr() throws Exception {
        // Get the classificationAttr
        restClassificationAttrMockMvc.perform(get("/api/classification-attrs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClassificationAttr() throws Exception {
        // Initialize the database
        classificationAttrRepository.saveAndFlush(classificationAttr);

        int databaseSizeBeforeUpdate = classificationAttrRepository.findAll().size();

        // Update the classificationAttr
        ClassificationAttr updatedClassificationAttr = classificationAttrRepository.findById(classificationAttr.getId()).get();
        // Disconnect from session so that the updates on updatedClassificationAttr are not directly saved in db
        em.detach(updatedClassificationAttr);
        updatedClassificationAttr
            .cid(UPDATED_CID)
            .aid(UPDATED_AID)
            .avid(UPDATED_AVID);

        restClassificationAttrMockMvc.perform(put("/api/classification-attrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedClassificationAttr)))
            .andExpect(status().isOk());

        // Validate the ClassificationAttr in the database
        List<ClassificationAttr> classificationAttrList = classificationAttrRepository.findAll();
        assertThat(classificationAttrList).hasSize(databaseSizeBeforeUpdate);
        ClassificationAttr testClassificationAttr = classificationAttrList.get(classificationAttrList.size() - 1);
        assertThat(testClassificationAttr.getCid()).isEqualTo(UPDATED_CID);
        assertThat(testClassificationAttr.getAid()).isEqualTo(UPDATED_AID);
        assertThat(testClassificationAttr.getAvid()).isEqualTo(UPDATED_AVID);
    }

    @Test
    @Transactional
    public void updateNonExistingClassificationAttr() throws Exception {
        int databaseSizeBeforeUpdate = classificationAttrRepository.findAll().size();

        // Create the ClassificationAttr

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClassificationAttrMockMvc.perform(put("/api/classification-attrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(classificationAttr)))
            .andExpect(status().isBadRequest());

        // Validate the ClassificationAttr in the database
        List<ClassificationAttr> classificationAttrList = classificationAttrRepository.findAll();
        assertThat(classificationAttrList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClassificationAttr() throws Exception {
        // Initialize the database
        classificationAttrRepository.saveAndFlush(classificationAttr);

        int databaseSizeBeforeDelete = classificationAttrRepository.findAll().size();

        // Delete the classificationAttr
        restClassificationAttrMockMvc.perform(delete("/api/classification-attrs/{id}", classificationAttr.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ClassificationAttr> classificationAttrList = classificationAttrRepository.findAll();
        assertThat(classificationAttrList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClassificationAttr.class);
        ClassificationAttr classificationAttr1 = new ClassificationAttr();
        classificationAttr1.setId(1L);
        ClassificationAttr classificationAttr2 = new ClassificationAttr();
        classificationAttr2.setId(classificationAttr1.getId());
        assertThat(classificationAttr1).isEqualTo(classificationAttr2);
        classificationAttr2.setId(2L);
        assertThat(classificationAttr1).isNotEqualTo(classificationAttr2);
        classificationAttr1.setId(null);
        assertThat(classificationAttr1).isNotEqualTo(classificationAttr2);
    }
}
