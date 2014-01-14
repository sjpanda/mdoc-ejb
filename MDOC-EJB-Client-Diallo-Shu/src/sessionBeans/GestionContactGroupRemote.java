package sessionBeans;

import java.util.List;

import javax.ejb.Remote;

import entityBeans.IContact;
import entityBeans.IContactGroup;

@Remote
public interface GestionContactGroupRemote {
	public boolean createContactGroup(String name , String idContact);

	public List<IContactGroup> getAllContactGroup();

	public IContactGroup getContactGroupById(String id);

	public boolean addContact(String[] contacts, String idContactGroup);

	public List<IContact> getContactsByIdContactGroup(String idContactGroup);
	
	public boolean deleteContactGroup(IContactGroup contactGroup);
}
