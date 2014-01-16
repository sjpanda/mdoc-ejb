package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import sessionBeans.local.GestionContactGroupLocal;
import sessionBeans.local.GestionContactLocal;
import entityBeans.IContact;
import entityBeans.IContactGroup;

public class ContactGroupController {

	private IContactGroup contactGroup;
	private IContact contact;
	private List<IContactGroup> contactGroups;
	private List<IContact> contacts;
	private Map<Long, Boolean> contactAdded;
	private String action;
	private String idContactGroup;

	@EJB(name="ContactBeanEntity")
	private GestionContactLocal gestionContactLocal;
	@EJB(name="ContactGroupBeanEntity")
	private GestionContactGroupLocal gestionContactGroupLocal;

	public void init(ComponentSystemEvent event){		
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();

		String idContact = params.get("idContact");
		String idContactGroup = params.get("idContactGroup");
		if(params.get("action") != null){
			action = params.get("action");
		}
		if(idContact != null){
			System.out.println("idContact = " + idContact);

			Object[] result = gestionContactLocal.getContactById(idContact);
			contact = (IContact)result[0];
			contactGroups = new ArrayList<IContactGroup>(contact.getBooks());

		}
		if(idContactGroup != null){
			contacts = new ArrayList<IContact>();
			this.idContactGroup= idContactGroup;

			if(action.equals("create")){
				contactAdded = new HashMap<Long, Boolean>();
				contacts = getAllContactForAdd(idContactGroup);
				//a changer
				for(IContact c : contacts){
					contactAdded.put(c.getId(), false);
				}
			}
			else{
				contacts = gestionContactGroupLocal.getContactsByIdContactGroup(idContactGroup);
			}

		}
		contactGroup = gestionContactGroupLocal.instanceContactGroup();
	}

	public String save(){
		FacesContext contexte = FacesContext.getCurrentInstance();

		String groupContactName = contactGroup.getGroupName();
		String idContact = contact.getId()+"";

		if(gestionContactGroupLocal.createContactGroup(groupContactName, idContact)){
			contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", null));
			MessageController.getCurrentMessage(false, "Contact group succesfully added", "Contact group has been added");			
		}
		else{
			contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Incorrect", null));
			MessageController.getCurrentMessage(false, "Failed to Add Contact group", "Failure when adding contact group");	
		}

		Object[] result = gestionContactLocal.getContactById(idContact);
		contact = (IContact)result[0];
		contactGroups = new ArrayList<IContactGroup>(contact.getBooks());

		return null;
	}

	public String cancel(){
		contactGroup.setGroupName("");
		return null;

	}

	public String addContact(){
		ArrayList<String> listContact1 = new ArrayList<String>();
		FacesContext contexte = FacesContext.getCurrentInstance();
		for(IContact c : contacts){
			if(contactAdded.get(c.getId())){
				listContact1.add(c.getId()+"");
			}
		}
		String[] list = new String[listContact1.size()];
		for(int i = 0 ; i < listContact1.size() ; i++){
			list[i]= listContact1.get(i);
		}

		if(gestionContactGroupLocal.addContact(list, idContactGroup)){
			contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", null));
			MessageController.getCurrentMessage(false, "Contacts succesfully added", "Contacts have been added");			
		}
		else{
			contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Incorrect", null));
			MessageController.getCurrentMessage(false, "Failed to Add Contacts", "Failure when adding contacts");	
		}

		return null;
	}

	public IContactGroup getContactGroup() {
		return contactGroup;
	}

	public void setContactGroup(IContactGroup contactGroup) {
		this.contactGroup = contactGroup;
	}

	public IContact getContact() {
		return contact;
	}

	public void setContact(IContact contact) {
		this.contact = contact;
	}

	public List<IContactGroup> getContactGroups() {
		return contactGroups;
	}

	public void setContactGroups(List<IContactGroup> contactGroups) {
		this.contactGroups = contactGroups;
	}

	public List<IContact> getContacts() {
		return contacts;
	}

	public void setContacts(List<IContact> contacts) {
		this.contacts = contacts;
	}

	public Map<Long, Boolean> getContactAdded() {
		return contactAdded;
	}

	public void setContactAdded(Map<Long, Boolean> contactAdded) {
		this.contactAdded = contactAdded;
	}

	public String getIdContactGroup() {
		return idContactGroup;
	}

	public void setIdContactGroup(String idContactGroup) {
		this.idContactGroup = idContactGroup;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	private List getAllContactForAdd(String idContactGroup){
		List contactsUnique = new ArrayList();

		List contacts = gestionContactLocal.getAllContacts();
		IContactGroup contactGroup = gestionContactGroupLocal.getContactGroupById(idContactGroup);
		Set<IContact> contactsGroup = contactGroup.getContacts();

		boolean existe = false;
		for(int i = 0 ; i < contacts.size() ; i++){
			existe = false;
			for(IContact c : contactsGroup){
				if(c.getId() == ((IContact)contacts.get(i)).getId()){
					existe = true;
					break;
				}
			}
			if(!existe){
				contactsUnique.add(contacts.get(i));
			}
		}

		return contactsUnique;
	}

}
