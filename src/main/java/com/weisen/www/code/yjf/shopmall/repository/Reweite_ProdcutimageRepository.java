package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.Prodcutimage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Reweite_ProdcutimageRepository extends JpaRepository<Prodcutimage, Long> {

    // 根据商品规格id查询图片
    @Query(value ="select * from prodcutimage where specificationsid = ?1",nativeQuery = true)
    List<Prodcutimage> findAllBySpecificationsid(Long specificationsid);
}
