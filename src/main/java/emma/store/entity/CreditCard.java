package emma.store.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "CreditCard")
public class CreditCard {

	@Id
	@Column(name="CardId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String cardId;
	
	@Column(name="CardNumber", nullable = false)
	@Pattern(regexp = "[0-9]{16}")
	private String cardNumber;

	@Column(name="ccvNumber", nullable = false)
	@Pattern(regexp = "[0-9]{3}")
	private String ccvNumber;

	@Column(name="CardName", nullable = false)
	private String name;

	@Column(name="ExpiryDate", nullable = false)
	@Pattern(regexp = "[0-9]{2}/[0-9]{2}")
	private String expiryDate;

	public CreditCard() {
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCcvNumber() {
		return ccvNumber;
	}

	public void setCcvNumber(String ccvNumber) {
		this.ccvNumber = ccvNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
}