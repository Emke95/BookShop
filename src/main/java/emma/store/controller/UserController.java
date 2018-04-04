package emma.store.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import emma.store.dao.UserDao;
import emma.store.entity.CreditCard;
import emma.store.entity.ShippingAddress;
import emma.store.entity.User;
import emma.store.model.BookInfo;

@Controller
@Transactional
@EnableWebMvc
public class UserController {

	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String showUsers(Model model) {

		List<User> users = userDao.findAll();
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


		userDao.register(user);
		return "redirect:/login";	

	}


	@RequestMapping(value= {"/address"}, method = RequestMethod.GET)
	public String getAddressPage(Model model) { 
		ShippingAddress shippingAddress = null;	
		if (shippingAddress == null) {
			shippingAddress = new ShippingAddress();

		}	
		model.addAttribute("addressForm", shippingAddress);
		return"address";

	}

	@RequestMapping(value= {"/address"}, method = RequestMethod.POST)
	@Transactional(propagation = Propagation.NEVER)
	public String addAddress(Model model, ShippingAddress address, HttpServletRequest request)
	{
		/*public String addAddress(@ModelAttribute ShippingAddress shippingAddress, User user) {
			
			shippingAddress.setUser(user);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		address.setUser(user);
		model.addAttribute("address", address);*/
		
		userDao.save(address);
		
		return"redirect:/books";
	}
	

}
