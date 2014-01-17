package sessionBeans.local;

import java.util.List;

import javax.ejb.Local;

import entityBeans.impl.PhoneNumber;

@Local
public interface GestionPhoneNumberLocal {
	public PhoneNumber instancePhoneNumber(String phoneKind, String phoneNumber);
	
	public List<PhoneNumber> getPhoneNumbersByIdContact(long idContact);
	
	public boolean deletePhoneNumber(long id);
}
