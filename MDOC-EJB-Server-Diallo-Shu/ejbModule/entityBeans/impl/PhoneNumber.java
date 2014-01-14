package entityBeans.impl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import entityBeans.IContact;
import entityBeans.IPhoneNumber;

@Entity
public class PhoneNumber implements IPhoneNumber {
	private long id;
	private int version;
	private String phoneKind;
	private String phoneNumber;
	private IContact contact;
	
	public PhoneNumber(){}
	
	public PhoneNumber(String phoneKind, String phoneNumber){
		this.phoneKind = phoneKind;
		this.phoneNumber = phoneNumber;
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
	
	public String getPhoneKind() {
		return phoneKind;
	}
	public void setPhoneKind(String phoneKind) {
		this.phoneKind = phoneKind;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@ManyToOne
	public IContact getContact(){
		return contact;
	}
	public void setContact(IContact contact){
		this.contact = contact;
	}
}
