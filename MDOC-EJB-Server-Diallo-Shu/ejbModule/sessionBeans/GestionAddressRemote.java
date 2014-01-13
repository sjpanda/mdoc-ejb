package sessionBeans;

import javax.ejb.Remote;

import entityBeans.Address;

@Remote
public interface GestionAddressRemote {
	public Address getAddressById(long id);
}
