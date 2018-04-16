package emma.store.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import emma.store.entity.Book;

@Component
public class FileValidator implements Validator {
	
	public boolean supports(Class<?> clazz) {
		return clazz == Book.class;
	}
	public void validate(Object target, Errors errors) {

		Book bookInfo = (Book) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isbn", "NotEmpty.bookForm.isbn");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.bookForm.price");

		String isbn = bookInfo.getIsbn();
		double price=bookInfo.getPrice();

		if(price==0)
			errors.rejectValue("price","Zero.bookForm.price");

		if (isbn != null && isbn.length() > 0) {
			if (isbn.matches("\\s+")) {
				errors.rejectValue("isbn", "Pattern.bookForm.isbn");
			} 
		}
	}

}