package sessionBeans.remote;

import java.util.List;

import javax.ejb.Remote;

import entityBeans.impl.Contact;
import entityBeans.impl.ContactGroup;

@Remote
public interface GestionContactGroupRemote {
	public boolean createContactGroup(String name , String idContact);
	
	public ContactGroup instanceContactGroup();

	public List<ContactGroup> getAllContactGroup();

	public ContactGroup getContactGroupById(String id);

	public boolean addContact(String[] contacts, String idContactGroup);

	public List<Contact> getContactsByIdContactGroup(String idContactGroup);
	
	public boolean deleteContactGroup(ContactGroup contactGroup);
}
