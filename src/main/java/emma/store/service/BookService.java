package emma.store.service;

import java.util.List;

import emma.store.entity.Book;

public interface BookService {
	List<Book> findAll();
	Book findOne(Long id);
	void save(Book book);
	void delete(Long id);
	 void deleteBook(Book book);
	Book findByIsbn(String isbn);
}
