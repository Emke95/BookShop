package emma.store.controller;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import emma.store.service.BookService;
import emma.store.service.UserService;
import emma.store.entity.User;
import emma.store.entity.Book;

@Controller
public class AppController {


	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getMainPage() {

		return "main";
	}

	@RequestMapping("/bookDetail")
	public String bookDetail(
			@PathParam("id") Long id, Model model, Principal principal
			) {
		if(principal != null) {
			String email = principal.getName();
			User user = userService.findByEmail(email);
			model.addAttribute("user", user);
		}

		Book book = bookService.findOne(id);

		model.addAttribute("book", book);

		List<Integer> qtyList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

		model.addAttribute("qtyList", qtyList);
		model.addAttribute("qty", 1);

		return "bookDetail";
	}

}