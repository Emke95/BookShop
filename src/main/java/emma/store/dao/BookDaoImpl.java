package emma.store.dao;

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
			book = this.findBook(isbn);
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

	public Book findBook(String isbn) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Book.class);
		crit.add(Restrictions.eq("isbn", isbn));
		return (Book) crit.uniqueResult();
	}


	public BookInfo findBookInfo(String isbn) {
		Book book = this.findBook(isbn);
		if (book == null) {
			return null;
		}
		return new BookInfo(book.getIsbn(), book.getTitle(), book.getAuthor(),book.getCategory(), book.getPrice(), book.getQuantity());
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

	public void delete(BookInfo bookInfo) {
		String isbn = bookInfo.getIsbn();
		
		Book book = null;
		if(isbn!=null) {
			book = this.findBook(isbn);
		}
		
		this.sessionFactory.getCurrentSession().delete(book);
	}


	/*public void save(Book book)
	{
		try
		{	
			Transaction transaction = this.sessionFactory.getCurrentSession().beginTransaction();

			String title = StringEscapeUtils.escapeHtml(book.getTitle());
			String author = StringEscapeUtils.escapeHtml(book.getAuthor());
			String category = StringEscapeUtils.escapeHtml(book.getCategory());
			double price = (book.getPrice());
			long quantity =(book.getQuantity());

			book.setTitle(title);
			book.setAuthor(author);
			book.setCategory(category);
			book.setPrice(price);
			book.setQuantity(quantity);

			this.sessionFactory.getCurrentSession().save(book);
			transaction.commit();

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			this.sessionFactory.getCurrentSession().close();
		}
	}*/

}