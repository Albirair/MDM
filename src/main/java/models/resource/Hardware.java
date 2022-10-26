package models.resource;

import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class Hardware extends PhysicalResource {
	public String depth;
	public String height;
	public int measurementUnits;
	public String weight;
	public int weightUnits;
	public String width;
	@ManyToOne
	@JoinColumn
	@JsonBackReference("hardware")
	public Hardware hardware;
	@OneToMany(mappedBy = "hardware", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference("hardware")
	public Set<Hardware> contains;
	@ManyToOne
	@JoinColumn
	@JsonBackReference("physicalDevice")
	public PhysicalDevice physicalDevice;
	@ManyToOne
	@JoinColumn
	@JsonBackReference("equipmentHolder")
	public EquipmentHolder equipmentHolder;
}