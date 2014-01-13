package sessionBeans;

import javax.ejb.Remote;

import entityBeans.Entreprise;

@Remote
public interface GestionEntrepriseRemote {
	public Entreprise getEntrepriseByIdContact(long idContact);
}
