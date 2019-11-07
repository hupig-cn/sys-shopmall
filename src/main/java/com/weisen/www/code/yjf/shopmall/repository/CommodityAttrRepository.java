package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.CommodityAttr;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CommodityAttr entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommodityAttrRepository extends JpaRepository<CommodityAttr, Long> {

}
