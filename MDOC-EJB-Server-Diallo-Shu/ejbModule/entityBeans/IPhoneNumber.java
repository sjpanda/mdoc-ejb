package entityBeans;


public interface IPhoneNumber {
	
	public long getId();
	public void setId(long id);

	public int getVersion();
	public void setVersion(int version);
	
	public String getPhoneKind();
	public void setPhoneKind(String phoneKind);

	public String getPhoneNumber();
	public void setPhoneNumber(String phoneNumber);
	
	public IContact getContact();
	public void setContact(IContact contact);
}
