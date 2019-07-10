package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.Specifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Rewrite_SpecificationsRepository extends JpaRepository<Specifications, Long> {

    //根据商品查询规格
    List<Specifications> findAllByCommodityid(String commodityid);
}
