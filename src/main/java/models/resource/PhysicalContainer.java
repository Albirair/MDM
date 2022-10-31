package models.resource;

import javax.persistence.*;

@Entity
public class PhysicalContainer extends ManagedHardware {
	public boolean hotSwappable;
	public boolean removable;
	public boolean replaceable;
}