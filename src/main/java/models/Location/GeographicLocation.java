package models.Location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;

@Entity
public class GeographicLocation extends PanacheEntity{
    
    public String href;
    public String name;
    public String geometryType;
    public String accuracy;
    public String spatialRef;
    public String type;
    public String schemaLocation;
    // Forign Key:
    @OneToOne
    @JoinColumn(name = "geometry")
    public GeographicPoint geometry;
    // Primary Key:
    @OneToOne(mappedBy = "GeographicLocation")
    public GeographicAddress GeographicAddress;



    public GeographicLocation() {
    }

    public GeographicLocation( String href, String name, String geometryType,String accuracy, String spatialRef, GeographicPoint geometry,String type, String schemaLocation) {
        
        this.href = href;
        this.name = name;
        this.geometryType = geometryType;
        this.accuracy = accuracy;
        this.spatialRef = spatialRef;
        this.geometry = geometry;
        this.type = type;
        this.schemaLocation = schemaLocation;
    }
}