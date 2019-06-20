package com.weisen.www.code.yjf.shopmall.web.rest;

import com.weisen.www.code.yjf.shopmall.ShopmallApp;
import com.weisen.www.code.yjf.shopmall.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.shopmall.domain.Introduce;
import com.weisen.www.code.yjf.shopmall.repository.IntroduceRepository;
import com.weisen.www.code.yjf.shopmall.service.IntroduceService;
import com.weisen.www.code.yjf.shopmall.service.dto.IntroduceDTO;
import com.weisen.www.code.yjf.shopmall.service.mapper.IntroduceMapper;
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
 * Integration tests for the {@Link IntroduceResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, ShopmallApp.class})
public class IntroduceResourceIT {

    private static final String DEFAULT_COMMODITYID = "AAAAAAAAAA";
    private static final String UPDATED_COMMODITYID = "BBBBBBBBBB";

    private static final String DEFAULT_INTRODUCETYPE = "AAAAAAAAAA";
    private static final String UPDATED_INTRODUCETYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

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
    private IntroduceRepository introduceRepository;

    @Autowired
    private IntroduceMapper introduceMapper;

    @Autowired
    private IntroduceService introduceService;

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

    private MockMvc restIntroduceMockMvc;

    private Introduce introduce;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IntroduceResource introduceResource = new IntroduceResource(introduceService);
        this.restIntroduceMockMvc = MockMvcBuilders.standaloneSetup(introduceResource)
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
    public static Introduce createEntity(EntityManager em) {
        Introduce introduce = new Introduce()
            .commodityid(DEFAULT_COMMODITYID)
            .introducetype(DEFAULT_INTRODUCETYPE)
            .content(DEFAULT_CONTENT)
            .order(DEFAULT_ORDER)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return introduce;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Introduce createUpdatedEntity(EntityManager em) {
        Introduce introduce = new Introduce()
            .commodityid(UPDATED_COMMODITYID)
            .introducetype(UPDATED_INTRODUCETYPE)
            .content(UPDATED_CONTENT)
            .order(UPDATED_ORDER)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return introduce;
    }

    @BeforeEach
    public void initTest() {
        introduce = createEntity(em);
    }

    @Test
    @Transactional
    public void createIntroduce() throws Exception {
        int databaseSizeBeforeCreate = introduceRepository.findAll().size();

        // Create the Introduce
        IntroduceDTO introduceDTO = introduceMapper.toDto(introduce);
        restIntroduceMockMvc.perform(post("/api/introduces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(introduceDTO)))
            .andExpect(status().isCreated());

        // Validate the Introduce in the database
        List<Introduce> introduceList = introduceRepository.findAll();
        assertThat(introduceList).hasSize(databaseSizeBeforeCreate + 1);
        Introduce testIntroduce = introduceList.get(introduceList.size() - 1);
        assertThat(testIntroduce.getCommodityid()).isEqualTo(DEFAULT_COMMODITYID);
        assertThat(testIntroduce.getIntroducetype()).isEqualTo(DEFAULT_INTRODUCETYPE);
        assertThat(testIntroduce.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testIntroduce.getOrder()).isEqualTo(DEFAULT_ORDER);
        assertThat(testIntroduce.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testIntroduce.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testIntroduce.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testIntroduce.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testIntroduce.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testIntroduce.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testIntroduce.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createIntroduceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = introduceRepository.findAll().size();

        // Create the Introduce with an existing ID
        introduce.setId(1L);
        IntroduceDTO introduceDTO = introduceMapper.toDto(introduce);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIntroduceMockMvc.perform(post("/api/introduces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(introduceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Introduce in the database
        List<Introduce> introduceList = introduceRepository.findAll();
        assertThat(introduceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllIntroduces() throws Exception {
        // Initialize the database
        introduceRepository.saveAndFlush(introduce);

        // Get all the introduceList
        restIntroduceMockMvc.perform(get("/api/introduces?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(introduce.getId().intValue())))
            .andExpect(jsonPath("$.[*].commodityid").value(hasItem(DEFAULT_COMMODITYID.toString())))
            .andExpect(jsonPath("$.[*].introducetype").value(hasItem(DEFAULT_INTRODUCETYPE.toString())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
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
    public void getIntroduce() throws Exception {
        // Initialize the database
        introduceRepository.saveAndFlush(introduce);

        // Get the introduce
        restIntroduceMockMvc.perform(get("/api/introduces/{id}", introduce.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(introduce.getId().intValue()))
            .andExpect(jsonPath("$.commodityid").value(DEFAULT_COMMODITYID.toString()))
            .andExpect(jsonPath("$.introducetype").value(DEFAULT_INTRODUCETYPE.toString()))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()))
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
    public void getNonExistingIntroduce() throws Exception {
        // Get the introduce
        restIntroduceMockMvc.perform(get("/api/introduces/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIntroduce() throws Exception {
        // Initialize the database
        introduceRepository.saveAndFlush(introduce);

        int databaseSizeBeforeUpdate = introduceRepository.findAll().size();

        // Update the introduce
        Introduce updatedIntroduce = introduceRepository.findById(introduce.getId()).get();
        // Disconnect from session so that the updates on updatedIntroduce are not directly saved in db
        em.detach(updatedIntroduce);
        updatedIntroduce
            .commodityid(UPDATED_COMMODITYID)
            .introducetype(UPDATED_INTRODUCETYPE)
            .content(UPDATED_CONTENT)
            .order(UPDATED_ORDER)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        IntroduceDTO introduceDTO = introduceMapper.toDto(updatedIntroduce);

        restIntroduceMockMvc.perform(put("/api/introduces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(introduceDTO)))
            .andExpect(status().isOk());

        // Validate the Introduce in the database
        List<Introduce> introduceList = introduceRepository.findAll();
        assertThat(introduceList).hasSize(databaseSizeBeforeUpdate);
        Introduce testIntroduce = introduceList.get(introduceList.size() - 1);
        assertThat(testIntroduce.getCommodityid()).isEqualTo(UPDATED_COMMODITYID);
        assertThat(testIntroduce.getIntroducetype()).isEqualTo(UPDATED_INTRODUCETYPE);
        assertThat(testIntroduce.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testIntroduce.getOrder()).isEqualTo(UPDATED_ORDER);
        assertThat(testIntroduce.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testIntroduce.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testIntroduce.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testIntroduce.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testIntroduce.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testIntroduce.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testIntroduce.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingIntroduce() throws Exception {
        int databaseSizeBeforeUpdate = introduceRepository.findAll().size();

        // Create the Introduce
        IntroduceDTO introduceDTO = introduceMapper.toDto(introduce);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIntroduceMockMvc.perform(put("/api/introduces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(introduceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Introduce in the database
        List<Introduce> introduceList = introduceRepository.findAll();
        assertThat(introduceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteIntroduce() throws Exception {
        // Initialize the database
        introduceRepository.saveAndFlush(introduce);

        int databaseSizeBeforeDelete = introduceRepository.findAll().size();

        // Delete the introduce
        restIntroduceMockMvc.perform(delete("/api/introduces/{id}", introduce.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Introduce> introduceList = introduceRepository.findAll();
        assertThat(introduceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Introduce.class);
        Introduce introduce1 = new Introduce();
        introduce1.setId(1L);
        Introduce introduce2 = new Introduce();
        introduce2.setId(introduce1.getId());
        assertThat(introduce1).isEqualTo(introduce2);
        introduce2.setId(2L);
        assertThat(introduce1).isNotEqualTo(introduce2);
        introduce1.setId(null);
        assertThat(introduce1).isNotEqualTo(introduce2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IntroduceDTO.class);
        IntroduceDTO introduceDTO1 = new IntroduceDTO();
        introduceDTO1.setId(1L);
        IntroduceDTO introduceDTO2 = new IntroduceDTO();
        assertThat(introduceDTO1).isNotEqualTo(introduceDTO2);
        introduceDTO2.setId(introduceDTO1.getId());
        assertThat(introduceDTO1).isEqualTo(introduceDTO2);
        introduceDTO2.setId(2L);
        assertThat(introduceDTO1).isNotEqualTo(introduceDTO2);
        introduceDTO1.setId(null);
        assertThat(introduceDTO1).isNotEqualTo(introduceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(introduceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(introduceMapper.fromId(null)).isNull();
    }
}
