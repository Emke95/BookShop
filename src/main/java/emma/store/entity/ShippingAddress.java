package emma.store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ShippingAddress")
public class ShippingAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ShippingAddressId")
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

	public int getShippingAddressId() {
		return shippingAddressId;
	}

	public void setShippingAddressId(int shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
	}

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
}