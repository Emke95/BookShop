package emma.store.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import emma.store.service.BookService;
import emma.store.validator.FileValidator;
import emma.store.entity.Book;
import emma.store.entity.Category;

@Controller
public class BookController {
	@Autowired
	private BookService  bookService;

	@Autowired
	private FileValidator fileValidator;

	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {

		Object obj = dataBinder.getTarget();
		if (obj == null) {
			return;
		}
		System.out.println("Target=" + obj);

		if (obj.getClass() == Book.class) {
			dataBinder.setValidator(fileValidator);
			// For upload Image.
			dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		}
	}

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String listBooksPage(Model model,@RequestParam(value = "title", defaultValue = "") String likeName)
	{

		List<Book> bookList = bookService.findAll();

		model.addAttribute("bookList", bookList);
		return "books";
	}
	
	@RequestMapping(value = "/titleAsc", method = RequestMethod.GET)
	public String listBooksAsc(Model model,@RequestParam(value = "title", defaultValue = "") String likeName)
	{

		List<Book> bookList = bookService.alphabetical();

		model.addAttribute("bookList", bookList);
		return "books";
	}
	
	@RequestMapping(value = "/titleDesc", method = RequestMethod.GET)
	public String listBooksDesc(Model model,@RequestParam(value = "title", defaultValue = "") String likeName)
	{

		List<Book> bookList = bookService.descending();

		model.addAttribute("bookList", bookList);
		return "books";
	}
	
	@RequestMapping(value = "/authAsc", method = RequestMethod.GET)
	public String listAuthAsc(Model model,@RequestParam(value = "title", defaultValue = "") String likeName)
	{

		List<Book> bookList = bookService.authAlphabetical();

		model.addAttribute("bookList", bookList);
		return "books";
	}

	@RequestMapping(value = "/authDesc", method = RequestMethod.GET)
	public String listAuthDesc(Model model,@RequestParam(value = "title", defaultValue = "") String likeName)
	{

		List<Book> bookList = bookService.authDescending();

		model.addAttribute("bookList", bookList);
		return "books";
	}
	
	@RequestMapping(value = "/isbnAsc", method = RequestMethod.GET)
	public String listIsbnAsc(Model model,@RequestParam(value = "title", defaultValue = "") String likeName)
	{

		List<Book> bookList = bookService.IsbnAscending();

		model.addAttribute("bookList", bookList);
		return "books";
	}
	
	@RequestMapping(value = "/isbnDesc", method = RequestMethod.GET)
	public String listIsbnDesc(Model model,@RequestParam(value = "title", defaultValue = "") String likeName)
	{

		List<Book> bookList = bookService.IsbnDescending();

		model.addAttribute("bookList", bookList);
		return "books";
	}
	
	@RequestMapping(value= {"/book"}, method = RequestMethod.GET)
	public String getBooksPage(Model model) {
		Book book = new Book();
		Category[] categoryList = Category.values();

		model.addAttribute("categoryList", categoryList);
		model.addAttribute("bookForm", book);
		return"book";
	}

	@RequestMapping(value = "/book/edit/{isbn}", method = RequestMethod.GET)
	public String getEditBookForm(Model model, @PathVariable String isbn) {

		Book book = bookService.findByIsbn(isbn);

		model.addAttribute("bookForm", book);

		return "book"; 
	}

	@RequestMapping(value = "/book/delete", method = RequestMethod.POST)
	public String bookDelete(Model model,@RequestParam(value="isbn", defaultValue="")String isbn) {
		Book book=null;
		if (isbn != null && isbn.length()> 0) {
			book = bookService.findByIsbn(isbn);
		}
		if(book!=null)
			bookService.deleteBook(book);

		return "redirect:/books";  
	}


	@RequestMapping(value = "/book", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.NEVER)
	public String postCreateBook(@ModelAttribute("bookForm") @Valid Book book, BindingResult result) {

		if (result.hasErrors()) {
			return "book";
		}
		try {
			bookService.save(book);
		}
		catch (Exception e) {
			return "book";
		}
		return "redirect:/books"; 
	}

	@RequestMapping(value = { "/bookCover" }, method = RequestMethod.GET)
	public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,	@RequestParam("isbn") String isbn) throws IOException {

		Book book = null;

		if (isbn != null) {
			book = this.bookService.findByIsbn(isbn);
		}

		if (book != null && book.getImage() != null) {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(book.getImage());
		}

		response.getOutputStream().close();
	}
}
