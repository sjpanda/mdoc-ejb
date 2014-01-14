package entityBeans.impl;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import entityBeans.IAddress;
import entityBeans.IContact;
import entityBeans.IContactGroup;
import entityBeans.IPhoneNumber;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Contact implements IContact {
	private long id;
	private String firstname;
	private String lastname;
	private String email;
	private IAddress address;
	private Set<IPhoneNumber> profiles;
	private Set<IContactGroup> books;

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
	
	@OneToOne
	public IAddress getAddress() {
		return address;
	}
	public void setAddress(IAddress address) {
		this.address = address;
	}
	
	@OneToMany(mappedBy = "contact")
	public Set<IPhoneNumber> getProfiles() {
		return profiles;
	}
	public void setProfiles(Set<IPhoneNumber> profiles) {
		this.profiles = profiles;
	}
	
	@ManyToMany
	public Set<IContactGroup> getBooks() {
		return books;
	}
	public void setBooks(Set<IContactGroup> books) {
		this.books = books;
	}
}
