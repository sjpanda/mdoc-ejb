package entityBeans;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contact{
	private long id;
	private String firstname;
	private String lastname;
	private String email;
	private Address address;
	private Set<PhoneNumber> profiles;
	private Set<ContactGroup> books;

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Set<PhoneNumber> getProfiles() {
		return profiles;
	}
	public void setProfiles(Set<PhoneNumber> profiles) {
		this.profiles = profiles;
	}
	
	public Set<ContactGroup> getBooks() {
		return books;
	}
	public void setBooks(Set<ContactGroup> books) {
		this.books = books;
	}
}
