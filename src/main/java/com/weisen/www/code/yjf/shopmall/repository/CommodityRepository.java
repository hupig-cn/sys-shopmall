package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.Commodity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Commodity entity.
 */
@Repository
public interface CommodityRepository extends JpaRepository<Commodity, Long> {

}
