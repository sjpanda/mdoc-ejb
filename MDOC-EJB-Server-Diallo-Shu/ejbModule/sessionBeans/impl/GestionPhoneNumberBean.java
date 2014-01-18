package sessionBeans.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sessionBeans.local.GestionPhoneNumberLocal;
import sessionBeans.remote.GestionPhoneNumberRemote;
import entityBeans.impl.PhoneNumber;

@Stateless(mappedName="PhoneNumberBean")
public class GestionPhoneNumberBean implements GestionPhoneNumberLocal, GestionPhoneNumberRemote {

	@PersistenceContext
	EntityManager em;
	
	public PhoneNumber instancePhoneNumber(String phoneKind, String phoneNumber){
		return new PhoneNumber(phoneKind, phoneNumber);
	}
	
	public List<PhoneNumber> getPhoneNumbersByIdContact(long idContact){
		try{
			List<PhoneNumber> contacts = em.createQuery("from PhoneNumber p where p.contact.id = " + idContact).getResultList();
			if(contacts.size() <= 0){
				return null;
			}
			return contacts;
		} catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public boolean deletePhoneNumber(long id){
		try{
			PhoneNumber p = em.find(PhoneNumber.class, id);
			em.remove(p);

			return true;
		} catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
}
