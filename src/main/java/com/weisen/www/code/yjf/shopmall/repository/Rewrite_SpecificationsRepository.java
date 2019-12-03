package com.weisen.www.code.yjf.shopmall.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.weisen.www.code.yjf.shopmall.domain.Specifications;

@Repository
public interface Rewrite_SpecificationsRepository extends JpaRepository<Specifications, Long> {

	// 分页查询商品列表
	@Query(value = "select * from specifications where (?1 is null or id = ?1) order by id asc limit ?2,?3", nativeQuery = true)
	List<Specifications> getSpecificationsList(String goodsid, int pageNum, int pageSize);

	// 分页查询商品列表
	@Query(value = "select count(*) from specifications where (?1 is null or id = ?1)", nativeQuery = true)
	int getSpecificationsListCount(String goodsid);

	// 根据商品查询规格
	List<Specifications> findAllByCommodityid(String commodityid);

	@Query(value = "select spe.price as price , spe.specifications as attr, c.name as name , spe.fileid as fileid from commodity c JOIN specifications spe ON c.id = spe.commodityid where spe.id = ?1", nativeQuery = true)
	Map<String, Object> findByIdToMap(Long id);

	@Query(value = "SELECT c.name as name , c.postage as postage , o.num as num , spe.price as price ,spe.model as model , spe.fileid as fileid  from jhi_order o JOIN specifications spe ON  o.specificationsid = spe.id JOIN commodity c ON c.id = spe.id where o.bigorder = ?1", nativeQuery = true)
	List<Map<String, Object>> findOrderInfoByOrderId(String ordreId);

	@Query(value = "select * from specifications where id = ?1", nativeQuery = true)
	Optional<Specifications> findAmoutById(Long id);

	// 根据商品ID查找商品
	Specifications findByCommodityid(String commodityid);

	// 查询商家所有商品
	@Query(value = "select * from specifications where creator = ?1 ORDER BY price desc LIMIT ?2,?3", nativeQuery = true)
	List<Specifications> findBySpecifications(String userId, Integer pageNum, Integer pageSize);


    @Query(value = "select * from specifications where commodityid = ?1 ",nativeQuery = true)
    Specifications findSpecificationsByCommodityid(String commodityid);
    //hui
    @Query(value = "select * from specifications Order by other Desc Limit ?1,?2",nativeQuery = true)
    List<Specifications> findSpecificationsByOrdrerBys(Integer pageNum,Integer pageSize);
    //hui
    @Query(value = "select commodityid from specifications where model like ?1 ",nativeQuery = true)
    List<Integer> findSpecificationsByModels(String name);
    //hui
    @Query(value = "select commodityid from specifications where specifications like ?1 ",nativeQuery = true)
    List<Integer> findSpecificationsBySpecificationss(String name);
    //hui
    @Query(value = "SELECT * FROM specifications ORDER BY CONVERT(price,DECIMAL(10,2)) Limit ?1,?2",nativeQuery = true)
    List<Specifications> findSpecificationsByOrdrerByp(Integer pageNum,Integer pageSize);
    //hui
    @Query(value = "SELECT * FROM specifications ORDER BY createdate DESC LIMIT ?1,?2",nativeQuery = true)
    List<Specifications> findSpecificationsByOrdrerByc(Integer pageNum,Integer pageSize);
    //hui
    @Query(value = "SELECT * FROM specifications ORDER BY sales DESC LIMIT ?1,?2",nativeQuery = true)
    List<Specifications> findSpecificationsByOrdrerBysc(Integer pageNum,Integer pageSize);
}
