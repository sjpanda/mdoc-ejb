package sessionBeans.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entityBeans.IPhoneNumber;

import sessionBeans.GestionPhoneNumberRemote;

@Stateless(mappedName="PhoneNumberBeanEntity")
public class GestionPhoneNumberBeanEntity implements GestionPhoneNumberRemote {

	@PersistenceContext
	EntityManager em;
	
	public List<IPhoneNumber> getPhoneNumbersByIdContact(long idContact){
		try{
			List<IPhoneNumber> contacts = em.createQuery("from PhoneNumber p where p.contact.id = " + idContact).getResultList();
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
			IPhoneNumber p = em.find(IPhoneNumber.class, id);
			em.remove(p);

			return true;
		} catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
}
