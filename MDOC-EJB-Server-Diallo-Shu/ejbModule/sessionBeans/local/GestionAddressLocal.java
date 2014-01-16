package sessionBeans.local;

import javax.ejb.Local;

import entityBeans.IAddress;

@Local
public interface GestionAddressLocal {
	public IAddress instanceAddress();
	
	public IAddress instanceAddress(String street, String city, String zip, String country);
	
	public IAddress getAddressById(long id);
}
