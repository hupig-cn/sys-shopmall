package com.weisen.www.code.yjf.shopmall.web.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.shopmall.service.OrderService;

/**
 * REST controller for managing Order.
 */
@RestController
@RequestMapping("/api")
public class OrderResource {

    private final Logger log = LoggerFactory.getLogger(OrderResource.class);

    private static final String ENTITY_NAME = "shopmallOrder";

    private final OrderService orderService;

    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

//    /**
//     * POST  /orders : Create a new order.
//     *
//     * @param orderDTO the orderDTO to create
//     * @return the ResponseEntity with status 201 (Created) and with body the new orderDTO, or with status 400 (Bad Request) if the order has already an ID
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PostMapping("/orders")
//    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) throws URISyntaxException {
//        log.debug("REST request to save Order : {}", orderDTO);
//        if (orderDTO.getId() != null) {
//            throw new BadRequestAlertException("A new order cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        OrderDTO result = orderService.save(orderDTO);
//        return ResponseEntity.created(new URI("/api/orders/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }
//
//    /**
//     * PUT  /orders : Updates an existing order.
//     *
//     * @param orderDTO the orderDTO to update
//     * @return the ResponseEntity with status 200 (OK) and with body the updated orderDTO,
//     * or with status 400 (Bad Request) if the orderDTO is not valid,
//     * or with status 500 (Internal Server Error) if the orderDTO couldn't be updated
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PutMapping("/orders")
//    public ResponseEntity<OrderDTO> updateOrder(@RequestBody OrderDTO orderDTO) throws URISyntaxException {
//        log.debug("REST request to update Order : {}", orderDTO);
//        if (orderDTO.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        OrderDTO result = orderService.save(orderDTO);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderDTO.getId().toString()))
//            .body(result);
//    }
//
//    /**
//     * GET  /orders : get all the orders.
//     *
//     * @param pageable the pagination information
//     * @return the ResponseEntity with status 200 (OK) and the list of orders in body
//     */
//    @GetMapping("/orders")
//    public ResponseEntity<List<OrderDTO>> getAllOrders(Pageable pageable) {
//        log.debug("REST request to get a page of Orders");
//        Page<OrderDTO> page = orderService.findAll(pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/orders");
//        return ResponseEntity.ok().headers(headers).body(page.getContent());
//    }
//
//    /**
//     * GET  /orders/:id : get the "id" order.
//     *
//     * @param id the id of the orderDTO to retrieve
//     * @return the ResponseEntity with status 200 (OK) and with body the orderDTO, or with status 404 (Not Found)
//     */
//    @GetMapping("/orders/{id}")
//    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
//        log.debug("REST request to get Order : {}", id);
//        Optional<OrderDTO> orderDTO = orderService.findOne(id);
//        return ResponseUtil.wrapOrNotFound(orderDTO);
//    }
//
//    /**
//     * DELETE  /orders/:id : delete the "id" order.
//     *
//     * @param id the id of the orderDTO to delete
//     * @return the ResponseEntity with status 200 (OK)
//     */
//    @DeleteMapping("/orders/{id}")
//    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
//        log.debug("REST request to delete Order : {}", id);
//        orderService.delete(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
//    }
}
