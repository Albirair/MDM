package models.location;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;

@Entity
public class GeographicSubAddress extends PanacheEntity{

    public String name;
    public String label;
    public String GLN_code;
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
    @ManyToOne 
    @JoinColumn(name = "GeoAdd_GeoSubAdd")
    @JsonBackReference(value = "GeoAdd_GeoSubAdd")
    public GeographicAddress GeoAdd_GeoSubAdd;

    public GeographicSubAddress() {
    }

    public GeographicSubAddress( String name, String label, String GLN_code, String href, String subAddressType, String subUnitType, String subUnitNumber,String levelType, String levelNumber, String buildingName, String privateStreetName, String privateStreetNumber, String type, String schemaLocation) {

        this.name = name;
        this.label = label;
        this.GLN_code = GLN_code;
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
