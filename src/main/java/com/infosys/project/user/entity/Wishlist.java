package com.infosys.project.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "wishlist")
@IdClass(WishlistId.class)
public class Wishlist {

	@Id
	@Column(name = "buyerid", unique = true, nullable = false, length = 11)
	Long buyerId;
	
	@Id
	@Column(name = "prodid", unique = true, nullable = false, length = 11)
	Long prodId;

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	
	
}
