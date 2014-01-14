package sessionBeans;

import javax.ejb.Remote;

import entityBeans.IAddress;

@Remote
public interface GestionAddressRemote {
	public IAddress getAddressById(long id);
}
