package emma.store.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import emma.store.Strategy.Card;
import emma.store.Strategy.Counties;
import emma.store.Strategy.PaymentMethod;
import emma.store.Strategy.PaymentMethodFactory;
import emma.store.entity.*;
import emma.store.service.*;

@Controller
public class OrderController {
	
	private ShippingAddress shippingAddress = new ShippingAddress();
	private Payment payment = new Payment();

	@Autowired
	private UserService userService;
	
	@Autowired
	BookService bookService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private ShippingAddressService shippingAddressService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private UserShippingService userShippingService;

	@Autowired
	private UserPaymentService userPaymentService;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDemoService orderDemoService;
	
	@Autowired
	private Cart cart;

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String showOrderDemos(Model model) {
		List<OrderDemo> orderDemos = orderDemoService.findAll();
		model.addAttribute("orderDemoList", orderDemos);
		return "orders";
	}
	
	@RequestMapping(value="/searchByUser")
	public String showOrderUser(@RequestParam("id") Long id, Principal principal, Model model)
	{
		String email = principal.getName();
		User user = userService.findByEmail(email);
		id = user.getId();
		
		List<OrderDemo> orderDemos = orderDemoService.findByUserId(id);
		model.addAttribute("orderDemoList", orderDemos);
		return"orders";
		
	}

	@RequestMapping("/addPaymentMethod")
	public String addPaymentMethod(Model model, Principal principal)
	{
		List<String> paymentList = PaymentMethodFactory.listOfPaymentMethods;
		Collections.sort(paymentList);
		model.addAttribute("paymentList", paymentList);
		model.addAttribute("classActiveEdit", true);

		return "redirect:/main";
	}
	
	@RequestMapping("/checkout")
	public String checkout(@RequestParam("id") Long cartId,
			@RequestParam(value = "missingRequiredField", required = false) boolean missingRequiredField, Model model,
			Principal principal) {
		String email = principal.getName();
		User user = userService.findByEmail(email);

		if (cartId != user.getShoppingCart().getId()) {
			return "badRequestPage";
		}

		List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

		if (cartItemList.size() == 0) {
			model.addAttribute("emptyCart", true);
			return "forward:/cart";
		}

		for (CartItem cartItem : cartItemList) {
			if (cartItem.getBook().getQuantity() < cartItem.getQty()) {
				model.addAttribute("notEnoughStock", true);
				return "forward:/cart";
			}
		}

		List<UserShipping> userShippingList = user.getUserShippingList();
		List<UserPayment> userPaymentList = user.getUserPaymentList();

		model.addAttribute("userShippingList", userShippingList);
		model.addAttribute("userPaymentList", userPaymentList);

		if (userPaymentList.size() == 0) {
			model.addAttribute("emptyPaymentList", true);
		} else {
			model.addAttribute("emptyPaymentList", false);
		}

		if (userShippingList.size() == 0) {
			model.addAttribute("emptyShippingList", true);
		} else {
			model.addAttribute("emptyShippingList", false);
		}
		
		for (UserShipping userShipping : userShippingList) {
			if (userShipping.isUserShippingDefault()) {
				shippingAddressService.setByUserShipping(userShipping, shippingAddress);
			}
		}

		for (UserPayment userPayment : userPaymentList) {
			if (userPayment.isDefaultPayment()) {
				paymentService.setByUserPayment(userPayment, payment);
			}
		}

		model.addAttribute("shippingAddress", shippingAddress);
		model.addAttribute("payment", payment);
		model.addAttribute("cartItemList", cartItemList);
		model.addAttribute("shoppingCart", user.getShoppingCart());

		model.addAttribute("classActiveShipping", true);

		if (missingRequiredField) {
			model.addAttribute("missingRequiredField", true);
		}

		return "checkout";

	}

	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public String checkoutPost(@ModelAttribute("shippingAddress") ShippingAddress shippingAddress,
			@ModelAttribute("payment") Payment payment,
			@ModelAttribute("shippingMethod") String shippingMethod, Principal principal, Model model) {

		String email = principal.getName();
		User u = userService.findByEmail(email);
		ShoppingCart shoppingCart = u.getShoppingCart();

		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		model.addAttribute("cartItemList", cartItemList);

		if (shippingAddress.getHouseNo()== 0 
				|| shippingAddress.getStreet().isEmpty()
				|| shippingAddress.getArea().isEmpty()
				|| shippingAddress.getCounty().isEmpty()
				|| shippingAddress.getCountry().isEmpty() 
				|| payment.getCardNumber().isEmpty()
				|| payment.getCvc() == 0)
			return "redirect:/checkout?id=" + shoppingCart.getId() + "&missingRequiredField=true";

		User user = userService.findByEmail(email);

		orderService.createOrders(shoppingCart, shippingAddress, payment, user);

		shoppingCartService.clearShoppingCart(shoppingCart);

		return "orderSubmittedPage";
	}

	@RequestMapping("/setShippingAddress")
	public String setShippingAddress(@RequestParam("userShippingId") Long userShippingId, Principal principal,
			Model model) {
		String email = principal.getName();
		User user = userService.findByEmail(email);

		UserShipping userShipping = userShippingService.findById(userShippingId);

		if (userShipping.getUser().getId() != user.getId()) {
			return "badRequestPage";
		} else {
			shippingAddressService.setByUserShipping(userShipping, shippingAddress);

			List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

			model.addAttribute("shippingAddress", shippingAddress);
			model.addAttribute("payment", payment);
			model.addAttribute("cartItemList", cartItemList);
			model.addAttribute("shoppingCart", user.getShoppingCart());

			List<UserShipping> userShippingList = user.getUserShippingList();
			List<UserPayment> userPaymentList = user.getUserPaymentList();

			model.addAttribute("userShippingList", userShippingList);
			model.addAttribute("userPaymentList", userPaymentList);

			model.addAttribute("shippingAddress", shippingAddress);

			model.addAttribute("classActiveShipping", true);

			if (userPaymentList.size() == 0) {
				model.addAttribute("emptyPaymentList", true);
			} else {
				model.addAttribute("emptyPaymentList", false);
			}

			model.addAttribute("emptyShippingList", false);

			return "checkout";
		}
	}
	@RequestMapping("/setPaymentMethod")
	public String setPaymentMethod(@RequestParam("userPaymentId") Long userPaymentId, Principal principal,
			Model model) {
		User user = userService.findByEmail(principal.getName());
		UserPayment userPayment = userPaymentService.findById(userPaymentId);
		
		if (userPayment.getUser().getId() != user.getId()) {
			return "badRequestPage";
		} else {
			paymentService.setByUserPayment(userPayment, payment);

			List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

			model.addAttribute("shippingAddress", shippingAddress);
			model.addAttribute("payment", payment);
			model.addAttribute("cartItemList", cartItemList);
			model.addAttribute("shoppingCart", user.getShoppingCart());

			List<UserShipping> userShippingList = user.getUserShippingList();
			List<UserPayment> userPaymentList = user.getUserPaymentList();

			model.addAttribute("userShippingList", userShippingList);
			model.addAttribute("userPaymentList", userPaymentList);

			model.addAttribute("shippingAddress", shippingAddress);

			model.addAttribute("classActivePayment", true);

			model.addAttribute("emptyPaymentList", false);

			if (userShippingList.size() == 0) {
				model.addAttribute("emptyShippingList", true);
			} else {
				model.addAttribute("emptyShippingList", false);
			}

			return "checkout";
		}
	}
	
	// Simple Cart methods for demo
	
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
	
	@Transactional
	@RequestMapping(value= "cart/remove/{isbn}")
	public String removeFromCart(@PathVariable("isbn") String isbn)
	{
		Book book = bookService.findByIsbn(isbn);
		cart.removeBook(book);
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
			redirectAttributes.addFlashAttribute("cartMessage", "Order is confirmed. Total cost: $" + cart.getTotalCost());
			cart.clearCart();
			return "redirect:/cart";
		}	}
}
