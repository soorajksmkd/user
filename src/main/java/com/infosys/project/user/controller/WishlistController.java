package com.infosys.project.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.infosys.project.user.dto.WishlistDTO;
import com.infosys.project.user.service.WishlistService;

@RestController
public class WishlistController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	WishlistService wishlistService;
	
	@GetMapping(value = "/wishlist/{buyerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<WishlistDTO> viewWishlist(@PathVariable Long buyerId){
		logger.info("View all products from wishlist");
		return wishlistService.viewWishlist(buyerId);
	}
	
	@GetMapping(value = "/wishlist",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<WishlistDTO> getWishlistDetails(){
		logger.info("Fetching all products from wishlist");
		return wishlistService.getWishlistDetails();
	}
	
	@PostMapping(value = "/wishlist",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addToWishlist(@RequestBody WishlistDTO wishlistDTO) {
		logger.info("Place order cart : {}", wishlistDTO);
		String message = wishlistService.addToWishlist(wishlistDTO);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/wishlist/{buyerId}/{prodId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> removeFromWishlist(@PathVariable Long buyerId, @PathVariable Long prodId) {
		logger.info("Delete order from wishlist having buyerId - {}, prodId - {}", buyerId, prodId);
		String message = wishlistService.removeFromWishlist(buyerId, prodId);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@GetMapping(value = "/wishlist/{buyerId}/{prodId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public WishlistDTO getSpecificProductFromWishlist(@PathVariable Long buyerId, @PathVariable Long prodId) {
		logger.info("Get order from wishlist having buyerId - {}, prodId - {}", buyerId, prodId);
		return wishlistService.getSpecificProductFromWishlist(buyerId, prodId);
	}
	
}
