package sessionBeans.remote;

import javax.ejb.Remote;

import entityBeans.IEntreprise;

@Remote
public interface GestionEntrepriseRemote {
	public IEntreprise getEntrepriseByIdContact(long idContact);
}
