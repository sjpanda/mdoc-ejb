package models;

import javax.faces.event.ComponentSystemEvent;

public class Message {

	private String title;
	private String description;
	private Boolean display;
	private Boolean error;
	
	public Message(){
		title = "";
		description = "";
		display = false;
		error = false;
	}
	
	public void init(ComponentSystemEvent event){
		title = "";
		description = "";
		display = false;
		error = false;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getDisplay() {
		return display;
	}
	public void setDisplay(Boolean display) {
		this.display = display;
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}
	
}
