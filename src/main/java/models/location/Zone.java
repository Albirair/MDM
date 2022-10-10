package models.location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;


@Entity
public class Zone extends PanacheEntity{

    public String name;
    public String code;
    // Primary Key:
    @OneToOne(mappedBy = "zone_state")
    public State zone_state;
    @OneToOne(mappedBy = "zone_office")
    public Office zone_office;

    public Zone() {
    }

    public Zone( String name, String code) {

        this.name = name;
        this.code = code;
    }
}