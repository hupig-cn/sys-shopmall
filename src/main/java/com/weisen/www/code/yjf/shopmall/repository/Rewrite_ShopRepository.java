package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/29 11:32
 */
@Repository
public interface Rewrite_ShopRepository extends JpaRepository<Shopping, Long> {

    List<Shopping> findShoppingByUseridOrderByCreatedate(Long userid);

    List<Shopping> findShoppingByUserid(Long userid);

    Shopping findShoppingByCommodityid(String Commodityid);

    Shopping findShoppingById(Long id);

    int deleteShoppingByUserid(Long userid);
}
