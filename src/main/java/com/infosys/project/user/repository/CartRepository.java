package com.infosys.project.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.project.user.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

	Optional<Cart> findByBuyerIdAndProdId(Long buyerId, Long prodId);

	void deleteByBuyerIdAndProdId(Long buyerId, Long prodId);

}
