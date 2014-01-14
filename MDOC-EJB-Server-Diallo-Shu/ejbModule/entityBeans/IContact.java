package entityBeans;

import java.util.Set;


public interface IContact{
	public long getId();
	public void setId(long id);
	
	public int getVersion();
	public void setVersion(int version);
	
	public String getFirstname();
	public void setFirstname(String firstname);
	
	public String getLastname();
	public void setLastname(String lastname);
	
	public String getEmail();
	public void setEmail(String email);
	
	public IAddress getAddress();
	public void setAddress(IAddress address);
	
	public Set<IPhoneNumber> getProfiles();
	public void setProfiles(Set<IPhoneNumber> profiles);
	
	public Set<IContactGroup> getBooks();
	public void setBooks(Set<IContactGroup> books);
}
