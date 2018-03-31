package emma.store.entity;

import org.hibernate.validator.constraints.Email;
import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {

	public enum Role {
		USER, ADMIN
	}

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserId")
	private int userId;

	@Column(name = "FirstName", nullable=false)
	private String firstName;

	@Column(name = "LastName", nullable=false)
	private String lastName;

	@Column(name = "Email", nullable=false, unique= true)
	@Email
	private String email;

	@Column(name="Active")
	private boolean active;

	@Column(name="Password", nullable=false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private Role role = Role.USER;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public User() {

	}
	public User(User user) {
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.role = user.getRole();

	}
	
	public User(String firstName, String lastName, String email, String password, boolean active, Role role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.active = active;
		this.role = role;
	}
	
	public String toString() {
		return firstName + lastName + email + password + active + role;
	}

}