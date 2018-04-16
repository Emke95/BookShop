package emma.store.entity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION,
proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

	private Map<Book, Integer> contents = new HashMap<>();

	public Map<Book, Integer> getContents() {
		return contents;
	}

	public void setContents(Map<Book, Integer> contents) {
		this.contents = contents;
	}

	public void addBook(Book book, int count) {

		if (contents.containsKey(book)) {
			contents.put(book, contents.get(book) + count);
		} 
		else {
			contents.put(book, count);
		}
	}

	public void removeBook(Book book) {
		contents.remove(book);
	}

	public void clearCart() {
		contents.clear();
	}

	public double getTotalCost() {
		double totalCost = 0;
		for (Book book : contents.keySet()) {
			totalCost = book.getPrice();
		}
		return totalCost;
	}
} 