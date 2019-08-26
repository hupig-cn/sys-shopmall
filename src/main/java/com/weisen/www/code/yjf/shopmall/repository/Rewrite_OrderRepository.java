package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Order entity.
 */
@Repository
public interface Rewrite_OrderRepository extends JpaRepository<Order, Long> {

    //查询用户所有订单
    List<Order> findAllByUserid(String userid);

    //查询时间内的所有订单数量
    @Query(value = "select conut(*) from jhi_order where unix_timestamp(createdate) > unix_timestamp(?1) and " +
        "unix_timestamp(createdate) < unix_timestamp(?2) and state = ?3",nativeQuery = true)
    int getAllOrderCount(String startTime,String endTime,String state);

}
