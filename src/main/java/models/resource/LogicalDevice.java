package models.resource;

import java.util.Set;
import javax.persistence.*;

@Entity
public class LogicalDevice extends LogicalResource {
	@ManyToMany
	public Set<Protocol> protocol;
}