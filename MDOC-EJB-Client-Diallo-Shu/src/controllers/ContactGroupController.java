package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import sessionBeans.remote.GestionContactGroupRemote;
import sessionBeans.remote.GestionContactRemote;
import entityBeans.impl.Contact;
import entityBeans.impl.ContactGroup;

public class ContactGroupController {

	private ContactGroup contactGroup;
	private Contact contact;
	private List<ContactGroup> contactGroups;
	private List<Contact> contacts;
	private Map<Long, Boolean> contactAdded;
	private String action;
	private String idContactGroup;

//	@EJB(name="ContactBeanEntity")
//	private GestionContactLocal gestionContactLocal;
//	@EJB(name="ContactGroupBeanEntity")
//	private GestionContactGroupLocal gestionContactGroupLocal;
	
	private GestionContactRemote gestionContact = null;
	private GestionContactGroupRemote gestionContactGroup = null;

	public void init(ComponentSystemEvent event){	
		initGestionBeans();
		
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();

		String idContact = params.get("idContact");
		String idContactGroup = params.get("idContactGroup");
		if(params.get("action") != null){
			action = params.get("action");
		}
		if(idContact != null){
			System.out.println("idContact = " + idContact);

//			Object[] result = gestionContact.getContactById(idContact);
//			contact = (Contact)result[0];
			
			contact = gestionContact.getContactById(idContact);
			
			contactGroups = new ArrayList<ContactGroup>(contact.getBooks());

		}
		if(idContactGroup != null){
			contacts = new ArrayList<Contact>();
			this.idContactGroup= idContactGroup;

			if(action.equals("create")){
				contactAdded = new HashMap<Long, Boolean>();
				contacts = getAllContactForAdd(idContactGroup);
				//a changer
				for(Contact c : contacts){
					contactAdded.put(c.getId(), false);
				}
			}
			else{
				contacts = gestionContactGroup.getContactsByIdContactGroup(idContactGroup);
			}

		}
		contactGroup = gestionContactGroup.instanceContactGroup();
	}

	public String save(){
		FacesContext contexte = FacesContext.getCurrentInstance();

		String groupContactName = contactGroup.getGroupName();
		String idContact = contact.getId()+"";

		if(gestionContactGroup.createContactGroup(groupContactName, idContact)){
			contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", null));
			MessageController.getCurrentMessage(false, "Contact group succesfully added", "Contact group has been added");			
		}
		else{
			contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Incorrect", null));
			MessageController.getCurrentMessage(true, "Failed to Add Contact group", "Failure when adding contact group");	
		}

//		Object[] result = gestionContact.getContactById(idContact);
//		contact = (Contact)result[0];
		
		contact = gestionContact.getContactById(idContact);
		
		contactGroups = new ArrayList<ContactGroup>(contact.getBooks());

		return null;
	}

	public String cancel(){
		contactGroup.setGroupName("");
		return null;

	}

	public String addContact(){
		ArrayList<String> listContact1 = new ArrayList<String>();
		FacesContext contexte = FacesContext.getCurrentInstance();
		for(Contact c : contacts){
			if(contactAdded.get(c.getId())){
				listContact1.add(c.getId()+"");
			}
		}
		String[] list = new String[listContact1.size()];
		for(int i = 0 ; i < listContact1.size() ; i++){
			list[i]= listContact1.get(i);
		}

		if(gestionContactGroup.addContact(list, idContactGroup)){
			contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", null));
			MessageController.getCurrentMessage(false, "Contacts succesfully added", "Contacts have been added");			
		}
		else{
			contexte.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Incorrect", null));
			MessageController.getCurrentMessage(false, "Failed to Add Contacts", "Failure when adding contacts");	
		}

		return null;
	}

	public ContactGroup getContactGroup() {
		return contactGroup;
	}

	public void setContactGroup(ContactGroup contactGroup) {
		this.contactGroup = contactGroup;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public List<ContactGroup> getContactGroups() {
		return contactGroups;
	}

	public void setContactGroups(List<ContactGroup> contactGroups) {
		this.contactGroups = contactGroups;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
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

		List contacts = gestionContact.getAllContacts();
		ContactGroup contactGroup = gestionContactGroup.getContactGroupById(idContactGroup);
		Set<Contact> contactsGroup = contactGroup.getContacts();

		boolean existe = false;
		for(int i = 0 ; i < contacts.size() ; i++){
			existe = false;
			for(Contact c : contactsGroup){
				if(c.getId() == ((Contact)contacts.get(i)).getId()){
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
	
	private void initGestionBeans(){
		InitialContext context;
		try {
			context = new InitialContext();
			gestionContact = (GestionContactRemote)context.lookup("ContactBeanEntity");
			gestionContactGroup = (GestionContactGroupRemote)context.lookup("ContactGroupBeanEntity");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
