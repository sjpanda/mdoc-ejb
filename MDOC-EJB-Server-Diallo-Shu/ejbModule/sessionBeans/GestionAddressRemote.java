package sessionBeans;

import javax.ejb.Remote;

import entityBeans.IAddress;

@Remote
public interface GestionAddressRemote {
	public IAddress instanceAddress();
	
	public IAddress instanceAddress(String street, String city, String zip, String country);
	
	public IAddress getAddressById(long id);
}
