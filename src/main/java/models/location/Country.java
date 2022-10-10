package models.location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;

@Entity
public class Country extends PanacheEntity{

    public String name;
    public String code;
    // Forign Key:
    @OneToOne
    @JoinColumn(name = "coun_continent")
    public Continent coun_continent;
    //  Primary Key:
    @OneToOne(mappedBy = "State_country")
    public State State_country;
    @OneToOne(mappedBy = "GeoAdd_Country")
    public GeographicAddress GeoAdd_Country;

    public Country() {
    }

    public Country( String name, String code, Continent coun_continent) {

        this.name = name;
        this.code = code;
        this.coun_continent = coun_continent;
    }
}
