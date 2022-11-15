package models.resource;

import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class LogicalResource extends Resource {
	public int status;
	public int serviceState;
	public boolean isOperational;
	@OneToMany(mappedBy = "logicalResource", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference("logicalResource")
	public Set<LogicalPhysicalResource> logicalPhysicalResource;
}