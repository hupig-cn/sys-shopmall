package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.Classification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Rewrite_ClassificationRepository extends JpaRepository<Classification, Long> {

	// 查询商品所有分类 LuoJinShui
	List<Classification> findByPid(Long pid);

	// 查询是否有该分类
	Classification findClassificationByIdAndPid(Long brandid, Long pid);

	// hui
	@Query(value = "select id from classification where name like ?1 ", nativeQuery = true)
	List<Integer> findClassificationBynameLike(String name);

	// hui
	@Query(value = "select id from classification where type = ?1 ", nativeQuery = true)
	Long findClassificationByType(Integer type);

}
