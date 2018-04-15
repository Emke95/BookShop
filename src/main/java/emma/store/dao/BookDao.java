package emma.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emma.store.entity.Book;

@Repository
public interface BookDao extends JpaRepository<Book, Long> {

	List<Book> findByTitleContaining(String title);
	List<Book> findByCategory(String category);
	List<Book> findByAuthorContaining(String author);
	Book findByIsbn(String isbn);

}
