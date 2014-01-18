package sessionBeans.local;

import javax.ejb.Local;

@Local
public interface GestionLoginLocal {
	public boolean logIn();

	public void logOut();
	
	public void destroyBean();
	
	public String getName();
	
	public void setName(String name);
	
	public String getPassword();
	
	public void setPassword(String password);

	public Boolean getConnected();

	public void setConnected(Boolean connected);
}
