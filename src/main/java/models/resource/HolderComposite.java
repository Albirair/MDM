package models.resource;

import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class HolderComposite extends EquipmentHolder {
	public String cableManagementStrategy;
	public int mountingOptions;
	public String serviceApproach;
	@OneToMany(mappedBy = "holderComposite", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference("holderComposite")
	public Set<EquipmentHolder> has;
}