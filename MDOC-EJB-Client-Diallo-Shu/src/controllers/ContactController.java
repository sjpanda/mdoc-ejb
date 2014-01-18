package controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import sessionBeans.remote.GestionAddressRemote;
import sessionBeans.remote.GestionContactRemote;
import sessionBeans.remote.GestionPhoneNumberRemote;
import entityBeans.impl.Address;
import entityBeans.impl.Contact;
import entityBeans.impl.Entreprise;
import entityBeans.impl.PhoneNumber;

public class ContactController {

	private String numSiret;
	private Contact contact;
	private String HomePhone;
	private String mobilePhone;
	private String officePhone;
	private String action;
	private List<Contact> resultSearch;
	private int versionContact;

//	@EJB(name="ContactBeanEntity")
//	private GestionContactLocal gestionContact;
//	@EJB(name="AddressBeanEntity")
//	private GestionAddressLocal gestionAddress;
//	@EJB(name="PhoneNumberBeanEntity")
//	private GestionPhoneNumberLocal gestionPhoneNumber;

	private GestionContactRemote gestionContact = null;
	private GestionAddressRemote gestionAddress = null;
	private GestionPhoneNumberRemote gestionPhoneNumber = null;

	public ContactController(){
	}

	public void init(ComponentSystemEvent event){	
		initGestionBeans();

		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		numSiret = "";
		if(params.get("action") != null){
			action = params.get("action");
		}

		if(action.equals("create") || action.equals("search")){
			contact = gestionContact.instanceContact();
			contact.setAddress(gestionAddress.instanceAddress());
			HomePhone = "";mobilePhone="";officePhone="";
		}
		else if(action.equals("update")){
			String idContact = params.get("idContact");
			if((idContact == null) || (idContact.isEmpty())){
				return;
			}

//			Object[] result = gestionContact.getContactById(idContact);
//			contact = (Contact)result[0];
			
			contact = getContactById(idContact);
						
			if(contact instanceof Entreprise){
				numSiret = ((Entreprise)contact).getNumSiret() + "";
			}
			if (contact.getProfiles() != null) {
				for (PhoneNumber phoneNumber : contact.getProfiles()) {
					if (phoneNumber.getPhoneKind().equalsIgnoreCase("home")) {
						HomePhone = phoneNumber.getPhoneNumber();
					}
					if (phoneNumber.getPhoneKind().equalsIgnoreCase(
							"office")) {
						officePhone = phoneNumber.getPhoneNumber();
					}
					if (phoneNumber.getPhoneKind().equalsIgnoreCase(
							"mobile")) {
						mobilePhone = phoneNumber.getPhoneNumber();
					}
				}
			}
		}
	}

	public String save(){
		FacesContext contexte = FacesContext.getCurrentInstance();

		int numberSiret = -1;
		if(! numSiret.isEmpty()){
			try{
				numberSiret = Integer.parseInt(numSiret);
			} catch(NumberFormatException e) {
				contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Incorrect", null));
				MessageController.getCurrentMessage(true, "Failed to create contact", "Failure when creating contact");
				return null;
			}
		}

		String fname = contact.getFirstname();
		String lname = contact.getLastname();
		String email = contact.getEmail();

		String street = contact.getAddress().getStreet();
		String zip = contact.getAddress().getZip();
		String city = contact.getAddress().getCity();
		String country = contact.getAddress().getCountry();

		Address address;
		if(street.isEmpty() && zip.isEmpty() && city.isEmpty() && country.isEmpty()){
			address = null;
		} else {
			address = gestionAddress.instanceAddress(street, city, zip, country);
		}

		String homepn = getHomePhone();
		String officepn = getOfficePhone();
		String mobilepn = getMobilePhone();

		Set<PhoneNumber> profiles;
		if(homepn.isEmpty() && officepn.isEmpty() && mobilepn.isEmpty()){
			profiles = null;
		} else {
			profiles = new HashSet<PhoneNumber>();
			if(! homepn.isEmpty()){
				PhoneNumber home = gestionPhoneNumber.instancePhoneNumber("home", homepn);
				profiles.add(home);
			}
			if(! officepn.isEmpty()){
				PhoneNumber office = gestionPhoneNumber.instancePhoneNumber("office", officepn);
				profiles.add(office);
			}
			if(! mobilepn.isEmpty()){
				PhoneNumber mobile = gestionPhoneNumber.instancePhoneNumber("mobile", mobilepn);
				profiles.add(mobile);
			}
		}

		if(action.equals("create")){
			if(gestionContact.createContact(fname, lname, email, address, profiles, numberSiret)){
				contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", null));
				MessageController.getCurrentMessage(false, "Contact has been created", "Contact succesfully created");
			} else {
				contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Incorrect", null));
				MessageController.getCurrentMessage(true, "Failed to create contact", "Failure when creating contact");
			}
		}
		else if(action.equals("update")){
			//if(versionContact == contact.getVersion()){
				if(gestionContact.updateContact(contact.getId(), fname, lname, email, street, zip, city, country, homepn, officepn, mobilepn, numberSiret)){
					contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", null));
					MessageController.getCurrentMessage(false, "Contact succesfully updated", "Contact has been updated");
				} else {
					contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Incorrect", null));
					MessageController.getCurrentMessage(true, "Failed to update contact", "Failure when updating contact");
				}
//			}else{
//				contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Incorrect", null));
//				MessageController.getCurrentMessage(true, "Failed to update contact", "Contact is modified in the meantime, please reload the page to continue the modification");
//			}
		}

		return null;
	}

	public String generateContacts(){
		FacesContext contexte = FacesContext.getCurrentInstance();
		if(gestionContact == null){
			initGestionBeans();
		}
		if(gestionContact.generateContacts()){
			contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", null));
			MessageController.getCurrentMessage(false, "Generate contact : Contacts succesfully added", "Contact has been added");			
		}
		else{
			contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect", null));
			MessageController.getCurrentMessage(true, "Generate contact", "Failed to generate contacts");	
		}

		return null;
	}

	public String search(){
		FacesContext contexte = FacesContext.getCurrentInstance();
		String fname = contact.getFirstname();
		String lname = contact.getLastname();
		String email = contact.getEmail();

		String street = contact.getAddress().getStreet();
		String zip = contact.getAddress().getZip();
		String city = contact.getAddress().getCity();
		String country = contact.getAddress().getCountry();

		Address address = gestionAddress.instanceAddress(street, city, zip, country);

		String homepn = getHomePhone();
		String officepn = getOfficePhone();
		String mobilepn = getMobilePhone();


		resultSearch = gestionContact.searchContact(fname, lname, email, address, homepn, officepn, mobilepn);
		System.out.println("listResultat = " + resultSearch.size());
		if(!resultSearch.isEmpty()){
			return "result";
		}
		else{
			contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Incorrect", null));
			MessageController.getCurrentMessage(true, "Search contact", "The search result is empty");
		}

		return null;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<Contact> getResultSearch() {
		return resultSearch;
	}

	public void setResultSearch(List<Contact> resultSearch) {
		this.resultSearch = resultSearch;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}


	public String getHomePhone() {
		return HomePhone;
	}

	public void setHomePhone(String homePhone) {
		HomePhone = homePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getNumSiret() {
		if(contact instanceof Entreprise){
			return ((Entreprise)contact).getNumSiret() + "";
		}
		
		return numSiret;
	}

	public void setNumSiret(String numSiret) {
		this.numSiret = numSiret;
	}


	private void initGestionBeans(){
		InitialContext context;
		try {
			context = new InitialContext();
			gestionContact = (GestionContactRemote)context.lookup("ContactBean");
			gestionAddress = (GestionAddressRemote)context.lookup("AddressBean");
			gestionPhoneNumber = (GestionPhoneNumberRemote)context.lookup("PhoneNumberBean");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private Contact getContactById(String id){
		Contact contact = gestionContact.getContactById(id);
		if(contact.getAddress() == null){
			contact.setAddress(gestionAddress.instanceAddress());
		}
		return contact;
	}
}
