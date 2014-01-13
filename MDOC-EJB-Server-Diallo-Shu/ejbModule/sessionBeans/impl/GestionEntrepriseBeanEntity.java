package sessionBeans.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sessionBeans.GestionEntrepriseRemote;
import entityBeans.Entreprise;

@Stateless(mappedName="EntrepriseBeanEntity")
public class GestionEntrepriseBeanEntity implements GestionEntrepriseRemote{
	
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
