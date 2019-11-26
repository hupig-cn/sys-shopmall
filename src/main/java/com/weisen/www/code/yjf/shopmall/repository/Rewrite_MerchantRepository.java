package com.weisen.www.code.yjf.shopmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.weisen.www.code.yjf.shopmall.domain.Merchant;

@Repository
public interface Rewrite_MerchantRepository extends JpaRepository<Merchant, Long> {

	// 判断用户是否是商家
	@Query(value = "select * from merchant.merchant where userid = ?1 and state = 2", nativeQuery = true)
	Merchant findByUseridAndState(String userId);

}
