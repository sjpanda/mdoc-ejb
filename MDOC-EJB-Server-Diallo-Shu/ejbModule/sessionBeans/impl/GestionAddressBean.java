package sessionBeans.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sessionBeans.local.GestionAddressLocal;
import sessionBeans.remote.GestionAddressRemote;
import entityBeans.impl.Address;

@Stateless(mappedName="AddressBean")
public class GestionAddressBean implements GestionAddressLocal, GestionAddressRemote {

	@PersistenceContext
	EntityManager em;

	public Address instanceAddress(){		
		return  new Address();
	}
	
	public Address instanceAddress(String street, String city, String zip, String country){		
		return  new Address(street, city, zip, country);
	}

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
