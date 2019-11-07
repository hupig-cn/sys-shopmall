package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.V2ShoppingCart;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the V2ShoppingCart entity.
 */
@SuppressWarnings("unused")
@Repository
public interface V2ShoppingCartRepository extends JpaRepository<V2ShoppingCart, Long> {

}
