package entityBeans.impl;

import java.io.Serializable;
import java.util.HashSet;
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
import javax.persistence.Version;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private int version;
	private String firstname;
	private String lastname;
	private String email;
	private Address address;
	private Set<PhoneNumber> profiles;
	private Set<ContactGroup> books;
	
	public Contact(){
		profiles = new HashSet<PhoneNumber>();
        books = new HashSet<ContactGroup>();
	}

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Version
	public int getVersion(){
		return version;
	}
	public void setVersion(int version){
		this.version = version;
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@OneToMany(mappedBy = "contact")
	public Set<PhoneNumber> getProfiles() {
		return profiles;
	}
	public void setProfiles(Set<PhoneNumber> profiles) {
		this.profiles = profiles;
	}
	
	@ManyToMany
	public Set<ContactGroup> getBooks() {
		return books;
	}
	public void setBooks(Set<ContactGroup> books) {
		this.books = books;
	}
}
