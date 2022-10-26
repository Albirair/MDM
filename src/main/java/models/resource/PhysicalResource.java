package models.resource;

import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class PhysicalResource extends Resource {
	public String manufactureDate;
	public String otherIdentifier;
	public int powerState;
	public String serialNumber;
	public String versionNumber;
	@OneToMany(mappedBy = "physicalResource", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference("physicalResource")
	public Set<LogicalPhysicalResource> logicalPhysicalResource;
}