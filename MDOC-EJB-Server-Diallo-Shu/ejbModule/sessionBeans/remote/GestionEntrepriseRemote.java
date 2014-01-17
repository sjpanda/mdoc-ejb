package sessionBeans.remote;

import javax.ejb.Remote;

import entityBeans.impl.Entreprise;

@Remote
public interface GestionEntrepriseRemote {
	public Entreprise getEntrepriseByIdContact(long idContact);
}
