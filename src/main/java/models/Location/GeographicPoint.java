package models.Location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;


@Entity
public class GeographicPoint extends PanacheEntity{
    
    public String x;
    public String y;
    public String z;
    @OneToOne(mappedBy = "geometry")
    public GeographicLocation geometry;

    public GeographicPoint() {
    }

    public GeographicPoint( String x, String y, String z) {
        
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
