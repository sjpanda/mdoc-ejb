package sessionBeans.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sessionBeans.GestionEntrepriseRemote;
import entityBeans.IEntreprise;

@Stateless(mappedName="EntrepriseBeanEntity")
public class GestionEntrepriseBeanEntity implements GestionEntrepriseRemote{
	
	@PersistenceContext
	EntityManager em;
	
	public IEntreprise getEntrepriseByIdContact(long idContact){
		
		IEntreprise entreprise = null;
		try{
			entreprise = em.find(IEntreprise.class, idContact);
		} catch(Exception e){
			e.printStackTrace();
		}
		return entreprise;
	}
}
