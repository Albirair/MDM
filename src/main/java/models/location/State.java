package models.location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
public class State extends PanacheEntity{

    public String arabic_name;
    public String english_name;
    public String code;
    public String alternative_ar_name;
    public String alternative_en_name;
    public long population;
    public long area;
    public Date date_of_population_estimation;

    
    // Primary Key:
    @OneToMany
    @JsonManagedReference(value = "settlement_state")
    public Set <Settlement> settlement_state;

    // Forign Key:
    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "state_country")
    public Country state_country;

    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "GeoAdd_State")
    public GeographicAddress GeoAdd_State;

    @ManyToOne
    @JoinColumn
    @JsonBackReference(value = "locality_state")
    public Locality locality_state;
  

    public State() {
    }

    public State( String arabic_name, String english_name, String code, String alternative_ar_name, String alternative_en_name, long population, long area, Date date_of_population_estimation, Country state_country) {

        this.arabic_name =  arabic_name;
        this.english_name = english_name;
        this.code = code;
        this.alternative_ar_name = alternative_ar_name;
        this.alternative_en_name = alternative_en_name;
        this.population = population;
        this.area = area;
        this.date_of_population_estimation = date_of_population_estimation;
        this.state_country = state_country;
    }
}
