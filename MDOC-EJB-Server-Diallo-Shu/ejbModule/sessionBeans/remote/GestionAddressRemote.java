package sessionBeans.remote;

import javax.ejb.Remote;

import entityBeans.impl.Address;

@Remote
public interface GestionAddressRemote {
	public Address instanceAddress();
	
	public Address instanceAddress(String street, String city, String zip, String country);
	
	public Address getAddressById(long id);
}
