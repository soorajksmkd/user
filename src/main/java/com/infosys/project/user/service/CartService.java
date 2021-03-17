package com.infosys.project.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.infosys.project.user.dto.CartDTO;
import com.infosys.project.user.entity.Cart;
import com.infosys.project.user.repository.CartRepository;

@Service
@Transactional
public class CartService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CartRepository cartRepository;

	public List<CartDTO> getCartDetails() {
		List<Cart> cart = cartRepository.findAll();
		List<CartDTO> cartDTOs = new ArrayList<>();

		for (Cart c : cart) {
			CartDTO cartDTO = CartDTO.valueOf(c);
			cartDTOs.add(cartDTO);
		}
		logger.info("Cart details : {}", cartDTOs);

		return cartDTOs;
	}

	public String addToCart(CartDTO cartDTO) {
		logger.info("Add to cart : {}", cartDTO);
		Optional<Cart> optionalCart = cartRepository.findByBuyerIdAndProdId(cartDTO.getBuyerId(), cartDTO.getProdId());
		if (optionalCart.isPresent()) {
			logger.info("Already present in cart");
			return ("Already present in cart");
		} else {
			Cart cart = cartDTO.createEntity();
			cartRepository.save(cart);
			logger.info("Added to cart");
			return "Added to cart";
		}
	}

	public String removeFromCart(Long buyerId, Long prodId) {
		logger.info("Remove from cart buyerId : {}, prodId : {}", buyerId, prodId);
		Optional<Cart> optionalCart = cartRepository.findByBuyerIdAndProdId(buyerId, prodId);
		if (optionalCart.isPresent()) {
			cartRepository.deleteByBuyerIdAndProdId(buyerId, prodId);
			logger.info("Removed from cart buyerId : {}, prodId : {}", buyerId, prodId);
			return "Removed from cart";
		} else {
			logger.info("Item does not exist");
			return "Product does not exist in cart";
		}

	}

	public CartDTO getSpecificDetails(Long buyerId, Long prodId) {
		// TODO Auto-generated method stub
		
		Optional<Cart> optionalCart = cartRepository.findByBuyerIdAndProdId(buyerId, prodId);
		if (optionalCart.isPresent()) {
			logger.info("In if condition");
			Cart cart = optionalCart.get();
			CartDTO cartDTO = CartDTO.valueOf(cart);
			return cartDTO;
		}
		return null;
	}
}
