package entityBeans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class IPhoneNumber {
	private long id;
	private String phoneKind;
	private String phoneNumber;
	private IContact contact;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	
	public IContact getContact(){
		return contact;
	}
	public void setContact(IContact contact){
		this.contact = contact;
	}
}
