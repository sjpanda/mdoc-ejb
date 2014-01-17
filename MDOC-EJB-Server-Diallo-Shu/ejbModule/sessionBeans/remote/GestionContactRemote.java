package sessionBeans.remote;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import entityBeans.impl.Address;
import entityBeans.impl.Contact;
import entityBeans.impl.ContactGroup;
import entityBeans.impl.PhoneNumber;

@Remote
public interface GestionContactRemote {
	public boolean createContact(String fname, String lname, String email, Address address, Set<PhoneNumber> profiles, int numSiret);

	public boolean updateContact(Contact c, String fname, String lname, String email, 
			String street, String zip, String city, String country, String home, String office, String mobile, int siretnum);

	public boolean deleteContact(String id);

	public List searchContact(String fname, String lname, String email, Address address,
			String home, String office, String mobile);

	public Contact instanceContact();
	
	//public Object[] getContactById(String id);
	public Contact getContactById(String id);

	public List<Contact> getAllContacts();
	
	public List<ContactGroup> getContactGroupByIdContact(String idContact);
	
	public boolean generateContacts();
}
