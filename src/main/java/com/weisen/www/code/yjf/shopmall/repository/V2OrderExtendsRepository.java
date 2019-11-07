package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.V2OrderExtends;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the V2OrderExtends entity.
 */
@SuppressWarnings("unused")
@Repository
public interface V2OrderExtendsRepository extends JpaRepository<V2OrderExtends, Long> {

}
