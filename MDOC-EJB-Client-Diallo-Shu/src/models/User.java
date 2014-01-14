package models;

public class User {
	private String name;
	private String password;
	private Boolean connected;

	public User(){
		connected = false;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getConnected() {
		return connected;
	}

	public void setConnected(Boolean connected) {
		this.connected = connected;
	}

	public String login(){
		// Image here a database access to validate the users
		if (name.equalsIgnoreCase(password)){
			connected = true;
			return "success";
		} else {
			name = "";
			password = "";
			connected = false;
			return "failed";
		}
	}
	
	public String logout(){
		name = "";
		password = "";
		connected = false;
		return "success";
	}

}
