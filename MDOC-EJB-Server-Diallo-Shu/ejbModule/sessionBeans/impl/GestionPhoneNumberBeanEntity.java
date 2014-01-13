package sessionBeans.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entityBeans.PhoneNumber;

import sessionBeans.GestionPhoneNumberRemote;

@Stateless(mappedName="PhoneNumberBeanEntity")
public class GestionPhoneNumberBeanEntity implements GestionPhoneNumberRemote {

	@PersistenceContext
	EntityManager em;
	
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
