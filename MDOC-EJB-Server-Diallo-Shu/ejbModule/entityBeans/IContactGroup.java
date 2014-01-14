package entityBeans;

import java.util.Set;

public interface IContactGroup{
	public long getGroupId();
	public void setGroupId(long groupId);

	public String getGroupName();
	public void setGroupName(String groupName);
	
	public Set<IContact> getContacts();
	public void setContacts(Set<IContact> contacts);
}
