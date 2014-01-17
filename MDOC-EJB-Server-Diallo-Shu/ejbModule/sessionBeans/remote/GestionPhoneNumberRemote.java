package sessionBeans.remote;

import java.util.List;

import javax.ejb.Remote;

import entityBeans.impl.PhoneNumber;

@Remote
public interface GestionPhoneNumberRemote {
	public PhoneNumber instancePhoneNumber(String phoneKind, String phoneNumber);
	
	public List<PhoneNumber> getPhoneNumbersByIdContact(long idContact);
	
	public boolean deletePhoneNumber(long id);
}
