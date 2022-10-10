package models.location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;

@Entity
public class Block_Hay extends PanacheEntity{

    public String state;
    public String name;
    // Forign Key:
    @OneToOne
    @JoinColumn(name = "locality")
    public Locality locality;
    // Primary Key:
    @OneToOne
    @JoinColumn(name = "GeoAdd_BlockHay")
    public GeographicAddress GeoAdd_BlockHay;


    public Block_Hay() {
    }

    public Block_Hay( String state, String name, Locality locality) {

        this.state = state;
        this.name = name;
        this.locality = locality;
    }
}
