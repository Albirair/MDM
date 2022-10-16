package models.location;

// import io.quarkus.hibernate.orm.panache.PanacheEntity;
import models.ModelBase;
import javax.persistence.*;
import models.Patchable;



@Entity
public class GeographicPoint extends ModelBase implements Patchable{

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

    @Override
    public void setForeignKey(Object element)
            throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        
    }
}
