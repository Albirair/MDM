package models.location;

import models.ModelBase;
import javax.persistence.*;


@Entity
public class GeographicPoint extends ModelBase {

    public String x;
    public String y;
    public String z;

    @OneToOne(mappedBy = "GeoLoc_geoPoint")
    @JoinColumn
    public GeographicLocation GeoLoc_geoPoint;

    public GeographicPoint() {
    }

    public GeographicPoint(String x, String y, String z, GeographicLocation GeoLoc_geoPoint) {

        this.x = x;
        this.y = y;
        this.z = z;
        this.GeoLoc_geoPoint = GeoLoc_geoPoint;
    }
}
