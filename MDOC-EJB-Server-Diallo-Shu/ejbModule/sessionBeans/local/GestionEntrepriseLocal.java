package sessionBeans.local;

import javax.ejb.Local;

import entityBeans.impl.Entreprise;

@Local
public interface GestionEntrepriseLocal {
	public Entreprise getEntrepriseByIdContact(long idContact);
}
