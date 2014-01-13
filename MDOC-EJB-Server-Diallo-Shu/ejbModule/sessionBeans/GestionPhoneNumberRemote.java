package sessionBeans;

import java.util.List;

import entityBeans.PhoneNumber;

public interface GestionPhoneNumberRemote {
	public List<PhoneNumber> getPhoneNumbersByIdContact(long idContact);
	
	public boolean deletePhoneNumber(long id);
}
