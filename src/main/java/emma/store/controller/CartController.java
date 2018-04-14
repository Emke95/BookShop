package emma.store.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import emma.store.dao.BookDao;
import emma.store.dao.OrderDao;
import emma.store.dao.UserDao;
import emma.store.entity.Book;
import emma.store.entity.Cart;

@Controller
public class CartController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private BookDao bookDao;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private Cart cart;

	@RequestMapping(value = "cart/add/{isbn}")
	public String addToCart(@PathVariable("isbn") String isbn, @RequestHeader("referer") String referedFrom) {
		Book book = bookDao.findBookByIsbn(isbn);
		cart.addBook(book, 1);
		return "redirect:/books";
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String showCart(Model model){
		model.addAttribute(cart);
		return "cart";
	}

	@RequestMapping(value = "/cart/confirmOrder", method = RequestMethod.POST)
	public String confirmOrder(HttpSession httpSession, RedirectAttributes redirectAttributes, Principal principal){
		if(cart.getContents().isEmpty()){
			redirectAttributes.addFlashAttribute("cartMessage", "Cart is empty. Please add books to the cart to make order.");
			return "redirect:/cart";
		}else{
			String email = principal.getName();
			orderDao.addOrder(cart.getContents(), userDao.findByEmail(email));
			redirectAttributes.addFlashAttribute("cartMessage", "Order is confirmed. Total cost: " + cart.getTotalCost());
			cart.clearCart();
			return "redirect:/cart";
		}
	}
}
