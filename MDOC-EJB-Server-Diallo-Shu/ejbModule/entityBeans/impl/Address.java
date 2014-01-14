package entityBeans.impl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import entityBeans.IAddress;

@Entity
public class Address implements IAddress {
	private long id;
	private int version;
	private String street;
	private String city;
	private String zip;
	private String country;
	
	public Address(){}
	
	public Address(String street, String city, String zip, String country){
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.country = country;
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
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

}
