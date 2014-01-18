package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import sessionBeans.remote.GestionContactRemote;
import entityBeans.impl.Contact;

public class ListContactController {

	private List<Contact> contacts;
	private Contact contact;
	private String action;

//	@EJB(name="ContactBeanEntity")
//	private GestionContactLocal gestionContactLocal;
	
	private GestionContactRemote gestionContact = null;

	public void init(ComponentSystemEvent event){		
		initGestionBeans();
		
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();

		if(params.get("action") != null){
			action = params.get("action");
		}

		contacts = new ArrayList<Contact>();
		contacts = gestionContact.getAllContacts();
	}

	public String delete(){
		FacesContext contexte = FacesContext.getCurrentInstance();
		String id = contact.getId() + "";
		if(gestionContact.deleteContact(id)){
			contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", null));
		} else {
			contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Incorrect", null));
		}
		return null;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}


	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	private void initGestionBeans(){
		InitialContext context;
		try {
			context = new InitialContext();
			gestionContact = (GestionContactRemote)context.lookup("ContactBean");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
