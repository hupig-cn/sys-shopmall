package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Rewrite_ShoppingRepository extends JpaRepository<Shopping, Long> {

    // 获取用户购物车列表
    List<Shopping> findAllByUserid(String userid);

    // 商品是否之前就存在购物车中
    Long countByUseridAndSpecificationsid(String userid,String specificationsid);

    // 查询已存在的商品
    Shopping findByUseridAndSpecificationsid(String userid,String specificationsid);
}
