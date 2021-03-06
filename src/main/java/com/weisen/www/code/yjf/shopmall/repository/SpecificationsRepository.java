package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.Specifications;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Specifications entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SpecificationsRepository extends JpaRepository<Specifications, Long> {

}
