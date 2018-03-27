package emma.store.dao;

import java.util.List;

import emma.store.entity.Book;
import emma.store.model.BookInfo;

public interface BookDao {

	public void save(BookInfo bookInfo);
	//public void save(Book book);
	public Book findBook(String isbn);

	public BookInfo findBookInfo(String isbn);

	public List<BookInfo> queryBooks(String likeName);

	public void delete(BookInfo bookInfo);

}
