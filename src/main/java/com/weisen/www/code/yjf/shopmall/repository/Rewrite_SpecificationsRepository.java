package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.Specifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface Rewrite_SpecificationsRepository extends JpaRepository<Specifications, Long> {

    //根据商品查询规格
    List<Specifications> findAllByCommodityid(String commodityid);
    @Query(value = "select spe.price as price , spe.specifications as attr, c.name as name , spe.fileid as fileid from commodity c JOIN specifications spe ON c.id = spe.commodityid where spe.id = ?1",nativeQuery = true)
    Map<String,Object> findByIdToMap(Long id);
    @Query(value = "SELECT c.name as name , c.postage as postage , o.num as num , spe.price as price ,spe.model as model , spe.fileid as fileid  from jhi_order o JOIN specifications spe ON  o.specificationsid = spe.id JOIN commodity c ON c.id = spe.id where o.bigorder = ?1",nativeQuery = true)
    List<Map<String,Object>> findOrderInfoByOrderId(String ordreId);
    @Query(value = "select * from specifications where id = ?1",nativeQuery = true)
    Optional<Specifications> findAmoutById(Long id);
}
