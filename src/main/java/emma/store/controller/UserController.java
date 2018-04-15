package emma.store.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import emma.store.service.UserService;
import emma.store.entity.*;

@Controller
@Transactional
@EnableWebMvc
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String showUsers(Model model) {

		List<User> users = userService.findAll();
		model.addAttribute("usersList", users);
		return "users";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login(Model model) {

		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getRegisterPage() {

		return "register";
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	@Transactional(propagation = Propagation.NEVER)
	public String registerConfirm(@ModelAttribute User user) {
		userService.save(user);
		return "redirect:/login";	

	}

	@RequestMapping(value = "/users/delete/{id}", method = RequestMethod.POST)
	public String deleteUser(@PathVariable Long id) {

		userService.delete(id);

		return "redirect:/users";
	}

	@RequestMapping("/profile")
	public String myProfile(Model model, Principal principal) {
		String email = principal.getName();
		User user = userService.findByEmail(email);
		model.addAttribute("user", user);
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("orderList", user.getOrderList());

		UserShipping userShipping = new UserShipping();
		model.addAttribute("userShipping", userShipping);

		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("listOfShippingAddresses", true);

		model.addAttribute("classActiveEdit", true);

		return "profile";
	}

	@RequestMapping("/listOfShippingAddresses")
	public String listOfShippingAddresses(
			Model model, Principal principal, HttpServletRequest request
			) {
		String email = principal.getName();
		User user = userService.findByEmail(email);
		model.addAttribute("user", user);
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("orderList", user.getOrderList());

		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("classActiveShipping", true);
		model.addAttribute("listOfShippingAddresses", true);

		return "profile";
	}

	@RequestMapping("/addNewCreditCard")
	public String addNewCreditCard(Model model, Principal principal){

		String email = principal.getName();
		User user = userService.findByEmail(email);
		model.addAttribute("user", user);

		model.addAttribute("addNewCreditCard", true);
		model.addAttribute("classActiveBilling", true);
		model.addAttribute("listOfShippingAddresses", true);

		UserBilling userBilling = new UserBilling();
		UserPayment userPayment = new UserPayment();


		model.addAttribute("userBilling", userBilling);
		model.addAttribute("userPayment", userPayment);

		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("orderList", user.getOrderList());

		return "profile";
	}

	
	@RequestMapping(value="/addNewCreditCard", method=RequestMethod.POST)
	public String addNewCreditCard(
			@ModelAttribute("userPayment") UserPayment userPayment,
			@ModelAttribute("userBilling") UserBilling userBilling,
			Principal principal, Model model
			){

		String email = principal.getName();
		User user = userService.findByEmail(email);
		userService.updateUserBilling(userBilling, userPayment, user);
		
		model.addAttribute("user", user);
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("classActiveBilling", true);
		model.addAttribute("listOfShippingAddresses", true);
		model.addAttribute("orderList", user.getOrderList());
		
		return "profile";
	}
	
	@RequestMapping(value="/addNewShippingAddress", method=RequestMethod.POST)
	public String addNewShippingAddressPost(
			@ModelAttribute("userShipping") UserShipping userShipping,
			Principal principal, Model model
			){

		String email = principal.getName();
		User user = userService.findByEmail(email);
		userService.updateUserShipping(userShipping, user);
		
		model.addAttribute("user", user);
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("listOfShippingAddresses", true);
		model.addAttribute("classActiveShipping", true);
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("orderList", user.getOrderList());
		
		return "profile";
	}
	

}