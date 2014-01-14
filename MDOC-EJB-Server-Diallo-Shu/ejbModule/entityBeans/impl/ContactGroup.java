package entityBeans.impl;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

import entityBeans.IContact;
import entityBeans.IContactGroup;

@Entity
public class ContactGroup implements IContactGroup {
	private long groupId;
	private int version;
	private String groupName;
	private Set<IContact> contacts;

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
	
	@ManyToMany(mappedBy = "books")
	public Set<IContact> getContacts() {
		return contacts;
	}
	public void setContacts(Set<IContact> contacts) {
		this.contacts = contacts;
	}	
}
