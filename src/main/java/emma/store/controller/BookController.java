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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import emma.store.dao.BookDao;
import emma.store.entity.Book;
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

	@RequestMapping(value= {"/book/create"}, method = RequestMethod.GET)
	public String getBooksPage(Model model, @RequestParam(value = "isbn", defaultValue = "") String isbn) {
		BookInfo bookInfo = null;

		if(isbn!= null && isbn.length()>0) {
			bookInfo = bookDao.findBookInfo(isbn);
		}

		if(bookInfo == null) {
			bookInfo = new BookInfo();
			bookInfo.setNewBook(true);
		}
		
		model.addAttribute("book", bookInfo);
		return"book-create";
	}

	@RequestMapping(value = "/book/edit/{isbn}", method = RequestMethod.GET)
	public String getEditBookForm(Model model, @PathVariable String isbn) {

		BookInfo bookInfo = bookDao.findBookInfo(isbn);

		model.addAttribute("book", bookInfo);

		return "book-create"; 
	}

	@RequestMapping(value = "/book/delete/{isbn}", method = RequestMethod.POST)
	public String postDeleteBook(@PathVariable String isbn) {
		BookInfo bookInfo=null;
		if (isbn != null && isbn.length()> 0) {
			bookInfo = bookDao.findBookInfo(isbn);
		}
		if(bookInfo!=null)
			bookDao.delete(bookInfo);

		return "redirect:/books";  
	}


	@RequestMapping(value = "/book/save", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.NEVER)
	public @ResponseBody String postCreateBook(Model model,@ModelAttribute("book") @Validated BookInfo bookInfo, BindingResult result, final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "book-create";
		}
		try {
			bookDao.save(bookInfo);
		}
		catch (Exception e) {
			String message = e.getMessage();
			model.addAttribute("message", message);           
			return "book-create";
		}

		return "books"; 
	}

	@RequestMapping(value = { "/bookCover" }, method = RequestMethod.GET)
	public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,	@RequestParam("isbn") String isbn) throws IOException {

		Book book = null;

		if (isbn != null) {
			book = this.bookDao.findBook(isbn);
		}

		if (book != null && book.getImage() != null) {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(book.getImage());
		}

		response.getOutputStream().close();
	}
}
