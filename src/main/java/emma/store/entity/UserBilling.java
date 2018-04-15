package emma.store.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="UserBilling")
public class UserBilling {

	@Id @Column(name="UserBillingId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(cascade=CascadeType.ALL)
	private UserPayment userPayment;
	

	public UserPayment getUserPayment() {
		return userPayment;
	}

	public void setUserPayment(UserPayment userPayment) {
		this.userPayment = userPayment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}