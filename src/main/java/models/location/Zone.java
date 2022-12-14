package models.location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
// import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.Set;


@Entity
public class Zone extends PanacheEntity{

    public String name;
    public String code;

    @OneToMany
    @JsonManagedReference(value = "zone_office")
    public Set <Office> zone_office;

    public Zone() {
    }

    public Zone( String name, String code) {

        this.name = name;
        this.code = code;
    }
}