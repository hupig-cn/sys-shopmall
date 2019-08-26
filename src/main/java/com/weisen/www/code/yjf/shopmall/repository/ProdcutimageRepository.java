package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.Prodcutimage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Prodcutimage entity.
 */
@Repository
public interface ProdcutimageRepository extends JpaRepository<Prodcutimage, Long> {

}
