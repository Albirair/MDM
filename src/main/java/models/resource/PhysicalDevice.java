package models.resource;

import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class PhysicalDevice extends PhysicalResource {
	public boolean backplaneIndependent;
	public int backplaneNumber;
	public String configurationOrder;
	public String deviceGroupID;
	public boolean isComposite;
	public boolean canMixPower;
	@ManyToOne
	@JoinColumn
	@JsonBackReference("physicalDeviceComposite")
	public PhysicalDeviceComposite physicalDeviceComposite;
	@OneToMany(mappedBy = "physicalDevice", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference("physicalDevice")
	public Set<Hardware> consistsOf;
}