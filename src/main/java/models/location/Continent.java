package models.location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;

@Entity
public class Continent extends PanacheEntity{

    public String name;
    // Primary Key:
    @OneToOne(mappedBy = "coun_continent")
    public Country coun_continent;
    @OneToOne(mappedBy = "GeoAdd_Continent")
    public GeographicAddress GeoAdd_Continent;

    public Continent() {
    }

    public Continent( String name) {

        this.name = name;
    }
}
