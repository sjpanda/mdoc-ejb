package controllers;

import java.util.Map;

import javax.faces.context.FacesContext;

import models.Message;

public class MessageController {

	public static Message getCurrentMessage(Boolean error,String title , String description){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, Object> sessionMap = fc.getExternalContext().getSessionMap();
		Message message =  (Message) sessionMap.get("message");
		message.setDisplay(true);
		message.setError(error);
		message.setTitle(title);
		message.setDescription(description);

		return message;
	}

	public static void noDisplayMessage(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, Object> sessionMap = fc.getExternalContext().getSessionMap();
		Message message =  (Message) sessionMap.get("message");

	}
}
