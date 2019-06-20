package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.Order;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Order entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
