package sessionBeans;

import java.util.List;

import entityBeans.IPhoneNumber;

public interface GestionPhoneNumberRemote {
	public List<IPhoneNumber> getPhoneNumbersByIdContact(long idContact);
	
	public boolean deletePhoneNumber(long id);
}
