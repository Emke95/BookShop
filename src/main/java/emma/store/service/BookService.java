package emma.store.service;

import java.util.List;

import emma.store.entity.Book;
import emma.store.entity.Category;

public interface BookService {
	List<Book> alphabetical();
	List<Book> descending();
	List<Book> authAlphabetical();
	List<Book> authDescending();
	List<Book> findAll();
	List<Book> blurrySearch(String title);
	List<Book> blurryCatSearch(String category);
	List<Book> blurryAuthSearch(String author);
	Book findOne(Long id);
	void save(Book book);
	void delete(Long id);
	void deleteBook(Book book);
	Book findByIsbn(String isbn);
	List<Book> findByCategory(Category category);
}
