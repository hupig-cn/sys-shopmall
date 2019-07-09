package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Commodity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Rewrite_CommodityRepository extends JpaRepository<Commodity, Long> {

    // 根据销量查询商品
    @Query(value = "select id,name,brandid,classificationid,commoditystate,postage,salevalue,weight,creator,createdate,modifier,modifierdate" +
        ",modifiernum,logicdelete,other from commodity order by salevalue Desc limit?1,?2" , nativeQuery = true)
    List<Commodity> getAllBySalevalue(int startNum,int pageSize);

    // 根据商品名称查询商品列表
    @Query(value = "select id,name,brandid,classificationid,commoditystate,postage,salevalue,weight,creator,createdate,modifier,modifierdate" +
        ",modifiernum,logicdelete,other from commodity where name like '%?1%' limit?3,?4" , nativeQuery = true)
    List<Commodity> getAllByName(String name,int fromIndex,int pageSize);

}
