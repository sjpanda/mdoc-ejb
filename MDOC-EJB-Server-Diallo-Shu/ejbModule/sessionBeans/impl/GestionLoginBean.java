package sessionBeans.impl;

import javax.ejb.Remove;
import javax.ejb.Stateful;

import sessionBeans.local.GestionLoginLocal;
import sessionBeans.remote.GestionLoginRemote;

@Stateful(mappedName="LoginBean")
public class GestionLoginBean implements GestionLoginLocal, GestionLoginRemote {
	private String name = "";
	private String password = "";
	private Boolean connected = false;

	public boolean logIn(){
		if (name.equalsIgnoreCase(password)){
			this.name = name;
			this.password = password;
			connected = true;
			return true;
		}
		return false;
	}

	public void logOut(){
		destroyBean();
		this.name = "";
		this.password = "";
		connected = false;
	}
	
	@Remove
	public void destroyBean(){
		System.out.println("See you " + name);
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
}
