package emma.store.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emma.store.dao.BookDao;
import emma.store.entity.Book;
import emma.store.entity.Category;
import emma.store.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	@Autowired
	private BookDao bookDao;

	@Override
	public List<Book> findAll() {
		return bookDao.findAll();
	}

	@Override
	public Book findOne(Long id) {
		return bookDao.findOne(id);
	}

	@Override
	public void save(Book book) {
		if (book.getFileData() != null) {
			byte[] image = book.getFileData().getBytes();
			if (image != null && image.length > 0) {
				book.setImage(image);
			}
		}
		book.setCreateDate(new Date());
		bookDao.save(book);

	}

	@Override
	public void delete(Long id) {
		bookDao.delete(id);

	}

	public List<Book> blurrySearch(String title) {
		List<Book> bookList = bookDao.findByTitleContaining(title);
		List<Book> activeBookList = new ArrayList<>();

		for (Book book: bookList) {
			if(book.getQuantity() > 0) {
				activeBookList.add(book);
			}
		}

		return activeBookList;
	}
	
	public List<Book> blurryCatSearch(String category) {
		List<Book> bookList = bookDao.findByCategoryContaining(category);
		List<Book> activeBookList = new ArrayList<>();

		for (Book book: bookList) {
			if(book.getQuantity() > 0) {
				activeBookList.add(book);
			}
		}

		return activeBookList;
	}

	@Override
	public Book findByIsbn(String isbn) {
		return bookDao.findByIsbn(isbn);
	}

	@Override
	public void deleteBook(Book book) {
		bookDao.delete(book);	}

	@Override
	public List<Book> findByCategory(Category category) {
		List<Book> bookList = bookDao.findByCategory(category);

		List<Book> activeBookList = new ArrayList<>();

		for (Book book: bookList) {
			//if(book.isActive()) {
				activeBookList.add(book);
			
		}

		return activeBookList;
	}

	@Override
	public List<Book> blurryAuthSearch(String author) {
		List<Book> bookList = bookDao.findByAuthorContaining(author);
		List<Book> activeBookList = new ArrayList<>();

		for (Book book: bookList) {
			if(book.getQuantity() > 0) {
				activeBookList.add(book);
			}
		}

		return activeBookList;
	}

}
