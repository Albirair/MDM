
package models.location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;


@Entity
public class Office extends PanacheEntity {

    public String name;
    public String address;
    // Primary Key:
    @OneToOne(mappedBy = "officeId_cluster")
    public Cluster officeId_cluster;
    // Forign Key:
    @OneToOne
    @JoinColumn(name = "zone_office")
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
