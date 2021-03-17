package com.infosys.project.user.validator;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.infosys.project.user.dto.BuyerDTO;
import com.infosys.project.user.dto.SellerDTO;
import com.infosys.project.user.entity.Buyer;
import com.infosys.project.user.entity.Seller;
import com.infosys.project.user.repository.BuyerRepository;
import com.infosys.project.user.repository.SellerRepository;

public class Validator {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BuyerRepository buyerRepository;
	
	@Autowired
	SellerRepository sellerRepository;

	public static String validateBuyer(BuyerDTO user) throws Exception {
		if (!isValidUserName(user.getName())) {
			return ("Entered Username is Invalid! (Hint:  It should contain only alphabets and spaces."
					+ "It should not start or end with a space. It can't contain only spaces)");
		}

		if (!isValidPassword(user.getPassword())) {
			return ("Entered Password is Invalid! (Hint: It should be 7 to 20 characters in length (both inclusive)."
					+ "It should contain atleast one uppercase, at least one lowercase, at least one digit. It should"
					+ "also contain a special character amongst -  !, @, #, $, %, ^, &, *)");
		}

		if (!isValidEmail(user.getEmail())) {
			return ("Entered emailId is in invalid format! (Hint: Use the following format: example@exm.com)");
		}

		if (!isValidMobileNumber(user.getPhoneNumber())) {
			return ("Entered Mobile number is not a valid Indian mobile number! (Hint: It can contain only digits"
					+ "and length should be 10)");
		}
		return "OK";
	}

	public static String validateSeller(SellerDTO user) throws Exception {
		if (!isValidUserName(user.getName())) {
			return ("Entered Username is Invalid! (Hint:  It should contain only alphabets and spaces."
					+ "It should not start or end with a space. It can't contain only spaces)");
		}

		if (!isValidPassword(user.getPassword())) {
			return ("Entered Password is Invalid! (Hint: It should be 7 to 20 characters in length (both inclusive)."
					+ "It should contain atleast one uppercase, at least one lowercase, at least one digit. It should"
					+ "also contain a special character amongst -  !, @, #, $, %, ^, &, *)");
		}

		if (!isValidEmail(user.getEmail())) {
			return ("Entered emailId is in invalid format! (Hint: Use the following format: example@exm.com)");
		}

		if (!isValidMobileNumber(user.getPhoneNumber())) {
			return ("Entered Mobile number is not a valid Indian mobile number! (Hint: It can contain only digits"
					+ " and length should be 10)");
		}
		return "OK";
	}

	public static Boolean isValidUserName(String userName) {
		String regex = "[a-zA-Z][a-zA-Z ]{0,98}[a-zA-Z]";
		String regex1 = "[a-zA-Z]";
		if ((userName.matches(regex)) || (userName.matches(regex1)))
			return true;
		else
			return false;
	}

	public static Boolean isValidEmail(String email) {

		String regex = "^[\\w]+@[A-Za-z]+\\.[\\w]+$";
		if (email.matches(regex))
			return true;
		else
			return false;
	}

	public static Boolean isValidMobileNumber(String mobileNo) {

		String pattern = "[\\d]{10}";
		if (mobileNo.matches(pattern))
			return true;
		else
			return false;
	}

	public static Boolean isValidPassword(String password) {
		String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{7,20}$";
		if (password.matches(regex))
			return true;
		else
			return false;
	}

	public Boolean validateBuyerLogin(BuyerDTO loginDTO) {
		Optional<Buyer> optCust = buyerRepository.findByEmail(loginDTO.getEmail());
		if (optCust.isPresent()) {
			Buyer cust = optCust.get();
			if (cust.getPassword().equals(loginDTO.getPassword())) {
				return true;
			}
		}

		return false;
	}

	public Boolean validateSellerLogin(SellerDTO loginDTO) {
		Optional<Seller> optCust = sellerRepository.findByEmail(loginDTO.getEmail());
		if (optCust.isPresent()) {
			Seller cust = optCust.get();
			if (cust.getPassword().equals(loginDTO.getPassword())) {
				return true;
			}
		}

		return false;
	}

}
