package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.catalina.core.ApplicationContext;

import sessionBeans.GestionContactRemote;

import entityBeans.IContact;

public class ListContactController {

	private List<IContact> contacts;
	private IContact contact;
	private String action;

	public void init(ComponentSystemEvent event){		
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();

		if(params.get("action") != null){
			action = params.get("action");
		}
		Context context;
		try {
			context = new InitialContext();
			GestionContactRemote gestionContactRemote = (GestionContactRemote)context.lookup("ContactBeanEntity");

			contacts = new ArrayList<IContact>();
			contacts = gestionContactRemote.getAllContacts();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public String delete(){
		FacesContext contexte = FacesContext.getCurrentInstance();
		Context context;
		try {
			context = new InitialContext();
			GestionContactRemote gestionContactRemote = (GestionContactRemote)context.lookup("ContactBeanEntity");
			String id = contact.getId() + "";
			if(gestionContactRemote.deleteContact(id)){
				contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", null));
			} else {
				contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Incorrect", null));
			}
		} catch (NamingException e) {
			e.printStackTrace();
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
