package emma.store.entity;

import javax.persistence.*;
@Entity
@Table(name="UserPayment")
public class UserPayment {
	
	@Id @Column(name="UserPaymentId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="Type")
	private String type;

	@Column(name="CardNumber")
	private String cardNumber;
	
	@Column(name="ExpiryMonth")
	private int expiryMonth;
	
	@Column(name="ExpiryYear")
	private int expiryYear;
	
	@Column(name="CVC")
	private int cvc;
	
	@Column(name="HolderName")
	private String holderName;
	
	private boolean defaultPayment;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(int expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public int getExpiryYear() {
		return expiryYear;
	}

	public void setExpiryYear(int expiryYear) {
		this.expiryYear = expiryYear;
	}

	public int getCvc() {
		return cvc;
	}

	public void setCvc(int cvc) {
		this.cvc = cvc;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public boolean isDefaultPayment() {
		return defaultPayment;
	}

	public void setDefaultPayment(boolean defaultPayment) {
		this.defaultPayment = defaultPayment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
