package sessionBeans.local;

import javax.ejb.Local;

import entityBeans.impl.Address;

@Local
public interface GestionAddressLocal {
	public Address instanceAddress();
	
	public Address instanceAddress(String street, String city, String zip, String country);
	
	public Address getAddressById(long id);
}
