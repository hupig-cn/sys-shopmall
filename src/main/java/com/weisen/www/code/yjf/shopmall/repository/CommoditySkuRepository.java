package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.CommoditySku;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CommoditySku entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommoditySkuRepository extends JpaRepository<CommoditySku, Long> {

}
