package com.infosys.project.user.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CartId implements Serializable{
	
	Long buyerId;
	Long prodId;
	
	public CartId() {
		
	}
	
	public CartId(Long buyerId, Long prodId) {
		
		this.buyerId = buyerId;
		this.prodId = prodId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buyerId == null) ? 0 : buyerId.hashCode());
		result = prime * result + ((prodId == null) ? 0 : prodId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartId other = (CartId) obj;
		if (buyerId == null) {
			if (other.buyerId != null)
				return false;
		} else if (!buyerId.equals(other.buyerId))
			return false;
		if (prodId == null) {
			if (other.prodId != null)
				return false;
		} else if (!prodId.equals(other.prodId))
			return false;
		return true;
	}
	
	

}
