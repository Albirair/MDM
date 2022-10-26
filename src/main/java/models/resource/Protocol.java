package models.resource;

import java.util.Set;
import javax.persistence.*;

public class Protocol {
	public int currentPortNumber;
	public boolean isEphemeral;
	public int direction;
	public int portRangeStart;
	public int portRangeEnd;
	@ManyToMany(mappedBy = "support")
	public Set<LogicalDevice> support;
}