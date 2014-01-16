package sessionBeans.local;

import javax.ejb.Local;

import entityBeans.IEntreprise;

@Local
public interface GestionEntrepriseLocal {
	public IEntreprise getEntrepriseByIdContact(long idContact);
}
