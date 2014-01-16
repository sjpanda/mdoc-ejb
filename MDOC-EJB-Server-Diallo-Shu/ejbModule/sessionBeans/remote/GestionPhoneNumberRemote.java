package sessionBeans.remote;

import java.util.List;

import javax.ejb.Remote;

import entityBeans.IPhoneNumber;

@Remote
public interface GestionPhoneNumberRemote {
	public IPhoneNumber instancePhoneNumber(String phoneKind, String phoneNumber);
	
	public List<IPhoneNumber> getPhoneNumbersByIdContact(long idContact);
	
	public boolean deletePhoneNumber(long id);
}
