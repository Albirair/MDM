package models.resource;

import java.util.Set;
import javax.persistence.*;

@Entity
public class PhysicalPort extends ManagedHardware {
    public int duplexMode;
    public int ifType;
    public int portNumber;
    public int type;
    public String vendorPortName;
    @ManyToMany
    public Set<PhysicalConnector> connector;
}
