package emma.store.controller;

import java.security.Principal;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import emma.store.service.BookService;
import emma.store.service.OrderDemoService;
import emma.store.service.UserService;
import emma.store.entity.Cart;
import emma.store.entity.Book;

@Controller
public class AppController {

	@Autowired
	private Cart cart;

	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;

	@Autowired
	private OrderDemoService orderDemoService;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getMainPage() {

		return "main";
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String showCart(Model model){
		model.addAttribute(cart);
		return "cart";
	}
	
	@RequestMapping(value = "cart/add/{isbn}")
		public String addToCart(@PathVariable("isbn") String isbn, @RequestHeader("referer") String referedFrom) {
			Book book = bookService.findByIsbn(isbn);
			cart.addBook(book, 1);
			return "redirect:/cart";
		}

	@RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
	public String confirmOrder(HttpSession httpSession, RedirectAttributes redirectAttributes, Principal principal){
		if(cart.getContents().isEmpty()) {
			redirectAttributes.addFlashAttribute("cartMessage", "Cart is empty. Please add books to the cart to make order.");
			return "redirect:/cart";
		}
		else
		{
			String email = principal.getName();
			orderDemoService.addOrderDemo(cart.getContents(), userService.findByEmail(email));
			redirectAttributes.addFlashAttribute("cartMessage", "Order is confirmed. Total cost: " + cart.getTotalCost());
			cart.clearCart();
			return "redirect:/cart";
		}	}
}