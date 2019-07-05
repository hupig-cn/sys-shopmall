package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Commodity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Rewrite_CommodityRepository extends JpaRepository<Commodity, Long> {

}
