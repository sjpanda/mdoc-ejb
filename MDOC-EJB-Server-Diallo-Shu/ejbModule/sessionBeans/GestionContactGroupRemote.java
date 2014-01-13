package sessionBeans;

import java.util.List;

import javax.ejb.Remote;

import entityBeans.Contact;
import entityBeans.ContactGroup;

@Remote
public interface GestionContactGroupRemote {
	public boolean createContactGroup(String name , String idContact);

	public List<ContactGroup> getAllContactGroup();

	public ContactGroup getContactGroupById(String id);

	public boolean addContact(String[] contacts, String idContactGroup);

	public List<Contact> getContactsByIdContactGroup(String idContactGroup);
	
	public boolean deleteContactGroup(ContactGroup contactGroup);
}
