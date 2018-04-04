package emma.store.entity;

import javax.persistence.*;

@Entity
@Table(name = "ShippingAddress")
public class ShippingAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int shippingAddressId;

	@Column(name="HouseNo", nullable=false)
	private int houseNo;

	@Column(name="Street", nullable=false)
	private String street;

	@Column(name="Area", nullable=false)
	private String area;

	@Column(name="County", nullable=false)
	private String county;

	@Column(name="Country", nullable=false)
	private String country;
	
	@OneToOne(mappedBy = "shippingAddress")
	private User user;

	public int getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getShippingAddressId() {
		return shippingAddressId;
	}

	public void setShippingAddressId(int shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
	}

}