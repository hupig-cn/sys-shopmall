package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.ClassificationAttr;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ClassificationAttr entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClassificationAttrRepository extends JpaRepository<ClassificationAttr, Long> {

}
