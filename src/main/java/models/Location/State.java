package models.Location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;

@Entity
public class State extends PanacheEntity{
    
    public String name;
    public String code;
    // Primary Key:
    @OneToOne(mappedBy = "City_state")
    public City state;
    @OneToOne(mappedBy = "GeoAdd_State")
    public GeographicAddress GeoAdd_State;
    @OneToOne(mappedBy = "GeoAdd_office")
    public Office GeoAdd_office;

    // Forign Key:
    @OneToOne
    @JoinColumn(name = "State_country")
    public Country State_country;
    @OneToOne
    @JoinColumn(name = "zone_state")
    public Zone zone_state;
    

    public State() {
    }

    public State( String name, String code, Country State_country, Zone zone_state) {
        
        this.name = name;
        this.code = code;
        this.State_country = State_country;
        this.zone_state = zone_state;
    }
}
