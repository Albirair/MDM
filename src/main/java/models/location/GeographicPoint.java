package models.location;

// import io.quarkus.hibernate.orm.panache.PanacheEntity;
import models.ModelBase;
import javax.persistence.*;

@Entity
public class GeographicPoint extends ModelBase {

    public String x;
    public String y;
    public String z;
    @OneToOne(mappedBy = "geometry")
    public GeographicLocation geometry;

    public GeographicPoint() {
    }

    public GeographicPoint(String x, String y, String z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }
}
