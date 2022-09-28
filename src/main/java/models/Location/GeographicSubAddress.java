package models.Location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;

@Entity
public class GeographicSubAddress extends PanacheEntity{
    
    public String href;
    public String subAddressType;
    public String subUnitType;
    public String subUnitNumber;
    public String levelType;
    public String levelNumber;
    public String buildingName;
    public String privateStreetName;
    public String privateStreetNumber;
    public String type;
    public String schemaLocation;
    // Forign Key:
    @OneToOne
    @JoinColumn(name = "GeographicAddressId")
    public GeographicAddress GeographicAddress;

    public GeographicSubAddress() {
    }

    public GeographicSubAddress( String href, String subAddressType, String subUnitType, String subUnitNumber,String levelType, String levelNumber, String buildingName, String privateStreetName, String privateStreetNumber, String type, String schemaLocation) {
        
        this.href = href;
        this.subAddressType = subAddressType;
        this.subUnitType = subUnitType;
        this.subUnitNumber = subUnitNumber;
        this.levelNumber = levelNumber;
        this.levelType = levelType;
        this.buildingName = buildingName;
        this.privateStreetName = privateStreetName;
        this.privateStreetNumber = privateStreetNumber;
        this.type = type;
        this.schemaLocation = schemaLocation;

    }
    
}
