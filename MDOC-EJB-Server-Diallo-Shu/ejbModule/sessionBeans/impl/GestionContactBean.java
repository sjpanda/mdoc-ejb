package sessionBeans.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sessionBeans.local.GestionContactLocal;
import sessionBeans.remote.GestionContactGroupRemote;
import sessionBeans.remote.GestionContactRemote;
import sessionBeans.remote.GestionPhoneNumberRemote;
import entityBeans.impl.Address;
import entityBeans.impl.Contact;
import entityBeans.impl.ContactGroup;
import entityBeans.impl.Entreprise;
import entityBeans.impl.PhoneNumber;

@Stateless(mappedName="ContactBeanEntity")
public class GestionContactBean implements GestionContactLocal, GestionContactRemote {

	@PersistenceContext
	EntityManager em;

	public boolean createContact(String fname, String lname, String email, Address address, Set<PhoneNumber> profiles, int numSiret){		
		try {
			Contact c;
			if(numSiret <= 0){
				c = new Contact();
			} else {
				c = new Entreprise();
				((Entreprise)c).setNumSiret(numSiret);
			}

			if(address != null){
				em.persist(address);
				c.setAddress(address);	
			}

			c.setFirstname(fname);
			c.setLastname(lname);
			c.setEmail(email);
			em.persist(c);

			if(profiles != null){
				for(PhoneNumber profile : profiles){
					profile.setContact(c);
					c.getProfiles().add(profile);
					em.persist(profile);
				}
			}

			return true;	
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateContact(Contact c, String fname, String lname, String email, 
			String street, String zip, String city, String country, String home, String office, String mobile, int siretnum){
		try{
			c.setFirstname(fname);
			c.setLastname(lname);
			c.setEmail(email);
			c.getAddress().setStreet(street);
			c.getAddress().setZip(zip);
			c.getAddress().setCity(city);
			c.getAddress().setCountry(country);
			checkAndAdd("home", home, c, c.getProfiles());
			checkAndAdd("office", office, c, c.getProfiles());
			checkAndAdd("mobile", mobile, c, c.getProfiles());

			if(siretnum == -1){
				if(c instanceof Entreprise){
					return false;
				}
			} else {
				if(c instanceof Entreprise){
					((Entreprise)c).setNumSiret(siretnum);
				} else {
					return false;
				}
			}

			em.persist(c);

			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteContact(String id){
		long idNum = 0;
		try{
			idNum = Integer.parseInt(id);
		} catch (NumberFormatException e){
			e.printStackTrace();
			return false;
		}

		try{
			Contact c = em.find(Contact.class, idNum);
			if(c == null){
				return false;
			}
			c.getProfiles().clear();

			GestionContactGroupRemote gestionContactGroupRemote = new GestionContactGroupBean();
			for(ContactGroup cg : c.getBooks()){
				cg.getContacts().remove(c);
				if(cg.getContacts().size() == 0){
					gestionContactGroupRemote.deleteContactGroup(cg);
				}
			}

			em.remove(c);

			return true;	
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public List searchContact(String fname, String lname, String email, Address address,
			String home, String office, String mobile){
		try{
			StringBuffer s = new StringBuffer();

			s.append("select c, a from Contact c, Address a where c.address = a ");

			if(! fname.isEmpty()){
				s.append("and c.firstname like '%" + fname + "%' ");
			}
			if(! lname.isEmpty()){
				s.append("and c.lastname like '%" + lname + "%' ");
			}
			if(! email.isEmpty()){
				s.append("and c.email like '%" + email + "%' ");
			}
			if(! address.getStreet().isEmpty()){
				s.append("and a.street like '%" + address.getStreet() + "%' ");
			}
			if(! address.getZip().isEmpty()){
				s.append("and a.zip like '%" + address.getZip() + "%' ");
			}
			if(! address.getCity().isEmpty()){
				s.append("and a.city like '%" + address.getCity() + "%' ");
			}
			if(! address.getCountry().isEmpty()){
				s.append("and a.country like '%" + address.getCountry() + "%' ");
			}
			System.out.println("Query : " + s.toString());

			Query query = em.createQuery(s.toString());
			List contacts = query.getResultList();

			if(home.isEmpty() && office.isEmpty() && mobile.isEmpty()){
				return contacts;
			}

			List toRemove = new ArrayList();

			GestionPhoneNumberRemote gestionPhoneNumberRemote = new GestionPhoneNumberBean();

			for(int i=0; i<contacts.size(); i++){
				Contact c = (Contact)(((Object[])contacts.get(i))[0]);

				List<PhoneNumber> pns = gestionPhoneNumberRemote.getPhoneNumbersByIdContact(c.getId());
				if((! keep("home", home, pns)) || (! keep("office", office, pns)) || (! keep("mobile", mobile, pns))){
					toRemove.add(c);
				}
			}

			contacts.removeAll(toRemove);
			return contacts;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public Contact instanceContact(){
		return new Contact();
	}

	public Object[] getContactById(String id){
		try{
			List contacts = em.createQuery("select c, a from Contact c, Address a where c.id = " + id + " and c.address= a").getResultList();
			if((contacts != null) && (contacts.size() != 0)){
				return (Object[]) contacts.get(0);
			}
			return null;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Contact> getAllContacts(){
		try{
			Query query = em.createQuery("from Contact c left join fetch c.address address");
			List<Contact> contacts = query.getResultList();
			return contacts;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<ContactGroup> getContactGroupByIdContact(String idContact){
		try{
			List<ContactGroup> contactGroup = em.createQuery("select elements(c.books) from Contact c where c.id = " + idContact).getResultList();

			return contactGroup;
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("================================ idContact = "+idContact+"======================================================");
			return null;
		}
	}

	public boolean generateContacts(){
		try{
			List<Contact> contacts = new ArrayList<Contact>();
			List<Address> addresses = new ArrayList<Address>();
			List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();

			for(int i=0; i<3; i++){
				contacts.add(new Contact());
				addresses.add(new Address());
				contacts.get(1).setAddress(addresses.get(1));
			}
			
			for(int i=1; i<=9; i++){
				phoneNumbers.add(new PhoneNumber());
			}

			contacts.get(0).setFirstname("Clayton");
			contacts.get(0).setLastname("Pettet");
			contacts.get(0).setEmail("clayton.pettet@msn.com");

			contacts.get(1).setFirstname("Future Life");
			contacts.get(1).setEmail("contact@futurelife.com");
			((Entreprise)contacts.get(1)).setNumSiret(999999999);
			
			contacts.get(2).setFirstname("Hello Mdoc");
			contacts.get(2).setEmail("contact@hellomdoc.com");
			((Entreprise)contacts.get(2)).setNumSiret(888888888);
			
			addresses.get(0).setStreet("5 Place Jussieu");
			addresses.get(0).setCity("Paris");
			addresses.get(0).setZip("75005");
			addresses.get(0).setCountry("France");
			
			addresses.get(1).setStreet("221B Baker Street");
			addresses.get(1).setCity("London");
			addresses.get(1).setZip("NW16XE");
			addresses.get(1).setCountry("England");
			
			addresses.get(2).setStreet("29 Main Street");
			addresses.get(2).setCity("New York");
			addresses.get(2).setZip("02138");
			addresses.get(2).setCountry("USA");
			
			phoneNumbers.get(0).setPhoneKind("home");
			phoneNumbers.get(0).setPhoneNumber("0189625412");
			
			phoneNumbers.get(1).setPhoneKind("office");
			phoneNumbers.get(1).setPhoneNumber("0165971256");
			
			phoneNumbers.get(2).setPhoneKind("mobile");
			phoneNumbers.get(2).setPhoneNumber("0624859715");
			
			phoneNumbers.get(3).setPhoneKind("home");
			phoneNumbers.get(3).setPhoneNumber("0289758038");
			
			phoneNumbers.get(4).setPhoneKind("office");
			phoneNumbers.get(4).setPhoneNumber("0245895924");
			
			phoneNumbers.get(5).setPhoneKind("mobile");
			phoneNumbers.get(5).setPhoneNumber("07489254728");
			
			phoneNumbers.get(6).setPhoneKind("home");
			phoneNumbers.get(6).setPhoneNumber("(914)246-8051");
			
			phoneNumbers.get(7).setPhoneKind("office");
			phoneNumbers.get(7).setPhoneNumber("(516)712-6528");
			
			phoneNumbers.get(8).setPhoneKind("mobile");
			phoneNumbers.get(8).setPhoneNumber("773-338-7786");
			
			for(int i=0; i<3; i++){
				em.persist(contacts.get(i));
			}
			
			for(int i=0; i<=2; i++){
				for(int j=0; j<3; j++){
					phoneNumbers.get(j+3*i).setContact(contacts.get(i));
					contacts.get(i).getProfiles().add(phoneNumbers.get(j+3*i));
					em.persist(phoneNumbers.get(j+3*i));
				}
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private void checkAndAdd(String kind, String number, Contact contact, Set<PhoneNumber> profiles){
		if(number.equals("")){
			for(PhoneNumber p : profiles){
				if(p.getPhoneKind().equalsIgnoreCase(kind)){
					em.remove(p);
					break;
				}
			}
		} else {
			boolean add = true;
			for(PhoneNumber p : profiles){
				if(p.getPhoneKind().equalsIgnoreCase(kind)){
					add = false;
					p.setPhoneNumber(number);
				}
			}
			if(add){
				PhoneNumber p = new PhoneNumber();
				p.setPhoneKind(kind);
				p.setPhoneNumber(number);
				p.setContact(contact);
				em.persist(p);
				profiles.add(p);
			}
		}
	}

	private boolean keep(String kind, String number, List<PhoneNumber> phoneNumbers){
		if(number.isEmpty()){
			return true;
		}
		if((! number.isEmpty()) && (phoneNumbers == null)){
			return false;
		}
		for(Object o : phoneNumbers){
			PhoneNumber p = (PhoneNumber) o;
			if(p.getPhoneKind().equalsIgnoreCase(kind) && 
					(p.getPhoneNumber().equalsIgnoreCase(number) || (p.getPhoneNumber().contains(number)))){
				return true;
			}
		}
		return false;
	}
}
