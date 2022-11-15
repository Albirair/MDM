package models.resource;

import javax.persistence.*;

@Entity
public class ManagedHardware extends Hardware {
	public String additionInfo;
	public int administrativeState;
	public boolean physicalAlarmReportingEnabled;
	public int physicalAlarmStatus;
	public String coolingRequirements;
	public int hardwarePurpose;
}