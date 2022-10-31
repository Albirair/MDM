package models.resource;
import javax.persistence.*;

@Entity
public class HolderAtomic extends EquipmentHolder {
	public String physicalDescription;
	public boolean uniquePhysical;
}