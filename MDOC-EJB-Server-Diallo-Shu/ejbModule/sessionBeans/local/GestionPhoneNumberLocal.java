package sessionBeans.local;

import java.util.List;

import javax.ejb.Local;

import entityBeans.IPhoneNumber;

@Local
public interface GestionPhoneNumberLocal {
	public IPhoneNumber instancePhoneNumber(String phoneKind, String phoneNumber);
	
	public List<IPhoneNumber> getPhoneNumbersByIdContact(long idContact);
	
	public boolean deletePhoneNumber(long id);
}
