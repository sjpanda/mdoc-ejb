package sessionBeans.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sessionBeans.local.GestionEntrepriseLocal;
import sessionBeans.remote.GestionEntrepriseRemote;
import entityBeans.impl.Entreprise;

@Stateless(mappedName="EntrepriseBean")
public class GestionEntrepriseBean implements GestionEntrepriseLocal, GestionEntrepriseRemote{
	
	@PersistenceContext
	EntityManager em;
	
	public Entreprise getEntrepriseByIdContact(long idContact){
		
		Entreprise entreprise = null;
		try{
			entreprise = em.find(Entreprise.class, idContact);
		} catch(Exception e){
			e.printStackTrace();
		}
		return entreprise;
	}
}
