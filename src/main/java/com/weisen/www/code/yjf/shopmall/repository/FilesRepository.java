package com.weisen.www.code.yjf.shopmall.repository;

import com.weisen.www.code.yjf.shopmall.domain.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Files entity.
 */
@Repository
public interface FilesRepository extends JpaRepository<Files, Long> {

	//通过id找到该记录
	@Query(value = "select * from basic.files where id = ?1", nativeQuery = true)
	Files findByIds(Long id);

}
