package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.Specifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface Rewrite_SpecificationsRepository extends JpaRepository<Specifications, Long> {

    //根据商品查询规格
    List<Specifications> findAllByCommodityid(String commodityid);
    @Query(value = "select spe.price as price , spe.specifications as attr, c.name as name from commodity c JOIN specifications spe ON c.id = spe.commodityid where spe.id = ?1",nativeQuery = true)
    Map<String,Object> findByIdToMap(Long id);
}
