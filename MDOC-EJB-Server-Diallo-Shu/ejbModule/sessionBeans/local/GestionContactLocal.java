package sessionBeans.local;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import entityBeans.IAddress;
import entityBeans.IContact;
import entityBeans.IContactGroup;
import entityBeans.IPhoneNumber;

@Local
public interface GestionContactLocal {
	public boolean createContact(String fname, String lname, String email, IAddress address, Set<IPhoneNumber> profiles, int numSiret);

	public boolean updateContact(IContact c, String fname, String lname, String email, 
			String street, String zip, String city, String country, String home, String office, String mobile, int siretnum);

	public boolean deleteContact(String id);

	public List searchContact(String fname, String lname, String email, IAddress address,
			String home, String office, String mobile);

	public IContact instanceContact();
	
	public Object[] getContactById(String id);

	public List<IContact> getAllContacts();
	
	public List<IContactGroup> getContactGroupByIdContact(String idContact);
	
	public boolean generateContacts();
}
