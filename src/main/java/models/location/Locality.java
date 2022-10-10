package models.location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;

@Entity
public class Locality extends PanacheEntity{

    public String state;
    public String name;
    // Forign Key:
    @OneToOne
    @JoinColumn(name = "city")
    public City city;
    // @OneToOne(mappedBy = "locality")
    // public Locality locality;
    // Primary Key:
    @OneToOne(mappedBy = "GeoAdd_Locality")
    public GeographicAddress GeoAdd_Locality;

    public Locality() {
    }

    public Locality( String state, String name, City city) {

        this.state = state;
        this.name = name;
        this.city = city;
    }
}
