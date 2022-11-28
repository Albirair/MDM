package models.location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.*;

@Entity
public class Block_Hay extends PanacheEntity{
    public String name;
    public String code;
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

    public Block_Hay(  String name, String code, Locality locality, GeographicAddress GeoAdd_BlockHay) {

        this.name = name;
        this.code = code;
        this.locality = locality;
        this.GeoAdd_BlockHay = GeoAdd_BlockHay;
    }
}
