package emma.store.entity;

import javax.persistence.*;


@Entity
@Table(name="UserShipping")
public class UserShipping {
	
	@Id @Column(name="UserShippingId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="uHouseNo", nullable=false)
	private int uHouseNo;

	@Column(name="uStreet", nullable=false)
	private String uStreet;

	@Column(name="uArea", nullable=false)
	private String uArea;

	@Column(name="aCounty", nullable=false)
	private String aCounty;

	@Column(name="aCountry", nullable=false)
	private String aCountry;
	
	@Column(name="DefaultShipping")
	private boolean userShippingDefault;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getuHouseNo() {
		return uHouseNo;
	}

	public void setuHouseNo(int uHouseNo) {
		this.uHouseNo = uHouseNo;
	}

	public String getuStreet() {
		return uStreet;
	}

	public void setuStreet(String uStreet) {
		this.uStreet = uStreet;
	}

	public String getuArea() {
		return uArea;
	}

	public void setuArea(String uArea) {
		this.uArea = uArea;
	}

	public String getaCounty() {
		return aCounty;
	}

	public void setaCounty(String aCounty) {
		this.aCounty = aCounty;
	}

	public String getaCountry() {
		return aCountry;
	}

	public void setaCountry(String aCountry) {
		this.aCountry = aCountry;
	}

	public boolean isUserShippingDefault() {
		return userShippingDefault;
	}

	public void setUserShippingDefault(boolean userShippingDefault) {
		this.userShippingDefault = userShippingDefault;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
