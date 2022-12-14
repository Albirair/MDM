package models.location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
public class Country extends PanacheEntity{

    public String arabic_name; 
    public String english_name;
    public String code;
    public long population;
    public long area;
    public Date date_of_population_estimation;

    // Forign Key:
    @OneToMany
    @JsonManagedReference(value = "country_continent")
    public Set <Continent> country_continent;
    //  Primary Key:
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "state_country")
    public State state_country;
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "GeoAdd_Country")
    public GeographicAddress GeoAdd_Country;

    public Country() {
    }

    public Country( String arabic_name, String english_name, String code ,long population, long area, Date date_of_population_estimation, State state_country, GeographicAddress GeoAdd_Country) {

        this.arabic_name =  arabic_name;
        this.english_name = english_name;
        this.code = code;
        this.population = population;
        this.area = area;
        this.date_of_population_estimation = date_of_population_estimation;
        this.state_country = state_country;
        this.GeoAdd_Country = GeoAdd_Country;
    }
}
