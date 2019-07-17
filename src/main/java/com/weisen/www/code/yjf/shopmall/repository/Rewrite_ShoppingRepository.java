package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Rewrite_ShoppingRepository extends JpaRepository<Shopping, Long> {

    // 获取用户购物车列表
    List<Shopping> findAllByUserid(Long userid);
    @Query(value = "select * from shopping where specificationsid = ?1 and userid = ?2",nativeQuery = true)
    Shopping findBySpecificationsIdAndUserId(Long goodsId, Long userId);
}
