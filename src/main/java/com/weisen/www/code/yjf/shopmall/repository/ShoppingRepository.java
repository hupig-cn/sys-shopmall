package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.Shopping;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Shopping entity.
 */
@Repository
public interface ShoppingRepository extends JpaRepository<Shopping, Long> {

}
