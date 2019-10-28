package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.Commodity;
import com.weisen.www.code.yjf.shopmall.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * Spring Data  repository for the Commodity entity.
 */
@Repository
public interface Rewrite_CommodityRepository extends JpaRepository<Commodity, Long> {

    // 根据销量查询商品
    @Query(value = "select id,name,brandid,classificationid,commoditystate,postage,salevalue,weight,creator,createdate,modifier,modifierdate" +
        ",modifiernum,logicdelete,other from commodity order by salevalue Desc limit?1,?2" , nativeQuery = true)
    List<Commodity> getAllBySalevalue(int startNum,int pageSize);

    // 根据商品名称查询商品列表
    @Query(value = "select id,name,brandid,classificationid,commoditystate,postage,salevalue,weight,creator,createdate,modifier,modifierdate" +
        ",modifiernum,logicdelete,other from commodity where name like concat('%',?1,'%') limit?2,?3" , nativeQuery = true)
    List<Commodity> getAllByName(String name,int fromIndex,int pageSize);

    // 根据时间查询商品
    @Query(value = "select id,name,brandid,classificationid,commoditystate,postage,salevalue,weight,creator,createdate,modifier,modifierdate" +
        ",modifiernum,logicdelete,other from commodity order by createdate desc limit?1,?2" , nativeQuery = true)
    List<Commodity> getAllByTime(int fromIndex,int pageSize);


    @Query(value = "SELECT c.id as commodityid,s.id as id,s.model as model,s.specifications as specifications ,s.price as price,c.salevalue as salevalue, i.content as content,s.discount as discount from commodity c LEFT JOIN introduce i ON c.id = i.commodityid LEFT JOIN specifications s ON i.commodityid = s.commodityid where s.id = ?1 and c.commoditystate = 1 ORDER BY i.other , c.createdate DESC", nativeQuery = true)
    Product findAllProductById(Long id);

    @Query(value = "SELECT c.id as commodityid,s.id as id,s.model as model,s.specifications as specifications ,s.price as price,c.salevalue as salevalue, i.content as content,s.discount as discount from commodity c LEFT JOIN introduce i ON c.id = i.commodityid LEFT JOIN specifications s ON i.commodityid = s.commodityid where i.content like CONCAT('%',?1,'%') and c.commoditystate = 1 ORDER BY i.other , c.createdate DESC LIMIT ?2,?3", nativeQuery = true)
    List<Product> findAllProductByName(String keyWord, Integer pageNum, Integer pageSize);

    @Query(value = "SELECT c.id as commodityid,c.name as name ,s.fileid as fileid,c.postage as postage,s.id as id,s.model as model,s.specifications as specifications ,s.price as price,c.salevalue as salevalue,s.discount as discount from commodity c LEFT JOIN specifications s ON s.commodityid = c.id ORDER BY s.other DESC, c.createdate DESC LIMIT ?1,?2", nativeQuery = true)
    List<Map<String,Object>> findAllProduct(int pageNum, Integer pageSize);

    @Query(value = "SELECT count(*) from commodity c LEFT JOIN introduce i ON c.id = i.commodityid LEFT JOIN specifications s ON i.commodityid = s.commodityid where i.content like CONCAT('%',?1,'%') and c.commoditystate = 1 ", nativeQuery = true)
    Integer findAllProductByNameCount(String name);

    @Query(value = "SELECT count(*) from commodity c LEFT JOIN introduce i ON c.id = i.commodityid LEFT JOIN specifications s ON i.commodityid = s.commodityid where  and c.commoditystate = 1 ", nativeQuery = true)
    Integer findAllProductCount();

    @Query(value = "select c.postage as postage,c.salevalue as salevalue ,c.name as name , spe.price as price ,spe.fileid as fileid,spe.model as model ,spe.num as num " +
        ", spe.integral as integral,spe.specifications as json from commodity c LEFT JOIN specifications spe on c.id = spe.commodityid where spe.id = ?1",nativeQuery = true)
    Map<String,Object> findProdcutDetailBySpecificationsId(Long speId);
    
    @Query(value = "SELECT c.id as commodityid,c.name as name ,s.fileid as fileid,c.postage as postage,s.id as id,s.model as model,s.specifications as specifications ,s.price as price,c.salevalue as salevalue,s.discount as discount from commodity c LEFT JOIN specifications s ON s.commodityid = c.id where specifications like %?3% ORDER BY s.other DESC, c.createdate DESC LIMIT ?1,?2", nativeQuery = true)
    List<Map<String,Object>> findAllProductByconent(int pageNum, Integer pageSize,String content);
}
