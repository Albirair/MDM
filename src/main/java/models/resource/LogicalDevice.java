package models.resource;

import java.util.Set;
import javax.persistence.*;

@Entity
public class LogicalDevice extends LogicalResource {
	@ManyToMany(mappedBy = "support")
	public Set<Protocol> support;
}