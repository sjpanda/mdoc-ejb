package sessionBeans.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sessionBeans.GestionAddressRemote;
import entityBeans.Address;

@Stateless(mappedName="AddressBeanEntity")
public class GestionAddressBeanEntity implements GestionAddressRemote {
	
	@PersistenceContext
	EntityManager em;
	
	public Address getAddressById(long id){	
		Address address = null;
		try{
			address = em.find(Address.class, id);
		} catch(Exception e){
			e.printStackTrace();
		}
		return address;
	}
}
