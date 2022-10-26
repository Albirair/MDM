package models.resource;

import java.util.Set;
import javax.persistence.ManyToMany;

public class PhysicalConnector extends Hardware {
	public int cableType;
	public int gender;
	public boolean inUse;
	public String pinDescription;
	public int typeOfConnector;
	@ManyToMany(mappedBy = "connector")
	public Set<PhysicalPort> port;
}