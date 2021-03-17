package com.infosys.project.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.infosys.project.user.dto.WishlistDTO;
import com.infosys.project.user.entity.Wishlist;
import com.infosys.project.user.repository.WishlistRepository;

@Service
@Transactional
public class WishlistService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	WishlistRepository wishlistRepository;

	public List<WishlistDTO> getWishlistDetails() {
		List<Wishlist> wishlist = wishlistRepository.findAll();
		List<WishlistDTO> wishlistDTOs = new ArrayList<>();

		for (Wishlist w : wishlist) {
			WishlistDTO wishlistDTO = WishlistDTO.valueOf(w);
			wishlistDTOs.add(wishlistDTO);
		}
		logger.info("Wishlist details : {}", wishlistDTOs);

		return wishlistDTOs;
	}

	public String addToWishlist(WishlistDTO wishlistDTO) {
		logger.info("Add to wishlist : {}", wishlistDTO);
		Optional<Wishlist> optionalWishlist = wishlistRepository.findByBuyerIdAndProdId(wishlistDTO.getBuyerId(),
				wishlistDTO.getProdId());
		if (optionalWishlist.isPresent()) {
			return "Product already present in wishlist";
		} else {
			Wishlist wishlist = wishlistDTO.createEntity();
			wishlistRepository.save(wishlist);
			logger.info("Added to wishlist");
			return "Product added to wishlist";
		}
	}

	public String removeFromWishlist(Long buyerId, Long prodId) {
		logger.info("Remove from wishlist buyerId : {}, prodId : {}", buyerId, prodId);
		Optional<Wishlist> optionalWishlist = wishlistRepository.findByBuyerIdAndProdId(buyerId, prodId);
		if (optionalWishlist.isPresent()) {
			wishlistRepository.deleteByBuyerIdAndProdId(buyerId, prodId);
			logger.info("Removed from wishlist buyerId : {}, prodId : {}", buyerId, prodId);
			return "Product removed from wishlist";
		} else {
			logger.info("Item does not exist");
			return "Product does not exist in wishlist";
		}
	}

	public List<WishlistDTO> viewWishlist(Long buyerId) {
		List<Wishlist> wishLists = wishlistRepository.findByBuyerId(buyerId);
		List<WishlistDTO> wishlistDTOs = new ArrayList<>();
		for (Wishlist w : wishLists) {
			WishlistDTO wishlistDTO = WishlistDTO.valueOf(w);
			wishlistDTOs.add(wishlistDTO);
		}
		logger.info("Wishlist details : {}", wishlistDTOs);
		return wishlistDTOs;
	}

	public WishlistDTO getSpecificProductFromWishlist(Long buyerId, Long prodId) {
		Optional<Wishlist> optionalWishlist = wishlistRepository.findByBuyerIdAndProdId(buyerId, prodId);
		if (optionalWishlist.isPresent()) {
			Wishlist wishlist = optionalWishlist.get();
			WishlistDTO wishlistDTO = WishlistDTO.valueOf(wishlist);
			return wishlistDTO;
		}
		return null;
	}

}
