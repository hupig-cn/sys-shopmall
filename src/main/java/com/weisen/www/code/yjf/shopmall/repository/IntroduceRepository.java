package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.Introduce;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Introduce entity.
 */
@Repository
public interface IntroduceRepository extends JpaRepository<Introduce, Long> {

}
