//package com.weisen.www.code.yjf.shopmall.web.rest;
//
//import com.weisen.www.code.yjf.shopmall.ShopmallApp;
//
//import com.weisen.www.code.yjf.shopmall.config.SecurityBeanOverrideConfiguration;
//
//import com.weisen.www.code.yjf.shopmall.domain.Order;
//import com.weisen.www.code.yjf.shopmall.repository.OrderRepository;
//import com.weisen.www.code.yjf.shopmall.service.OrderService;
//import com.weisen.www.code.yjf.shopmall.service.dto.OrderDTO;
//import com.weisen.www.code.yjf.shopmall.service.mapper.OrderMapper;
//import com.weisen.www.code.yjf.shopmall.web.rest.errors.ExceptionTranslator;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.validation.Validator;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//
//
//import static com.weisen.www.code.yjf.shopmall.web.rest.TestUtil.createFormattingConversionService;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.Matchers.hasItem;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
///**
// * Test class for the OrderResource REST controller.
// *
// * @see OrderResource
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, ShopmallApp.class})
//public class OrderResourceIntTest {
//
//    private static final String DEFAULT_BIGORDER = "AAAAAAAAAA";
//    private static final String UPDATED_BIGORDER = "BBBBBBBBBB";
//
//    private static final String DEFAULT_ORDERNUM = "AAAAAAAAAA";
//    private static final String UPDATED_ORDERNUM = "BBBBBBBBBB";
//
//    private static final String DEFAULT_STATE = "AAAAAAAAAA";
//    private static final String UPDATED_STATE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_USERID = "AAAAAAAAAA";
//    private static final String UPDATED_USERID = "BBBBBBBBBB";
//
//    private static final String DEFAULT_COMMODITYID = "AAAAAAAAAA";
//    private static final String UPDATED_COMMODITYID = "BBBBBBBBBB";
//
//    private static final String DEFAULT_SPECIFICATIONSID = "AAAAAAAAAA";
//    private static final String UPDATED_SPECIFICATIONSID = "BBBBBBBBBB";
//
//    private static final String DEFAULT_CONSIGNEE = "AAAAAAAAAA";
//    private static final String UPDATED_CONSIGNEE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
//    private static final String UPDATED_MOBILE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
//    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";
//
//    private static final String DEFAULT_NUM = "AAAAAAAAAA";
//    private static final String UPDATED_NUM = "BBBBBBBBBB";
//
//    private static final String DEFAULT_PAYMETHOD = "AAAAAAAAAA";
//    private static final String UPDATED_PAYMETHOD = "BBBBBBBBBB";
//
//    private static final String DEFAULT_PAYRESULT = "AAAAAAAAAA";
//    private static final String UPDATED_PAYRESULT = "BBBBBBBBBB";
//
//    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
//    private static final String UPDATED_CREATOR = "BBBBBBBBBB";
//
//    private static final String DEFAULT_CREATEDATE = "AAAAAAAAAA";
//    private static final String UPDATED_CREATEDATE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_MODIFIER = "AAAAAAAAAA";
//    private static final String UPDATED_MODIFIER = "BBBBBBBBBB";
//
//    private static final String DEFAULT_MODIFIERDATE = "AAAAAAAAAA";
//    private static final String UPDATED_MODIFIERDATE = "BBBBBBBBBB";
//
//    private static final Long DEFAULT_MODIFIERNUM = 1L;
//    private static final Long UPDATED_MODIFIERNUM = 2L;
//
//    private static final Boolean DEFAULT_LOGICDELETE = false;
//    private static final Boolean UPDATED_LOGICDELETE = true;
//
//    private static final String DEFAULT_OTHER = "AAAAAAAAAA";
//    private static final String UPDATED_OTHER = "BBBBBBBBBB";
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Autowired
//    private OrderMapper orderMapper;
//
//    @Autowired
//    private OrderService orderService;
//
//    @Autowired
//    private MappingJackson2HttpMessageConverter jacksonMessageConverter;
//
//    @Autowired
//    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;
//
//    @Autowired
//    private ExceptionTranslator exceptionTranslator;
//
//    @Autowired
//    private EntityManager em;
//
//    @Autowired
//    private Validator validator;
//
//    private MockMvc restOrderMockMvc;
//
//    private Order order;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        final OrderResource orderResource = new OrderResource(orderService);
//        this.restOrderMockMvc = MockMvcBuilders.standaloneSetup(orderResource)
//            .setCustomArgumentResolvers(pageableArgumentResolver)
//            .setControllerAdvice(exceptionTranslator)
//            .setConversionService(createFormattingConversionService())
//            .setMessageConverters(jacksonMessageConverter)
//            .setValidator(validator).build();
//    }
//
//    /**
//     * Create an entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static Order createEntity(EntityManager em) {
//        Order order = new Order()
//            .bigorder(DEFAULT_BIGORDER)
//            .ordernum(DEFAULT_ORDERNUM)
//            .state(DEFAULT_STATE)
//            .userid(DEFAULT_USERID)
//            .commodityid(DEFAULT_COMMODITYID)
//            .specificationsid(DEFAULT_SPECIFICATIONSID)
//            .consignee(DEFAULT_CONSIGNEE)
//            .mobile(DEFAULT_MOBILE)
//            .address(DEFAULT_ADDRESS)
//            .num(DEFAULT_NUM)
//            .paymethod(DEFAULT_PAYMETHOD)
//            .payresult(DEFAULT_PAYRESULT)
//            .creator(DEFAULT_CREATOR)
//            .createdate(DEFAULT_CREATEDATE)
//            .modifier(DEFAULT_MODIFIER)
//            .modifierdate(DEFAULT_MODIFIERDATE)
//            .modifiernum(DEFAULT_MODIFIERNUM)
//            .logicdelete(DEFAULT_LOGICDELETE)
//            .other(DEFAULT_OTHER);
//        return order;
//    }
//
//    @Before
//    public void initTest() {
//        order = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    public void createOrder() throws Exception {
//        int databaseSizeBeforeCreate = orderRepository.findAll().size();
//
//        // Create the Order
//        OrderDTO orderDTO = orderMapper.toDto(order);
//        restOrderMockMvc.perform(post("/api/orders")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
//            .andExpect(status().isCreated());
//
//        // Validate the Order in the database
//        List<Order> orderList = orderRepository.findAll();
//        assertThat(orderList).hasSize(databaseSizeBeforeCreate + 1);
//        Order testOrder = orderList.get(orderList.size() - 1);
//        assertThat(testOrder.getBigorder()).isEqualTo(DEFAULT_BIGORDER);
//        assertThat(testOrder.getOrdernum()).isEqualTo(DEFAULT_ORDERNUM);
//        assertThat(testOrder.getState()).isEqualTo(DEFAULT_STATE);
//        assertThat(testOrder.getUserid()).isEqualTo(DEFAULT_USERID);
//        assertThat(testOrder.getCommodityid()).isEqualTo(DEFAULT_COMMODITYID);
//        assertThat(testOrder.getSpecificationsid()).isEqualTo(DEFAULT_SPECIFICATIONSID);
//        assertThat(testOrder.getConsignee()).isEqualTo(DEFAULT_CONSIGNEE);
//        assertThat(testOrder.getMobile()).isEqualTo(DEFAULT_MOBILE);
//        assertThat(testOrder.getAddress()).isEqualTo(DEFAULT_ADDRESS);
//        assertThat(testOrder.getNum()).isEqualTo(DEFAULT_NUM);
//        assertThat(testOrder.getPaymethod()).isEqualTo(DEFAULT_PAYMETHOD);
//        assertThat(testOrder.getPayresult()).isEqualTo(DEFAULT_PAYRESULT);
//        assertThat(testOrder.getCreator()).isEqualTo(DEFAULT_CREATOR);
//        assertThat(testOrder.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
//        assertThat(testOrder.getModifier()).isEqualTo(DEFAULT_MODIFIER);
//        assertThat(testOrder.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
//        assertThat(testOrder.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
//        assertThat(testOrder.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
//        assertThat(testOrder.getOther()).isEqualTo(DEFAULT_OTHER);
//    }
//
//    @Test
//    @Transactional
//    public void createOrderWithExistingId() throws Exception {
//        int databaseSizeBeforeCreate = orderRepository.findAll().size();
//
//        // Create the Order with an existing ID
//        order.setId(1L);
//        OrderDTO orderDTO = orderMapper.toDto(order);
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restOrderMockMvc.perform(post("/api/orders")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Order in the database
//        List<Order> orderList = orderRepository.findAll();
//        assertThat(orderList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    public void getAllOrders() throws Exception {
//        // Initialize the database
//        orderRepository.saveAndFlush(order);
//
//        // Get all the orderList
//        restOrderMockMvc.perform(get("/api/orders?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(order.getId().intValue())))
//            .andExpect(jsonPath("$.[*].bigorder").value(hasItem(DEFAULT_BIGORDER.toString())))
//            .andExpect(jsonPath("$.[*].ordernum").value(hasItem(DEFAULT_ORDERNUM.toString())))
//            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
//            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.toString())))
//            .andExpect(jsonPath("$.[*].commodityid").value(hasItem(DEFAULT_COMMODITYID.toString())))
//            .andExpect(jsonPath("$.[*].specificationsid").value(hasItem(DEFAULT_SPECIFICATIONSID.toString())))
//            .andExpect(jsonPath("$.[*].consignee").value(hasItem(DEFAULT_CONSIGNEE.toString())))
//            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE.toString())))
//            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
//            .andExpect(jsonPath("$.[*].num").value(hasItem(DEFAULT_NUM.toString())))
//            .andExpect(jsonPath("$.[*].paymethod").value(hasItem(DEFAULT_PAYMETHOD.toString())))
//            .andExpect(jsonPath("$.[*].payresult").value(hasItem(DEFAULT_PAYRESULT.toString())))
//            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
//            .andExpect(jsonPath("$.[*].createdate").value(hasItem(DEFAULT_CREATEDATE.toString())))
//            .andExpect(jsonPath("$.[*].modifier").value(hasItem(DEFAULT_MODIFIER.toString())))
//            .andExpect(jsonPath("$.[*].modifierdate").value(hasItem(DEFAULT_MODIFIERDATE.toString())))
//            .andExpect(jsonPath("$.[*].modifiernum").value(hasItem(DEFAULT_MODIFIERNUM.intValue())))
//            .andExpect(jsonPath("$.[*].logicdelete").value(hasItem(DEFAULT_LOGICDELETE.booleanValue())))
//            .andExpect(jsonPath("$.[*].other").value(hasItem(DEFAULT_OTHER.toString())));
//    }
//
//    @Test
//    @Transactional
//    public void getOrder() throws Exception {
//        // Initialize the database
//        orderRepository.saveAndFlush(order);
//
//        // Get the order
//        restOrderMockMvc.perform(get("/api/orders/{id}", order.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.id").value(order.getId().intValue()))
//            .andExpect(jsonPath("$.bigorder").value(DEFAULT_BIGORDER.toString()))
//            .andExpect(jsonPath("$.ordernum").value(DEFAULT_ORDERNUM.toString()))
//            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
//            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.toString()))
//            .andExpect(jsonPath("$.commodityid").value(DEFAULT_COMMODITYID.toString()))
//            .andExpect(jsonPath("$.specificationsid").value(DEFAULT_SPECIFICATIONSID.toString()))
//            .andExpect(jsonPath("$.consignee").value(DEFAULT_CONSIGNEE.toString()))
//            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE.toString()))
//            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
//            .andExpect(jsonPath("$.num").value(DEFAULT_NUM.toString()))
//            .andExpect(jsonPath("$.paymethod").value(DEFAULT_PAYMETHOD.toString()))
//            .andExpect(jsonPath("$.payresult").value(DEFAULT_PAYRESULT.toString()))
//            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
//            .andExpect(jsonPath("$.createdate").value(DEFAULT_CREATEDATE.toString()))
//            .andExpect(jsonPath("$.modifier").value(DEFAULT_MODIFIER.toString()))
//            .andExpect(jsonPath("$.modifierdate").value(DEFAULT_MODIFIERDATE.toString()))
//            .andExpect(jsonPath("$.modifiernum").value(DEFAULT_MODIFIERNUM.intValue()))
//            .andExpect(jsonPath("$.logicdelete").value(DEFAULT_LOGICDELETE.booleanValue()))
//            .andExpect(jsonPath("$.other").value(DEFAULT_OTHER.toString()));
//    }
//
//    @Test
//    @Transactional
//    public void getNonExistingOrder() throws Exception {
//        // Get the order
//        restOrderMockMvc.perform(get("/api/orders/{id}", Long.MAX_VALUE))
//            .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    public void updateOrder() throws Exception {
//        // Initialize the database
//        orderRepository.saveAndFlush(order);
//
//        int databaseSizeBeforeUpdate = orderRepository.findAll().size();
//
//        // Update the order
//        Order updatedOrder = orderRepository.findById(order.getId()).get();
//        // Disconnect from session so that the updates on updatedOrder are not directly saved in db
//        em.detach(updatedOrder);
//        updatedOrder
//            .bigorder(UPDATED_BIGORDER)
//            .ordernum(UPDATED_ORDERNUM)
//            .state(UPDATED_STATE)
//            .userid(UPDATED_USERID)
//            .commodityid(UPDATED_COMMODITYID)
//            .specificationsid(UPDATED_SPECIFICATIONSID)
//            .consignee(UPDATED_CONSIGNEE)
//            .mobile(UPDATED_MOBILE)
//            .address(UPDATED_ADDRESS)
//            .num(UPDATED_NUM)
//            .paymethod(UPDATED_PAYMETHOD)
//            .payresult(UPDATED_PAYRESULT)
//            .creator(UPDATED_CREATOR)
//            .createdate(UPDATED_CREATEDATE)
//            .modifier(UPDATED_MODIFIER)
//            .modifierdate(UPDATED_MODIFIERDATE)
//            .modifiernum(UPDATED_MODIFIERNUM)
//            .logicdelete(UPDATED_LOGICDELETE)
//            .other(UPDATED_OTHER);
//        OrderDTO orderDTO = orderMapper.toDto(updatedOrder);
//
//        restOrderMockMvc.perform(put("/api/orders")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
//            .andExpect(status().isOk());
//
//        // Validate the Order in the database
//        List<Order> orderList = orderRepository.findAll();
//        assertThat(orderList).hasSize(databaseSizeBeforeUpdate);
//        Order testOrder = orderList.get(orderList.size() - 1);
//        assertThat(testOrder.getBigorder()).isEqualTo(UPDATED_BIGORDER);
//        assertThat(testOrder.getOrdernum()).isEqualTo(UPDATED_ORDERNUM);
//        assertThat(testOrder.getState()).isEqualTo(UPDATED_STATE);
//        assertThat(testOrder.getUserid()).isEqualTo(UPDATED_USERID);
//        assertThat(testOrder.getCommodityid()).isEqualTo(UPDATED_COMMODITYID);
//        assertThat(testOrder.getSpecificationsid()).isEqualTo(UPDATED_SPECIFICATIONSID);
//        assertThat(testOrder.getConsignee()).isEqualTo(UPDATED_CONSIGNEE);
//        assertThat(testOrder.getMobile()).isEqualTo(UPDATED_MOBILE);
//        assertThat(testOrder.getAddress()).isEqualTo(UPDATED_ADDRESS);
//        assertThat(testOrder.getNum()).isEqualTo(UPDATED_NUM);
//        assertThat(testOrder.getPaymethod()).isEqualTo(UPDATED_PAYMETHOD);
//        assertThat(testOrder.getPayresult()).isEqualTo(UPDATED_PAYRESULT);
//        assertThat(testOrder.getCreator()).isEqualTo(UPDATED_CREATOR);
//        assertThat(testOrder.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
//        assertThat(testOrder.getModifier()).isEqualTo(UPDATED_MODIFIER);
//        assertThat(testOrder.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
//        assertThat(testOrder.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
//        assertThat(testOrder.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
//        assertThat(testOrder.getOther()).isEqualTo(UPDATED_OTHER);
//    }
//
//    @Test
//    @Transactional
//    public void updateNonExistingOrder() throws Exception {
//        int databaseSizeBeforeUpdate = orderRepository.findAll().size();
//
//        // Create the Order
//        OrderDTO orderDTO = orderMapper.toDto(order);
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restOrderMockMvc.perform(put("/api/orders")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Order in the database
//        List<Order> orderList = orderRepository.findAll();
//        assertThat(orderList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    public void deleteOrder() throws Exception {
//        // Initialize the database
//        orderRepository.saveAndFlush(order);
//
//        int databaseSizeBeforeDelete = orderRepository.findAll().size();
//
//        // Delete the order
//        restOrderMockMvc.perform(delete("/api/orders/{id}", order.getId())
//            .accept(TestUtil.APPLICATION_JSON_UTF8))
//            .andExpect(status().isOk());
//
//        // Validate the database is empty
//        List<Order> orderList = orderRepository.findAll();
//        assertThat(orderList).hasSize(databaseSizeBeforeDelete - 1);
//    }
//
//    @Test
//    @Transactional
//    public void equalsVerifier() throws Exception {
//        TestUtil.equalsVerifier(Order.class);
//        Order order1 = new Order();
//        order1.setId(1L);
//        Order order2 = new Order();
//        order2.setId(order1.getId());
//        assertThat(order1).isEqualTo(order2);
//        order2.setId(2L);
//        assertThat(order1).isNotEqualTo(order2);
//        order1.setId(null);
//        assertThat(order1).isNotEqualTo(order2);
//    }
//
//    @Test
//    @Transactional
//    public void dtoEqualsVerifier() throws Exception {
//        TestUtil.equalsVerifier(OrderDTO.class);
//        OrderDTO orderDTO1 = new OrderDTO();
//        orderDTO1.setId(1L);
//        OrderDTO orderDTO2 = new OrderDTO();
//        assertThat(orderDTO1).isNotEqualTo(orderDTO2);
//        orderDTO2.setId(orderDTO1.getId());
//        assertThat(orderDTO1).isEqualTo(orderDTO2);
//        orderDTO2.setId(2L);
//        assertThat(orderDTO1).isNotEqualTo(orderDTO2);
//        orderDTO1.setId(null);
//        assertThat(orderDTO1).isNotEqualTo(orderDTO2);
//    }
//
//    @Test
//    @Transactional
//    public void testEntityFromId() {
//        assertThat(orderMapper.fromId(42L).getId()).isEqualTo(42);
//        assertThat(orderMapper.fromId(null)).isNull();
//    }
//}
