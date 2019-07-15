package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.Prodcutimage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Reweite_ProdcutimageRepository extends JpaRepository<Prodcutimage, Long> {

    // 根据商品规格id查询图片
    List<Prodcutimage> findAllBySpecificationsid(String specificationsid);
}
