package sessionBeans.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entityBeans.Contact;
import entityBeans.ContactGroup;

import sessionBeans.GestionContactGroupRemote;
import sessionBeans.GestionContactRemote;

@Stateless(mappedName="ContactGroupBeanEntity")
public class GestionContactGroupBeanEntity implements GestionContactGroupRemote {

	@PersistenceContext
	EntityManager em;

	public boolean createContactGroup(String name , String idContact){		
		try{
			ContactGroup c = new ContactGroup();
			c.setGroupName(name);

			long idNum = Integer.parseInt(idContact);
			Contact contact = em.find(Contact.class, idNum);

			if(contact == null){
				System.out.println("Contact " + idContact + " not found");
				return false;
			}

			c.getContacts().add(contact);
			contact.getBooks().add(c);
			em.persist(c);
			System.out.println("Id Contact = " + idNum + " => " + contact.getId());

			return true;
		} catch(Exception e){
			System.err.println(e.getMessage());
			return false;
		}
	}

	public List<ContactGroup> getAllContactGroup(){
		try{
			List<ContactGroup> contactGroup = em.createQuery("select c from ContactGroup c ").getResultList();
			return contactGroup;
		} catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	public ContactGroup getContactGroupById(String id){
		try{
			long idNum = 0;
			try{
				idNum = Integer.parseInt(id);
			} catch (NumberFormatException e){
				e.printStackTrace();
				return null;
			}

			return em.find(ContactGroup.class, idNum);
		} catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}	
	}

	public boolean addContact(String[] contacts, String idContactGroup){		
		try{
			long idNum = Integer.parseInt(idContactGroup);
			ContactGroup c = em.find(ContactGroup.class, idNum);
			if(c == null){
				System.out.println("Group contact " + idContactGroup + " not found");
				return false;
			}

			GestionContactRemote gestionContactRemote = new GestionContactBeanEntity();

			if(contacts != null){
				for(String idContact : contacts){	
					long id = Integer.parseInt(idContact);
					Contact contact = em.find(Contact.class, id);
					if(contact == null){
						System.out.println("Cannot find the contact " + idContact);
						return false;
					}
					contact.getBooks().add(c);
					c.getContacts().add(contact);
					System.out.println("Dans le foreach DAOCOntactGroup methode addContact idContact = " + idContact);
				}
				System.out.println("Add contact termine !!!");
				em.persist(c);
			}
			return true;
		} catch(Exception e){
			System.out.println("========================================= Erreur DAOContactGroup AddContact ============================================");
			e.printStackTrace();
			return false;
		}	
	}

	public List<Contact> getContactsByIdContactGroup(final String idContactGroup){
		try{
			long id = Integer.parseInt(idContactGroup);
			ContactGroup contactGroup = em.find(ContactGroup.class, id);
			return new ArrayList<Contact>(contactGroup.getContacts());
		} catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}	
	}

	public boolean deleteContactGroup(ContactGroup contactGroup){		
		try{
			em.remove(contactGroup);
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
