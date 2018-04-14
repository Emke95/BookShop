package emma.store.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import emma.store.dao.BookDao;
import emma.store.entity.Book;
import emma.store.entity.Category;
import emma.store.entity.User;
import emma.store.model.BookInfo;
import emma.store.validator.FileValidator;


@Controller
public class BookController {

	@Autowired
	private BookDao  bookDao;

	@Autowired
	private FileValidator fileValidator;

	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {

		Object obj = dataBinder.getTarget();
		if (obj == null) {
			return;
		}
		System.out.println("Target=" + obj);

		if (obj.getClass() == BookInfo.class) {
			dataBinder.setValidator(fileValidator);
			// For upload Image.
			dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		}
	}

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String listBooksPage(Model model,@RequestParam(value = "title", defaultValue = "") String likeName)
	{

		List<BookInfo> bookList = bookDao.queryBooks(likeName);

		model.addAttribute("bookList", bookList);
		return "books";
	}

	@RequestMapping(value= {"/book"}, method = RequestMethod.GET)
	public String getBooksPage(Model model, @RequestParam(value = "isbn", defaultValue = "") String isbn) {
		BookInfo bookInfo = null;
		Category[] categoryList = Category.values();
		if(isbn!= null && isbn.length()>0) {
			bookInfo = bookDao.findBookInfo(isbn);
		}

		if(bookInfo == null) {
			bookInfo = new BookInfo();
			bookInfo.setNewBook(true);
		}
		
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("bookForm", bookInfo);
		return"book";
	}



	@RequestMapping(value = "/bookDelete", method = RequestMethod.POST)
	public String bookDelete(Model model,@RequestParam(value="isbn", defaultValue="")String isbn) {
		BookInfo bookInfo=null;
		if (isbn != null && isbn.length()> 0) {
			bookInfo = bookDao.findBookInfo(isbn);
		}
		if(bookInfo!=null)
			bookDao.deleteBook(bookInfo);

		return "redirect:/books";  
	}


	@RequestMapping(value = "/book", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.NEVER)
	public String postCreateBook(Model model,@ModelAttribute("bookForm") @Validated BookInfo bookInfo, BindingResult result, final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "book";
		}
		try {
			bookDao.save(bookInfo);
		}
		catch (Exception e) {
			String message = e.getMessage();
			model.addAttribute("message", message);           
			return "book";
		}
		return "redirect:/books"; 
	}

	@RequestMapping(value = { "/bookCover" }, method = RequestMethod.GET)
	public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,	@RequestParam("isbn") String isbn) throws IOException {

		Book book = null;

		if (isbn != null) {
			book = this.bookDao.findBookByIsbn(isbn);
		}

		if (book != null && book.getImage() != null) {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(book.getImage());
		}

		response.getOutputStream().close();
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces="application/text")
	public @ResponseBody String search(@RequestParam("search") String value, HttpServletRequest request)
	{
		if(value!=null || !value.isEmpty())
		{
			HttpSession session = request.getSession();
			Book book = (Book) session.getAttribute("book");
			ArrayList<Book> bookList = new ArrayList<>();
		    String html = bookDao.searchAll(book, value, bookList);
			return html;
		}
		
		return " ";
	}
}
