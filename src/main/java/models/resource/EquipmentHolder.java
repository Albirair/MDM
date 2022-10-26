package models.resource;

import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class EquipmentHolder extends Hardware {
	public String type;
	public String state;
	public String manufacturer;
	public String location;
	public String ituArcStateAndStatusList;
	public boolean isReportingAlarms;
	public String expectedOrInstalledEquipmentRef;
	public String asapRef;
	public String acceptableEquipmentTypeList;
	public String acceptableEquipmentList;
	public int typeOfHolder;
	public boolean isSolitaryHolder;
	public int Status;
	@OneToMany(mappedBy = "equipmentHolder", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference("equipmentHolder")
	public Set<Hardware> holds;
	@ManyToOne
	@JoinColumn
	@JsonBackReference("holderComposite")
	public HolderComposite holderComposite;
}