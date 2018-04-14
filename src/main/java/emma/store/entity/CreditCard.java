package emma.store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "CreditCard")
public class CreditCard {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CardId")
	private int cardId;

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	
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

}