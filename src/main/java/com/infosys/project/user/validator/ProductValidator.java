package com.infosys.project.user.validator;

import java.math.BigDecimal;

import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.infosys.project.user.dto.ProductDTO;

public class ProductValidator {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	Environment environment;

	public static String validateProduct(ProductDTO productDTO) {
		if (!isValidProductName(productDTO.getProductName()))
			return ("Invalid Product Name Format!!!");
		if (!isValidDescription(productDTO.getDescription()))
			return ("Product Description too long!!!");
		if (!isValidPrice(productDTO.getPrice()))
			return ("Product should be priced atleast at ");
		if (!isValidStock(productDTO.getStock()))
			return ("Stock should be minimum 10!!");
		if (!isValidImage(productDTO.getImage()))
			return ("Image should be in jpeg/png format!!!");
		return ("OK");

	}

	private static boolean isValidImage(String image) {
		String imageSubString = image.substring(1, image.length()-1);
		String regex= "[^\s]+.(jpeg|png)";
		if (imageSubString.matches(regex))
			return true;
		return false;
	}

	private static boolean isValidStock(Long stock) {
		if (stock >= 10L)
			return true;
		return false;
	}

	private static boolean isValidPrice(BigDecimal price) {
		BigDecimal bd = new BigDecimal(200);
		if (price.compareTo(bd) > 0)
			return true;
		return false;
	}

	private static boolean isValidDescription(String description) {
		if (description.length() <= 500)
			return true;
		return false;
	}

	private static boolean isValidProductName(String productName) {
		String regex = "([a-zA-Z][a-zA-Z ,']{0,98}[a-zA-Z]|[a-zA-Z])";
		if (productName.matches(regex))
			return true;
		return false;
	}
}
