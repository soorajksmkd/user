package com.infosys.project.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.project.user.entity.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, Long>{

	Optional<Buyer> findByEmail(String email);

	Optional<Buyer> findByPhoneNumber(String phoneNumber);

}
