package emma.store.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang.StringEscapeUtils;

import emma.store.entity.Book;
import emma.store.entity.User;
import emma.store.model.BookInfo;

@Transactional
public class BookDaoImpl implements BookDao{

	@Autowired
	private SessionFactory sessionFactory;

	public void save(BookInfo bookInfo) {
		String isbn = bookInfo.getIsbn();

		Book book = null;
		boolean isNew = false;

		if (isbn !=null) {
			book = this.findBookByIsbn(isbn);
		}

		if (book == null) {
			isNew = true;
			book = new Book();
			book.setCreateDate(new Date());
		}

		book.setIsbn(isbn);
		book.setTitle(bookInfo.getTitle());
		book.setAuthor(bookInfo.getAuthor());
		book.setCategory(bookInfo.getCategory());
		book.setPrice(bookInfo.getPrice());
		book.setQuantity(bookInfo.getQuantity());

		if (bookInfo.getFileData() != null) {
			byte[] image = bookInfo.getFileData().getBytes();
			if (image != null && image.length > 0) {
				book.setImage(image);
			}
		}

		if (isNew) {

			this.sessionFactory.getCurrentSession().persist(book);

		}

		this.sessionFactory.getCurrentSession().flush();
	}

	public Book findBookByIsbn(String isbn) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Book.class);
		crit.add(Restrictions.eq("isbn", isbn));
		return (Book) crit.uniqueResult();
	}
	
	public Book findBookById(int bookId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Book.class);
		crit.add(Restrictions.eq("bookId", bookId));
		return (Book) crit.uniqueResult();
	}

	public BookInfo findBookInfo(String isbn) {
		Book book = this.findBookByIsbn(isbn);
		if (book == null) {
			return null;
		}
		return new BookInfo(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getCategory(),book.getPrice(), book.getQuantity());
	}

	public List<BookInfo> queryBooks(String likeName) {
		String sql = "Select new " + BookInfo.class.getName() + "(b.isbn,b.title,b.author,b.category, b.price, b.quantity) " + " from " + Book.class.getName() + " b ";
		if (likeName != null && likeName.length() > 0) {
			sql += " Where lower(b.title) like :likeName ";
		}
		sql += " order by b.createDate desc ";
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(sql);
		if (likeName != null && likeName.length() > 0) {
			query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
		}

		return query.list();
	}

	public void deleteBook(BookInfo bookInfo) {
		String isbn = bookInfo.getIsbn();
		
		Book book = null;
		
		if(isbn!=null) {
			book = this.findBookByIsbn(isbn);
		}
		
		this.sessionFactory.getCurrentSession().delete(book);
	}

	@Override
	public String searchAll(Book book, String value, ArrayList<Book> bookList) {
		Query q = this.sessionFactory.getCurrentSession().createQuery("from Book where (title LIKE :value OR author LIKE :value)");
		q.setString("value", value + "%");
		//q.setString("isbn", book.getIsbn());
		ArrayList<Book> result = (ArrayList<Book>) q.list();
		StringBuilder html = new StringBuilder();
		return html.toString();
	}

}