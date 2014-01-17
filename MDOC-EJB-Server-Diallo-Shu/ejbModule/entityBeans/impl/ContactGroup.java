package entityBeans.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

@Entity
public class ContactGroup implements Serializable {
	private static final long serialVersionUID = 1L;
	private long groupId;
	private int version;
	private String groupName;
	private Set<Contact> contacts;

	public ContactGroup(){
		contacts = new HashSet<Contact>();
	}
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	@Version
	public int getVersion(){
		return version;
	}
	public void setVersion(int version){
		this.version = version;
	}
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	@ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
	public Set<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}	
}
