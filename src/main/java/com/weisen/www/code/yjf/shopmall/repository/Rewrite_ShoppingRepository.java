package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.Shopping;
import com.weisen.www.code.yjf.shopmall.domain.Specifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface Rewrite_ShoppingRepository extends JpaRepository<Shopping, Long> {

    // 获取用户购物车列表
    List<Shopping> findAllByUserid(Long userid);

    // 商品是否之前就存在购物车中
    Long countByUseridAndSpecificationsid(String userid,String specificationsid);

    // 查询已存在的商品
    Shopping findByUseridAndSpecificationsid(String userid,String specificationsid);

    @Query(value = "select * from shopping where specificationsid = ?1 and userid = ?2",nativeQuery = true)
    Shopping findBySpecificationsIdAndUserId(Long goodsId, Long userId);

    //这个方法的num用途是特殊的,使用的时候请注意
    @Query(value = "select spe.id as id ,s.num as num , spe.commodityid as commodityid from shopping s JOIN specifications spe ON s.specificationsid = spe.id JOIN Commodity c ON spe.commodityid = c.id where s.in in ?1",nativeQuery = true)
    List<Specifications> findInfoByIds(Long[] ids);

    @Query(value = "select spe.price as price,car.num as number,spe.specifications as attr,spe.num as number,c.name as name , spe.fileid as fileid from shopping as car JOIN specifications spe ON car.specificationsid = spe.id JOIN commodity c ON c.id = spe.commodityid where car.userid = :userId AND car.id in :ids",nativeQuery = true)
    List<Map<String,String>> getOrderInfoByUserIdAndId(@Param("userId") Long userId,@Param("ids") Long[] ids);
}
