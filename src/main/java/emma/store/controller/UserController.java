package emma.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import emma.store.dao.UserDao;
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
}
