package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.Classification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Classification entity.
 */
@Repository
public interface Rewrite_ClassificationRepository extends JpaRepository<Classification, Long> {

}
