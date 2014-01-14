package sessionBeans;

import java.util.List;

import entityBeans.IPhoneNumber;

public interface GestionPhoneNumberRemote {
	public IPhoneNumber instancePhoneNumber(String phoneKind, String phoneNumber);
	
	public List<IPhoneNumber> getPhoneNumbersByIdContact(long idContact);
	
	public boolean deletePhoneNumber(long id);
}
