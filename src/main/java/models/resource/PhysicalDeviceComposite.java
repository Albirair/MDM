package models.resource;

import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class PhysicalDeviceComposite {
	@OneToMany(mappedBy = "physicalDeviceComposite", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference("physicalDeviceComposite")
	public Set<PhysicalDevice> has;
}