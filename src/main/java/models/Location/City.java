package models.Location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;

@Entity
public class City extends PanacheEntity{
    
    public String name;
    public String is_Capital;
    @OneToOne
    @JoinColumn(name = "City_state")
    public State City_state;
    @OneToOne(mappedBy = "GeAdd_city")
    public GeographicAddress GeAdd_city;


    public City() {
    }

    public City( String name, String is_Capital, State City_state) {
        
        this.name = name;
        this.is_Capital = is_Capital;
        this.City_state = City_state;
    }
}
