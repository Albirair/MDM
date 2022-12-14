package models.location;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;
import java.util.Set;


@Entity
public class Locality extends PanacheEntity{

    public String name;
    public String code;
    
    // Forign Key:
    @OneToMany
    @JsonManagedReference(value = "locality_state")
    public Set <State> locality_state;

    public Locality() {
    }

    public Locality( String code, String name) {

        this.name = name;
        this.code = code;
    }
}
