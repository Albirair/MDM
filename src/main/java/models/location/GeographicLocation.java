package models.location;

import models.ModelBase;
import javax.persistence.*;
import javax.persistence.Entity;

@Entity
public class GeographicLocation extends ModelBase {

    public String href;
    public String name;
    public String geometryType;
    public String accuracy;
    public String spatialRef;
    public String type;
    public String schemaLocation;
    
    // Forign Key:
    @OneToOne
    @JoinColumn(name = "GeoLoc_geoPoint")
    public GeographicPoint GeoLoc_geoPoint;
    // Primary Key:
    @OneToOne(mappedBy = "GeoAdd_geoLoca")
    @JoinColumn
    public GeographicAddress GeoAdd_geoLoca;

    public GeographicLocation() {
    }

    public GeographicLocation(String href, String name, String geometryType, String accuracy, String spatialRef,
            GeographicPoint GeoLoc_geoPoint, String type, String schemaLocation) {

        this.href = href;
        this.name = name;
        this.geometryType = geometryType;
        this.accuracy = accuracy;
        this.spatialRef = spatialRef;
        this.GeoLoc_geoPoint = GeoLoc_geoPoint;
        this.type = type;
        this.schemaLocation = schemaLocation;
    }
}