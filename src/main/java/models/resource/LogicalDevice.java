package models.resource;

import java.util.Set;
import javax.persistence.*;

public class LogicalDevice extends LogicalResource {
	@ManyToMany(mappedBy = "support")
	public Set<Protocol> support;
}