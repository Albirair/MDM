package models.location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
// import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
// import java.util.Set;


@Entity
public class Continent extends PanacheEntity{

    public String name;
    public long population;
    public long area;

    // Primary Key:
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "country_continent")
    public Country country_continent;

    // not updated yet
    // @OneToOne(mappedBy = "GeoAdd_Continent")
    // @JoinColumn
    // public GeographicAddress GeoAdd_Continent;

    public Continent() {
    }

    public Continent( String name, long population, long area) {

        this.name = name;
        this.population = population;
        this.area = area;
    }
}
