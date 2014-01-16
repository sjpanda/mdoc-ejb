package sessionBeans.local;

import java.util.List;

import javax.ejb.Local;

import entityBeans.IContact;
import entityBeans.IContactGroup;

@Local
public interface GestionContactGroupLocal {
	public boolean createContactGroup(String name , String idContact);
	
	public IContactGroup instanceContactGroup();

	public List<IContactGroup> getAllContactGroup();

	public IContactGroup getContactGroupById(String id);

	public boolean addContact(String[] contacts, String idContactGroup);

	public List<IContact> getContactsByIdContactGroup(String idContactGroup);
	
	public boolean deleteContactGroup(IContactGroup contactGroup);
}
