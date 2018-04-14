package emma.store.dao;

import java.util.ArrayList;
import java.util.List;

import emma.store.entity.Book;
import emma.store.entity.User;
import emma.store.model.BookInfo;

public interface BookDao {

	public void save(BookInfo bookInfo);
	public Book findBookByIsbn(String isbn);
	public Book findBookById(int bookId);
	public BookInfo findBookInfo(String isbn);
	public List<BookInfo> queryBooks(String likeName);
	public void deleteBook(BookInfo bookInfo);
	public String searchAll(Book book, String value, ArrayList<Book> bookList);

}
