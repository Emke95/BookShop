package emma.store.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import emma.store.dao.BookDao;
import emma.store.entity.Book;
import emma.store.model.BookInfo;

@Component
public class FileValidator implements Validator {

	@Autowired 
	private BookDao bookDao;

	 public boolean supports(Class<?> clazz) {
	        return clazz == BookInfo.class;
	    }
	 public void validate(Object target, Errors errors) {
	    	
	        BookInfo bookInfo = (BookInfo) target;
	       
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isbn", "NotEmpty.bookForm.isbn");
	       // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.bookForm.name");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.bookForm.price");
	 
	        String isbn = bookInfo.getIsbn();
	        double price=bookInfo.getPrice();
	               
	        if(price==0)
	        	errors.rejectValue("price","Zero.bookForm.price");
	        
	        //if(bookInfo.getFileData()==null)
	        	//System.out.println(bookInfo.getFileData().toString());
	        	//errors.rejectValue("fileData", "NotEmpty.bookForm.img");
	        
	        if (isbn != null && isbn.length() > 0) {
	            if (isbn.matches("\\s+")) {
	                errors.rejectValue("isbn", "Pattern.bookForm.isbn");
	            } else if(bookInfo.isNewBook()) {
	                Book book = bookDao.findBookByIsbn(isbn);
	                if (book != null) {
	                    errors.rejectValue("isbn", "Duplicate.bookForm.isbn");
	                }
	            }
	        }
	    }
	 
	}