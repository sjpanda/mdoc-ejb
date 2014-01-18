package models;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import sessionBeans.remote.GestionLoginRemote;

public class User {
//	private String name;
//	private String password;
//	private Boolean connected;

	private GestionLoginRemote gestionLogin = null;

	public User(){
//		connected = false;
		initGestionBeans();
	}

	public String getName() {
		initGestionBeans();
		return gestionLogin.getName();
	}
	public void setName(String name) {
		initGestionBeans();
		gestionLogin.setName(name);
	}
	public String getPassword() {
		initGestionBeans();
		return gestionLogin.getPassword();
	}
	public void setPassword(String password) {
		initGestionBeans();
		gestionLogin.setPassword(password);
	}

	public Boolean getConnected() {
		initGestionBeans();
		return gestionLogin.getConnected();
	}

	public void setConnected(Boolean connected) {
		initGestionBeans();
		gestionLogin.setConnected(connected);
	}

	public String login(){
		initGestionBeans();
		//		if (name.equalsIgnoreCase(password)){
		//			connected = true;
		//			return "success";
		//		} else {
		//			name = "";
		//			password = "";
		//			connected = false;
		//			return "failed";
		//		}

		if (gestionLogin.logIn()){
			setConnected(true);
			return "success";
		} else {
			setName("");
			setPassword("");
			setConnected(false);
			return "failed";
		}
	}

	public String logout(){
		initGestionBeans();
		gestionLogin.logOut();
		setName("");
		setPassword("");
		setConnected(false);
		return "success";
	}

	private void initGestionBeans(){
		if(gestionLogin == null){
			InitialContext context;
			try {
				context = new InitialContext();
				gestionLogin = (GestionLoginRemote)context.lookup("LoginBean");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
	}
}
