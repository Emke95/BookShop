package emma.store.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import emma.store.dao.UserDao;
import emma.store.entity.ShippingAddress;
import emma.store.entity.User;

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

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String getEditPage(Model model, @RequestParam(value = "userId", defaultValue = "") int userId) {

		User user = null;
		model.addAttribute("editForm", user);
		return "updateUserAccount";
	}

//	@RequestMapping(value="/addcard", method = RequestMethod.PUT)
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.NEVER)
	public String updateUser(Model model,@ModelAttribute("editForm")User user,  BindingResult result, final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "updateUserAccount";
		}
		try {
			userDao.edit(user);
		}
		catch (Exception e) {
			String message = e.getMessage();
			model.addAttribute("message", message);           
			return "updateUserAccount";
		}
		return "redirect:/main"; 
	}

	@RequestMapping(value= {"/address"}, method = RequestMethod.GET)
	public String getAddressPage(Model model, HttpServletRequest request) { 
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		ShippingAddress shippingAddress = null;	
		if (shippingAddress == null) {
			shippingAddress = new ShippingAddress();
		}	
		model.addAttribute("addressForm", shippingAddress);
		return"address";

	}

	/*@RequestMapping(value = "/address", method = RequestMethod.GET)
	public String getAddress(Model model, String shippingAddressId, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int addressId = Integer.parseInt(shippingAddressId);
		ShippingAddress shippingAddress = userDao.getAddress(user, addressId);

		model.addAttribute("address", shippingAddress);
		return "address";
	}
	 */


	/*@RequestMapping(value = "/address", method = RequestMethod.POST)
	public String addAddress(Model model, ShippingAddress shippingAddress, HttpServletRequest request)
	{
		if(shippingAddress!=null)
		{
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");

			shippingAddress.setUser(user);
			userDao.save(shippingAddress);
			//ArrayList<ShippingAddress> addressList = userDao.getMyAddresses(user.getUserId());
			//model.addAttribute("address", addressList);

		}
		return "redirect:/main";
	}*/


	@RequestMapping(value= {"/address"}, method = RequestMethod.PUT)
	@Transactional(propagation = Propagation.NEVER)
	public String addAddress(@Valid @ModelAttribute("user") User user, ShippingAddress shippingAddress, BindingResult result,
			Model model)
	{	
		userDao.save(shippingAddress);

		return"redirect:/books";
	}
}
