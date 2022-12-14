
package models.location;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;
import java.util.Set;


@Entity
public class Office extends PanacheEntity {

    public String name;
    public String address;
    
    // Primary Key:
    @OneToMany
    @JsonManagedReference(value = "officeId_cluster")
    public Set <Cluster> officeId_cluster;
    // Forign Key:

    @OneToOne
    @JoinColumn(name = "zone_office") // CHECK THIS RELATION !
    public Zone zone_office;

    @OneToOne
    @JoinColumn(name = "GeoAdd_office")
    public GeographicAddress GeoAdd_office;


    public Office() {
    }

    public Office( String name, GeographicAddress GeoAdd_office, Zone zone_office) {

        this.name = name;
        this.GeoAdd_office = GeoAdd_office;
        this.zone_office = zone_office;

    }

}
