package sessionBeans.local;

import java.util.List;

import javax.ejb.Local;

import entityBeans.impl.Contact;
import entityBeans.impl.ContactGroup;

@Local
public interface GestionContactGroupLocal {
	public boolean createContactGroup(String name , String idContact);
	
	public ContactGroup instanceContactGroup();

	public List<ContactGroup> getAllContactGroup();

	public ContactGroup getContactGroupById(String id);

	public boolean addContact(String[] contacts, String idContactGroup);

	public List<Contact> getContactsByIdContactGroup(String idContactGroup);
	
	public boolean deleteContactGroup(ContactGroup contactGroup);
}
