package models.resource;

import java.util.Set;
import javax.persistence.*;

import models.ModelBase;

@Entity
public class Protocol extends ModelBase {
	public int currentPortNumber;
	public boolean isEphemeral;
	public int direction;
	public int portRangeStart;
	public int portRangeEnd;
	@ManyToMany(mappedBy = "protocol")
	public Set<LogicalDevice> support;
}