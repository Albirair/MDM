package models.resource;

import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class LogicalResource extends Resource {
	@OneToMany(mappedBy = "logicalResource", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference("logicalResource")
	public Set<LogicalPhysicalResource> logicalPhysicalResource;
}