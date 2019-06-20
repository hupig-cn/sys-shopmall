package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.ShopmallApp;
import com.weisen.www.code.yjf.shopmall.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.shopmall.domain.Classification;
import com.weisen.www.code.yjf.shopmall.repository.ClassificationRepository;
import com.weisen.www.code.yjf.shopmall.service.ClassificationService;
import com.weisen.www.code.yjf.shopmall.service.dto.ClassificationDTO;
import com.weisen.www.code.yjf.shopmall.service.mapper.ClassificationMapper;
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
 * Integration tests for the {@Link ClassificationResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, ShopmallApp.class})
public class ClassificationResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUPERIOR = "AAAAAAAAAA";
    private static final String UPDATED_SUPERIOR = "BBBBBBBBBB";

    private static final String DEFAULT_ORDER = "AAAAAAAAAA";
    private static final String UPDATED_ORDER = "BBBBBBBBBB";

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
    private ClassificationRepository classificationRepository;

    @Autowired
    private ClassificationMapper classificationMapper;

    @Autowired
    private ClassificationService classificationService;

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

    private MockMvc restClassificationMockMvc;

    private Classification classification;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ClassificationResource classificationResource = new ClassificationResource(classificationService);
        this.restClassificationMockMvc = MockMvcBuilders.standaloneSetup(classificationResource)
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
    public static Classification createEntity(EntityManager em) {
        Classification classification = new Classification()
            .name(DEFAULT_NAME)
            .superior(DEFAULT_SUPERIOR)
            .order(DEFAULT_ORDER)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return classification;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Classification createUpdatedEntity(EntityManager em) {
        Classification classification = new Classification()
            .name(UPDATED_NAME)
            .superior(UPDATED_SUPERIOR)
            .order(UPDATED_ORDER)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return classification;
    }

    @BeforeEach
    public void initTest() {
        classification = createEntity(em);
    }

    @Test
    @Transactional
    public void createClassification() throws Exception {
        int databaseSizeBeforeCreate = classificationRepository.findAll().size();

        // Create the Classification
        ClassificationDTO classificationDTO = classificationMapper.toDto(classification);
        restClassificationMockMvc.perform(post("/api/classifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(classificationDTO)))
            .andExpect(status().isCreated());

        // Validate the Classification in the database
        List<Classification> classificationList = classificationRepository.findAll();
        assertThat(classificationList).hasSize(databaseSizeBeforeCreate + 1);
        Classification testClassification = classificationList.get(classificationList.size() - 1);
        assertThat(testClassification.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testClassification.getSuperior()).isEqualTo(DEFAULT_SUPERIOR);
        assertThat(testClassification.getOrder()).isEqualTo(DEFAULT_ORDER);
        assertThat(testClassification.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testClassification.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testClassification.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testClassification.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testClassification.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testClassification.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testClassification.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createClassificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = classificationRepository.findAll().size();

        // Create the Classification with an existing ID
        classification.setId(1L);
        ClassificationDTO classificationDTO = classificationMapper.toDto(classification);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClassificationMockMvc.perform(post("/api/classifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(classificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Classification in the database
        List<Classification> classificationList = classificationRepository.findAll();
        assertThat(classificationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllClassifications() throws Exception {
        // Initialize the database
        classificationRepository.saveAndFlush(classification);

        // Get all the classificationList
        restClassificationMockMvc.perform(get("/api/classifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(classification.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].superior").value(hasItem(DEFAULT_SUPERIOR.toString())))
            .andExpect(jsonPath("$.[*].order").value(hasItem(DEFAULT_ORDER.toString())))
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
    public void getClassification() throws Exception {
        // Initialize the database
        classificationRepository.saveAndFlush(classification);

        // Get the classification
        restClassificationMockMvc.perform(get("/api/classifications/{id}", classification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(classification.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.superior").value(DEFAULT_SUPERIOR.toString()))
            .andExpect(jsonPath("$.order").value(DEFAULT_ORDER.toString()))
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
    public void getNonExistingClassification() throws Exception {
        // Get the classification
        restClassificationMockMvc.perform(get("/api/classifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClassification() throws Exception {
        // Initialize the database
        classificationRepository.saveAndFlush(classification);

        int databaseSizeBeforeUpdate = classificationRepository.findAll().size();

        // Update the classification
        Classification updatedClassification = classificationRepository.findById(classification.getId()).get();
        // Disconnect from session so that the updates on updatedClassification are not directly saved in db
        em.detach(updatedClassification);
        updatedClassification
            .name(UPDATED_NAME)
            .superior(UPDATED_SUPERIOR)
            .order(UPDATED_ORDER)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        ClassificationDTO classificationDTO = classificationMapper.toDto(updatedClassification);

        restClassificationMockMvc.perform(put("/api/classifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(classificationDTO)))
            .andExpect(status().isOk());

        // Validate the Classification in the database
        List<Classification> classificationList = classificationRepository.findAll();
        assertThat(classificationList).hasSize(databaseSizeBeforeUpdate);
        Classification testClassification = classificationList.get(classificationList.size() - 1);
        assertThat(testClassification.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testClassification.getSuperior()).isEqualTo(UPDATED_SUPERIOR);
        assertThat(testClassification.getOrder()).isEqualTo(UPDATED_ORDER);
        assertThat(testClassification.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testClassification.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testClassification.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testClassification.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testClassification.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testClassification.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testClassification.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingClassification() throws Exception {
        int databaseSizeBeforeUpdate = classificationRepository.findAll().size();

        // Create the Classification
        ClassificationDTO classificationDTO = classificationMapper.toDto(classification);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClassificationMockMvc.perform(put("/api/classifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(classificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Classification in the database
        List<Classification> classificationList = classificationRepository.findAll();
        assertThat(classificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClassification() throws Exception {
        // Initialize the database
        classificationRepository.saveAndFlush(classification);

        int databaseSizeBeforeDelete = classificationRepository.findAll().size();

        // Delete the classification
        restClassificationMockMvc.perform(delete("/api/classifications/{id}", classification.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Classification> classificationList = classificationRepository.findAll();
        assertThat(classificationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Classification.class);
        Classification classification1 = new Classification();
        classification1.setId(1L);
        Classification classification2 = new Classification();
        classification2.setId(classification1.getId());
        assertThat(classification1).isEqualTo(classification2);
        classification2.setId(2L);
        assertThat(classification1).isNotEqualTo(classification2);
        classification1.setId(null);
        assertThat(classification1).isNotEqualTo(classification2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClassificationDTO.class);
        ClassificationDTO classificationDTO1 = new ClassificationDTO();
        classificationDTO1.setId(1L);
        ClassificationDTO classificationDTO2 = new ClassificationDTO();
        assertThat(classificationDTO1).isNotEqualTo(classificationDTO2);
        classificationDTO2.setId(classificationDTO1.getId());
        assertThat(classificationDTO1).isEqualTo(classificationDTO2);
        classificationDTO2.setId(2L);
        assertThat(classificationDTO1).isNotEqualTo(classificationDTO2);
        classificationDTO1.setId(null);
        assertThat(classificationDTO1).isNotEqualTo(classificationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(classificationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(classificationMapper.fromId(null)).isNull();
    }
}
