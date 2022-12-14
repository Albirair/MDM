package models.location;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
// import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;


@Entity
public class Zone extends PanacheEntity{

    public String name;
    public String code;

    @OneToOne(mappedBy = "zone_office") // MANY TO ONE!
    @JoinColumn
    public Office zone_office;

    public Zone() {
    }

    public Zone( String name, String code) {

        this.name = name;
        this.code = code;
    }
}