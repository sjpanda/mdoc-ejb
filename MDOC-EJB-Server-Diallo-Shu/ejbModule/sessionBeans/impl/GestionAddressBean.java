package sessionBeans.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sessionBeans.local.GestionAddressLocal;
import sessionBeans.remote.GestionAddressRemote;
import entityBeans.IAddress;
import entityBeans.impl.Address;

@Stateless(mappedName="AddressBeanEntity")
public class GestionAddressBean implements GestionAddressLocal, GestionAddressRemote {

	@PersistenceContext
	EntityManager em;

	public IAddress instanceAddress(){		
		return  new Address();
	}
	
	public IAddress instanceAddress(String street, String city, String zip, String country){		
		return  new Address(street, city, zip, country);
	}

	public IAddress getAddressById(long id){	
		IAddress address = null;
		try{
			address = em.find(IAddress.class, id);
		} catch(Exception e){
			e.printStackTrace();
		}
		return address;
	}
}
