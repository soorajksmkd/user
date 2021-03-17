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
import com.infosys.project.user.dto.CartDTO;
import com.infosys.project.user.dto.WishlistDTO;
import com.infosys.project.user.service.CartService;
import com.infosys.project.user.service.WishlistService;

@RestController
public class CartController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CartService cartService;
	
	@Autowired
	WishlistService wishlistService;
	
	@GetMapping(value = "/cart",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CartDTO> getCartDetails(){
		logger.info("Fetching all products from cart");
		return cartService.getCartDetails();
	}
	
	@PostMapping(value = "/cart/add",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addToCart(@RequestBody CartDTO cartDTO) {
		logger.info("Place order cart : {}", cartDTO);
		String message = cartService.addToCart(cartDTO);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/cart/remove/{buyerId}/{prodId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> removeFromCart(@PathVariable Long buyerId, @PathVariable Long prodId) {
		logger.info("Delete order from cart having buyerId - {}, prodId - {}", buyerId, prodId);
		String message = cartService.removeFromCart(buyerId, prodId);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}	
	
	@PostMapping(value = "/cart/addFromWishlist")
	public ResponseEntity<String> addToCartFromWishlist(@RequestBody CartDTO cartDTO) {
		logger.info("Add from wishlist to cart");
	WishlistDTO wishlistDTO = wishlistService.getSpecificProductFromWishlist(cartDTO.getBuyerId(), cartDTO.getProdId());
	if (wishlistDTO == null)
		return new ResponseEntity<>("No such product in Wishlist", HttpStatus.OK);
	String message = cartService.addToCart(cartDTO);
	return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	

	@GetMapping(value = "/cart/{buyerId}/{prodId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public CartDTO getSpecificDetails(@PathVariable Long buyerId, @PathVariable Long prodId){
		logger.info("Fetching specific products from cart");
		return cartService.getSpecificDetails(buyerId, prodId);
	}
}
