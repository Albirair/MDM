package models.resource;

import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class PhysicalDeviceComposite extends PhysicalDevice {
	@OneToMany(mappedBy = "physicalDeviceComposite", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference("physicalDeviceComposite")
	public Set<PhysicalDevice> has;
}