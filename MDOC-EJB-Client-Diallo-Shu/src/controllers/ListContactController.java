package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import sessionBeans.local.GestionContactLocal;
import entityBeans.IContact;

public class ListContactController {

	private List<IContact> contacts;
	private IContact contact;
	private String action;

	@EJB(name="ContactBeanEntity")
	private GestionContactLocal gestionContactLocal;

	public void init(ComponentSystemEvent event){		
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();

		if(params.get("action") != null){
			action = params.get("action");
		}

		contacts = new ArrayList<IContact>();
		contacts = gestionContactLocal.getAllContacts();
	}

	public String delete(){
		FacesContext contexte = FacesContext.getCurrentInstance();
		String id = contact.getId() + "";
		if(gestionContactLocal.deleteContact(id)){
			contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", null));
		} else {
			contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Incorrect", null));
		}
		return null;
	}

	public List<IContact> getContacts() {
		return contacts;
	}

	public void setContacts(List<IContact> contacts) {
		this.contacts = contacts;
	}


	public IContact getContact() {
		return contact;
	}

	public void setContact(IContact contact) {
		this.contact = contact;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}


}
